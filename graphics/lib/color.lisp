(load 'util)

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

