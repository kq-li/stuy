(defvar *max-s-step* 100)
(defvar *max-u-step* 25)
(defvar *max-v-step* 25)

(defun add-point (point &key matrix)
  (if (= (length point) 3)
      (matrix-add-column matrix (concatenate 'vector point #(1.0)))
      (matrix-add-column matrix point)))

(defun add-xyz (x y z &key matrix)
  (matrix-add-column matrix `#(,x ,y ,z 1.0)))

(defun add-edge (point1 point2 &key (matrix *edge-matrix*))
  (add-point point1 :matrix matrix)
  (add-point point2 :matrix matrix))

(defun generate-parametric-curve (x-function y-function z-function
                                  &key (max-step *max-s-step*))
  (let ((matrix (make-matrix :dimensions '(4 0))))
    (loop
       for s from 0 to max-step
       do
         (add-point `#(,@(mapcar (lambda (f)
                                   (funcall f (/ s max-step)))
                                 (list x-function y-function z-function)))
                    :matrix matrix))
    matrix))

(defun generate-circle (x y z r)
  (generate-parametric-curve (lambda (s) (+ x (* r (cos (* 2 PI s)))))
                             (lambda (s) (+ y (* r (sin (* 2 PI s)))))
                             (lambda (s) 0)))

(defun add-circle (x y z r)
  (let ((circle (generate-circle x y z r)))
    (loop
       for i from 0 to (- (matrix-dimension circle 1) 2)
       do (add-edge (matrix-get-column circle i)
                    (matrix-get-column circle (+ i 1))))))

(defun generate-hermite-curve (x0 y0 x1 y1 rx0 ry0 rx1 ry1)
  (generate-parametric-curve
   (lambda (s)
     (let* ((H-inverse (make-matrix :initial-contents
                                    '((  2 -2  1  1  )
                                      ( -3  3 -2 -1  )
                                      (  0  0  1  0  )
                                      (  1  0  0  0  ))))
            (G (make-matrix :initial-contents `((,x0) (,x1) (,rx0) (,rx1))))
            (C (matrix-matrix-mult h-inverse G)))
       (loop
          for i from 0 to 3
          sum (* (matrix-get-element C i 0) (expt s (- 3 i))))))
   (lambda (s)
     (let* ((H-inverse (make-matrix :initial-contents
                                    '((  2 -2  1  1  )
                                      ( -3  3 -2 -1  )
                                      (  0  0  1  0  )
                                      (  1  0  0  0  ))))
            (G (make-matrix :initial-contents `((,y0) (,y1) (,ry0) (,ry1))))
            (C (matrix-matrix-mult h-inverse G)))
       (loop
          for i from 0 to 3
          sum (* (matrix-get-element C i 0) (expt s (- 3 i))))))
   (lambda (s) 0)))

(defun add-hermite-curve (x0 y0 x1 y1 rx0 ry0 rx1 ry1)
  (let ((hermite (generate-hermite-curve x0 y0 x1 y1 rx0 ry0 rx1 ry1)))
    (loop
       for i from 0 to (- (matrix-dimension hermite 1) 2)
       do (add-edge (matrix-get-column hermite i)
                    (matrix-get-column hermite (+ i 1))))))

(defun generate-bezier-curve (x0 y0 x1 y1 x2 y2 x3 y3)
  (generate-parametric-curve
   (lambda (s)
     (let* ((H-inverse (make-matrix :initial-contents
                                    '(( -1  3 -3  1  )
                                      (  3 -6  3  0  )
                                      ( -3  3  0  0  )
                                      (  1  0  0  0  ))))
            (G (make-matrix :initial-contents `((,x0) (,x1) (,x2) (,x3))))
            (C (matrix-matrix-mult h-inverse G)))
       (loop
          for i from 0 to 3
          sum (* (matrix-get-element C i 0) (expt s (- 3 i))))))
   (lambda (s)
     (let* ((H-inverse (make-matrix :initial-contents
                                    '(( -1  3 -3  1  )
                                      (  3 -6  3  0  )
                                      ( -3  3  0  0  )
                                      (  1  0  0  0  ))))
            (G (make-matrix :initial-contents `((,y0) (,y1) (,y2) (,y3))))
            (C (matrix-matrix-mult h-inverse G)))
       (loop
          for i from 0 to 3
          sum (* (matrix-get-element C i 0) (expt s (- 3 i))))))
   (lambda (s) 0)))     

(defun add-bezier-curve (x0 y0 x1 y1 x2 y2 x3 y3)
  (let ((bezier (generate-bezier-curve x0 y0 x1 y1 x2 y2 x3 y3)))
    (loop
       for i from 0 to (- (matrix-dimension bezier 1) 2)
       do (add-edge (matrix-get-column bezier i)
                    (matrix-get-column bezier (+ i 1))))))

(defun add-triangle (point1 point2 point3 &key (matrix *triangle-matrix*))
  (add-point point1 :matrix matrix)
  (add-point point2 :matrix matrix)
  (add-point point3 :matrix matrix))

(defun cross-product (vector1 vector2)
  `#(,(- (* (aref vector1 1) (aref vector2 2))
         (* (aref vector1 2) (aref vector2 1)))
     ,(- (* (aref vector1 2) (aref vector2 0))
         (* (aref vector1 0) (aref vector2 2)))
     ,(- (* (aref vector1 0) (aref vector2 1))
         (* (aref vector1 1) (aref vector2 0)))))

(defun displacement-vector (point1 point2)
  `#(,@(loop
          for i from 0 to 2
          collect (- (aref point2 i)
                     (aref point1 i)))))

(defun triangle-normal (vertex1 vertex2 vertex3)
  (let* ((vector1 (displacement-vector vertex1 vertex2))
         (vector2 (displacement-vector vertex2 vertex3))
         (normal (cross-product vector1 vector2)))
    normal))

(defun add-box (x y z width height depth &key (matrix *triangle-matrix*))
  (let ((vertices (make-matrix :dimensions '(4 0))))
    (add-xyz x y z :matrix vertices)
    (add-xyz x y (+ z depth) :matrix vertices)
    (add-xyz x (+ y height) (+ z depth) :matrix vertices)
    (add-xyz x (+ y height) z :matrix vertices)
    (add-xyz (+ x width) (+ y height) z :matrix vertices)
    (add-xyz (+ x width) (+ y height) (+ z depth) :matrix vertices)
    (add-xyz (+ x width) y (+ z depth) :matrix vertices)
    (add-xyz (+ x width) y z :matrix vertices)
    (loop
       for (first second third) in '(
                                        (0 1 2) (2 3 0) ;; left
                                        (3 2 5) (5 4 3) ;; top
                                        (4 5 6) (6 7 4) ;; right
                                        (7 6 1) (1 0 7) ;; bottom
                                        (1 6 5) (5 2 1) ;; front
                                        (0 3 4) (4 7 0) ;; back
                                     )
       do
         (add-triangle (matrix-get-column vertices first)
                       (matrix-get-column vertices second)
                       (matrix-get-column vertices third)))))

(defun generate-nested-parametric-curve (x-function y-function z-function
                                         &key
                                           (max-u-step *max-u-step*)
                                           (max-v-step *max-v-step*))
  (let ((matrix (make-matrix :dimensions '(4 0))))
    (loop
       for u from 0 to max-u-step
       do
         (loop
            for v from 0 to max-v-step
            do
              (let ((point `#(,@(mapcar (lambda (f)
                                          (funcall f
                                                   (/ u max-u-step)
                                                   (/ v max-v-step)))
                                        (list x-function y-function z-function)))))
                (add-point point :matrix matrix))))
    matrix))

(defun generate-sphere (x y z r)
  (generate-nested-parametric-curve
   (lambda (u v)
     (+ x (* r (cos (* PI u)))))
   (lambda (u v)
     (+ y (* r (sin (* PI u)) (sin (* 2 PI v)))))
   (lambda (u v)
     (+ z (* r (sin (* PI u)) (cos (* 2 PI v)))))))

(defun add-sphere (x y z r)
  (let* ((n *max-u-step*)
         (sphere (generate-sphere x y z r)))
    (loop
       for i from 0 to (- (matrix-dimension sphere 1) n 2)
       do
         (let ((triangle1 (list i (+ i 1) (+ i n 1)))
               (triangle2 (list i (+ i n 1) (+ i n))))
           (apply 'add-triangle
                  (loop
                     for vertex in triangle1
                     collect (matrix-get-column sphere vertex)))
           (apply 'add-triangle
                  (loop
                     for vertex in triangle2
                     collect (matrix-get-column sphere vertex)))))))

(defun generate-torus (x y z cr tr)
  (generate-nested-parametric-curve
   (lambda (u v)
     (let ((A (* 2 PI u))
           (B (* 2 PI v)))
       (+ x (* cr (cos A) (cos B)) (* tr (cos B)))))
   (lambda (u v)
     (let ((A (* 2 PI u))
           (B (* 2 PI v)))
       (+ y (* cr (sin A)))))
   (lambda (u v)
     (let ((A (* 2 PI u))
           (B (* 2 PI v)))
       (+ z (* -1 cr (cos A) (sin B)) (* -1 tr (sin B)))))))

(defun add-torus (x y z cr tr)
  (let ((n *max-u-step*)
        (torus (generate-torus x y z cr tr)))
    (loop
       for i from 0 to (- (matrix-dimension torus 1) n 2)
       do
         (let ((triangle1 (list i (+ i 1) (+ i n 1)))
               (triangle2 (list i (+ i n 1) (+ i n))))
           (apply 'add-triangle
                  (loop
                     for vertex in triangle1
                     collect (matrix-get-column torus vertex)))
           (apply 'add-triangle
                  (loop
                     for vertex in triangle2
                     collect (matrix-get-column torus vertex)))))))

(defun add-edges (matrix)
  (matrix-append *edge-matrix* matrix))

(defun clear-edge-matrix ()
  (setf *edge-matrix* (make-matrix :dimensions '(4 0)))
  nil)

(defun clear-triangle-matrix ()
  (setf *triangle-matrix* (make-matrix :dimensions '(4 0)))
  nil)

(defun clear-screen ()
  (setf *screen* (make-array (list *x-resolution* *y-resolution*)
                             :initial-element *background-color*))
  nil)

(defun draw-triangles (&key (matrix *triangle-matrix*) color-function)
  (loop
     for i from 0 to (- (matrix-dimension matrix 1) 1) by 3
     do
       (let* ((point1 (matrix-get-column matrix i))
              (point2 (matrix-get-column matrix (+ i 1)))
              (point3 (matrix-get-column matrix (+ i 2)))
              (normal (triangle-normal point1 point2 point3)))
         (when (> (aref normal 2) 0)
           (draw-triangle point1 point2 point3)))))

(defun draw-triangle (point1 point2 point3)
  (draw-line point1 point2)
  (draw-line point2 point3)
  (draw-line point3 point1))       
       
(defun draw-lines (&key (matrix *edge-matrix*) color-function)
  (loop
     for i from 0 to (- (matrix-dimension matrix 1) 1) by 2
     do
       (let ((point1 (matrix-get-column matrix i))
             (point2 (matrix-get-column matrix (+ i 1))))
         (draw-line point1 point2 :color-function color-function))))

(defun draw-line (point1 point2 &key (color '(0 255 0)) color-function)
  (let ((x1 (aref point1 0))
        (y1 (aref point1 1))
        (z1 (aref point1 2))
        (x2 (aref point2 0))
        (y2 (aref point2 1))
        (z2 (aref point2 2))
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
             (setf d (+ (* A 2) B))
             (loop
                for x from x1 to x2
                do
                  (plot-pixel x y (funcall get-color x y))
                  (when (> d 0)
                    (incf y)
                    (incf d (* B 2)))
                  (incf d (* A 2))))
            ((>= dy dx) ; octant 2
             (setf d (+ A (* B 2)))
             (loop
                for y from y1 to y2
                do
                  (plot-pixel x y (funcall get-color x y))
                  (when (< d 0)
                    (incf x)
                    (incf d (* A 2)))
                  (incf d (* B 2))))
            ((and (< dy 0) (<= (* -1 dy) dx))
             (setf d (- (* A 2) B))
             (loop
                for x from x1 to x2
                do
                  (plot-pixel x y (funcall get-color x y))
                  (when (< d 0)
                    (decf y)
                    (decf d (* B 2)))
                  (incf d (* A 2))))
            ((> (* -1 dy) dx)
             (setf d (- A (* B 2)))
             (loop
                for y from y1 downto y2
                do
                  (plot-pixel x y (funcall get-color x y))
                  (when (> d 0)
                    (incf x)
                    (incf d (* A 2)))
                  (decf d (* B 2)))))
          (draw-line point2 point1
                     :color color
                     :color-function color-function)))))



