(defun square (x)
  (* x x))

(defun distance (x1 y1 x2 y2)
  (sqrt (+ (square (- x2 x1)) (square (- y2 y1)))))

(defun bound (x bound1 bound2)
  (if (< bound1 bound2)
      (max (min x bound2) bound1)
      (max (min x bound1) bound2)))

(defun create-ppm (filename array)
  (with-open-file (stream filename
                          :direction :output
                          :if-exists :supersede
                          :if-does-not-exist :create)
    (format stream "P3 ~{~d ~d ~} 255~%" (array-dimensions array))
    (let ((xres (first (array-dimensions array)))
          (yres (second (array-dimensions array))))
      (dotimes (x xres)
        (dotimes (y yres)
          (format stream "~{~d ~d ~d ~}" (aref array x y)))
        (format stream "~%")))))

(defun init-image (xres yres &optional (initial-color '(255 255 255)))
  (make-array (list xres yres) :initial-element initial-color)) 

(defun plot-pixel (array x y color)
  (setf (aref array y x) color))

(defun random-color ()
  (loop
     repeat 3
     collect (random 255)))

(defun generate-radial-gradient-function (xcenter ycenter radius startcolor endcolor)
  (function
   (lambda (x y)
    (let* ((startr (first startcolor))
           (startg (second startcolor))
           (startb (third startcolor))
           (endr (first endcolor))
           (endg (second endcolor))
           (endb (third endcolor))
           (rscale (/ (- endr startr) radius))
           (gscale (/ (- endg startg) radius))
           (bscale (/ (- endb startb) radius))
           (current (distance xcenter ycenter x y)))
      (list (bound (round (+ startr (* rscale current))) startr endr)
            (bound (round (+ startg (* gscale current))) startg endg)
            (bound (round (+ startb (* bscale current))) startb endb))))))

(defun draw-line (array x1 y1 x2 y2 color-function)
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
           (setq d (+ (* A 2) B))
           (loop
              for x from x1 to x2
              do
                ;;(format t "x: ~a y: ~a d: ~a~%" x y d)
                (plot-pixel array x y (funcall color-function x y))
                (when (> d 0)
                  (incf y)
                  (incf d (* B 2)))
                (incf d (* A 2))))
          ((>= dy dx) ; octant 2
           ;;(format t "octant 2~%")
           (setq d (+ A (* B 2)))
           (loop
              for y from y1 to y2
              do
                ;;(format t "x: ~a y: ~a d: ~a~%" x y d)
                (plot-pixel array x y (funcall color-function x y))
                (when (< d 0)
                  (incf x)
                  (incf d (* A 2)))
                (incf d (* B 2))))
          ((and (< dy 0) (<= (* -1 dy) dx))
           ;;(format t "octant 8~%")
           (setq d (- (* A 2) B))
           (loop
              for x from x1 to x2
              do
                ;;(format t "x: ~a y: ~a d: ~a~%" x y d)
                (plot-pixel array x y (funcall color-function x y))
                (when (< d 0)
                  (decf y)
                  (decf d (* B 2)))
                (incf d (* A 2))))
          ((> (* -1 dy) dx)
           ;;(format t "octant 7~%")
           (setq d (- A (* B 2)))
           (loop
              for y from y1 downto y2
              do
                ;;(format t "x: ~a y: ~a d: ~a~%" x y d)
                (plot-pixel array x y (funcall color-function x y))
                (when (> d 0)
                  (incf x)
                  (incf d (* A 2)))
                (decf d (* B 2)))))
        (draw-line array x2 y2 x1 y1 color-function))))
      
                   
         
