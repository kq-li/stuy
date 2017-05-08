(in-package :graphics)

(defun square (x)
  (* x x))

(defun distance (x1 y1 x2 y2)
  (sqrt (+ (square (- x2 x1)) (square (- y2 y1)))))

(defun bound (x bound1 bound2)
  (if (< bound1 bound2)
      (max (min x bound2) bound1)
      (max (min x bound1) bound2)))

(defun split-string (string delimiter)
  (loop
     for beg = 0 then (+ end 1)
     for end = (position delimiter string :start beg)
     then (position delimiter string :start (+ end 1))
     while beg
     collect (subseq string beg end)
     while end))

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

