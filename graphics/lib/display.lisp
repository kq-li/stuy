(defun init-image (&optional (initial-color '(255 255 255)))
  (make-array (list *x-resolution* *y-resolution*) :initial-element initial-color)) 

(defun plot-pixel (x y color)
  (let ((x (round x))
        (y (round y)))
    (when (and (and (>= x 0)
                    (< x *x-resolution*))
               (and (>= y 0)
                    (< y *y-resolution*)))
      (setf (aref *screen* (- *y-resolution* y 1) x) color))))

(defun create-ppm-data ()
  (with-output-to-string (string)
    (format string "P3 ~d ~d 255 ~%" *x-resolution* *y-resolution*)
    (dotimes (x *x-resolution*)
      (dotimes (y *y-resolution*)
        (format string "~{~d ~d ~d ~}" (aref *screen* x y)))
      (format string "~%"))))

(defun create-ppm (filename)
  (with-open-file (stream (concatenate 'string filename ".ppm")
                          :direction :output
                          :if-exists :supersede
                          :if-does-not-exist :create)
    (format stream "~a" (create-ppm-data))))

(defun create-png (filename)
  (create-ppm filename)
  (run-shell-command (format nil "convert ~a.ppm ~a.png" filename filename)))

(defun create (filename)
  (let* ((position (position #\. filename :from-end t))
         (prefix (if position (subseq filename 0 position) filename))
         (extension (if position (subseq filename position) nil)))
    (create-ppm prefix)
    (when (and extension
               (not (string= extension ".ppm")))
      (run-shell-command (format nil "convert ~a.ppm ~a" prefix filename)))))

(defun display ()
  (create "tmp.ppm")
  (run-shell-command "display tmp.ppm")
  (run-shell-command "rm tmp.ppm"))
  ;;(format t "~a~%" (run-shell-command (concatenate 'string "echo \"" (create-ppm-data) "\" | display"))))

