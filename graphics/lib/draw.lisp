(defclass point ()
  ((array :accessor point-array
          :initform (make-array 4 :initial-contents '(0 0 0 1.0) :adjustable t))))

(defun make-point (&key initial-list initial-array)
  (let ((point (make-instance 'point)))
    (cond
      (initial-list
       (adjust-array (point-array point) 4 :initial-contents (append initial-list '(1.0))))
      (initial-array
       (loop
          for i from 0 to 3
          do (setf (aref (point-array point) i)
                   (aref initial-array i)))))
    point))

(defmethod point-x ((point point))
  (aref (point-array point) 0))

(defmethod point-y ((point point))
  (aref (point-array point) 1))

(defmethod point-z ((point point))
  (aref (point-array point) 2))

(defun add-point-to-matrix (matrix point)
  (matrix-add-column matrix (point-array point)))
                                               
(defun add-edge-to-matrix (matrix point1 point2)
  (add-point-to-matrix matrix point1)
  (add-point-to-matrix matrix point2))

(defun draw-lines-from-matrix (screen matrix &key color-function)
  (loop
     for i from 0 to (- (matrix-dimension matrix 1) 1) by 2
     do (draw-line screen
                   (make-point :initial-array (matrix-get-column matrix i))
                   (make-point :initial-array (matrix-get-column matrix (+ i 1)))
                   :color-function color-function)))

(defun draw-line (screen point1 point2 &key (color '(0 0 0)) color-function)
  (let ((x1 (point-x point1))
        (y1 (point-y point1))
        (z1 (point-z point1))
        (x2 (point-x point2))
        (y2 (point-y point2))
        (z2 (point-z point2))
        (get-color
         (lambda (&optional x y)
           (if color-function
               (funcall color-function x y)
               color))))
    (let* ((x x1)
           (y y1)
           d
           (dx (- x2 x1))
           (dy (- y2 y1))
           (A dy)
           (B (* -1 dx)))
      (if (>= dx 0)
          (cond
            ((and (>= dy 0) (< dy dx)) ; octant 1
             ;;(format t "octant 1~%")
             (setf d (+ (* A 2) B))
             (loop
                for x from x1 to x2
                do
                ;;(format t "x: ~a y: ~a d: ~a~%" x y d)
                  (plot-pixel screen x y (funcall get-color x y))
                  (when (> d 0)
                    (incf y)
                    (incf d (* B 2)))
                  (incf d (* A 2))))
            ((>= dy dx) ; octant 2
             ;;(format t "octant 2~%")
             (setf d (+ A (* B 2)))
             (loop
                for y from y1 to y2
                do
                ;;(format t "x: ~a y: ~a d: ~a~%" x y d)
                  (plot-pixel screen x y (funcall get-color x y))
                  (when (< d 0)
                    (incf x)
                    (incf d (* A 2)))
                  (incf d (* B 2))))
            ((and (< dy 0) (<= (* -1 dy) dx))
             ;;(format t "octant 8~%")
             (setf d (- (* A 2) B))
             (loop
                for x from x1 to x2
                do
                ;;(format t "x: ~a y: ~a d: ~a~%" x y d)
                  (plot-pixel screen x y (funcall get-color x y))
                  (when (< d 0)
                    (decf y)
                    (decf d (* B 2)))
                  (incf d (* A 2))))
            ((> (* -1 dy) dx)
             ;;(format t "octant 7~%")
             (setf d (- A (* B 2)))
             (loop
                for y from y1 downto y2
                do
                ;;(format t "x: ~a y: ~a d: ~a~%" x y d)
                  (plot-pixel screen x y (funcall get-color x y))
                  (when (> d 0)
                    (incf x)
                    (incf d (* A 2)))
                  (decf d (* B 2)))))
          (draw-line screen point2 point1
                     :color color
                     :color-function color-function)))))
  
  
  
