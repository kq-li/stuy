(defun square (x)
  (* x x))

(defun distance (x1 y1 x2 y2)
  (sqrt (+ (square (- x2 x1)) (square (- y2 y1)))))

(defun bound (x bound1 bound2)
  (if (< bound1 bound2)
      (max (min x bound2) bound1)
      (max (min x bound1) bound2)))

