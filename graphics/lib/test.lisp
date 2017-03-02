(load 'init)

(defmacro matrix-test (description operation)
  `(progn
    (format t ,description)
    (terpri)
    (matrix-print ,operation)
    (terpri)))

(let ((matrix1 (make-matrix :initial-contents '((0 1 2) (3 4 5))))
      (matrix2 (make-matrix :initial-contents '((8 7 6) (5 4 3) (2 1 0))))
      (identity2 (make-identity-matrix 2))
      (identity3 (make-identity-matrix 3)))
  (matrix-test "A:" matrix1)
  (matrix-test "B:" matrix2)
  (matrix-test "I2:" identity2)
  (matrix-test "I3:" identity3)
  (matrix-test "5 * A:" (matrix-scalar-mult matrix1 5))
  (matrix-test "A * B:" (matrix-matrix-mult matrix1 matrix2))
  (matrix-test "I2 * A:" (matrix-matrix-mult identity2 matrix1))
  (matrix-test "A * I3:" (matrix-matrix-mult matrix1 identity3)))

(let* ((xres 500)
       (yres 500)
       (screen (init-image xres yres '(255 255 255)))
       (edge-matrix (make-matrix :dimensions '(4 0)))
       (filename "image"))
  (loop
     for x from 0 to (- xres 1) by 10
     do
       (let ((point1 (make-point :initial-list (list 0 0 0)))
             (point2 (make-point :initial-list (list x (+ (/ yres 2) (* 5 (sqrt x))) 0)))
             (point3 (make-point :initial-list (list x (- (/ yres 2) (* 5 (sqrt x))) 0))))
         (add-edge-to-matrix edge-matrix point1 point2)
         (add-edge-to-matrix edge-matrix point1 point3)))
  (draw-lines-from-matrix screen edge-matrix)
  (create-png filename screen)
  (display filename))
