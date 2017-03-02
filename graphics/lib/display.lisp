(defun init-image (xres yres &optional (initial-color '(255 255 255)))
  (make-array (list xres yres) :initial-element initial-color)) 

(defun plot-pixel (screen x y color)
  (when (and (and (>= x 0)
                  (< x (array-dimension screen 0)))
             (and (>= y 0)
                  (< y (array-dimension screen 1))))
    (setf (aref screen y x) color)))

(defun create-ppm (filename screen)
  (with-open-file (stream (concatenate 'string filename ".ppm")
                          :direction :output
                          :if-exists :supersede
                          :if-does-not-exist :create)
    (format stream "P3 ~{~d ~d ~} 255~%" (array-dimensions screen))
    (let ((xres (first (array-dimensions screen)))
          (yres (second (array-dimensions screen))))
      (dotimes (x xres)
        (dotimes (y yres)
          (format stream "~{~d ~d ~d ~}" (aref screen x y)))
        (format stream "~%")))))

(defun create-png (filename screen)
  (create-ppm filename screen)
  (run-shell-command (format nil "convert ~a.ppm ~a.png" filename filename)))

(defun display (filename)
  (let ((ppm-filename (concatenate 'string filename ".ppm"))
        (png-filename (concatenate 'string filename ".png")))
    (cond
      ((probe-file png-filename)
       (run-shell-command (concatenate 'string "display " png-filename)))
      ((probe-file ppm-filename)
       (run-shell-command (concatenate 'string "display " ppm-filename)))
      (t
       (format t "Cannot display image!~%")))))
