;;(defoverload distance
  ;;((point1 point2)
     ;;(distance (point-x point1) (point-y point1)
               ;;(point-x point2) (point-y point2)))
  ;;((x1 x2 y1 y2)
   ;;(sqrt (+ (square (- x2 x1)) (square (- y2 y1))))))

(defmacro defoverload (name overload-list)
  ;;(format t "(defun ~d (&body body)~%" name)
  ;;(format t "  (cond~%")
  ;;(loop
     ;;for pair in overload-list
     ;;do
       ;;(format t "    ((= (length body) ~a)~%" (length (first pair)))
       ;;(format t "     ~a)~%" (second pair)))
  ;;(format t "))~%")
  ;;`(defun ,name (x y)
     ;;(+ x y)))
  `(defun ,name (&rest body)
     (cond
       ,@(loop
            for (lambda-list . forms) in overload-list
            collect
              `((= (length body) ,(length lambda-list))
                (let* ,(loop
                          for argument in lambda-list
                          for i from 0
                          collect `(,argument (nth ,i body)))
                  ,@forms)))
       (t (error "Lambda list does not match!")))))

(defun check-argument-types (arguments types)
  (format t "~a: ~a, ~a: ~a~%" arguments (length arguments) types (length types))
  (if (= (length arguments) (length types))
    (loop
       for argument in arguments
       for type in types

       unless (typep argument type)
       do (format t "test~%")
       and return nil
       
       finally (return t))
    nil))

(defmacro defoverload-typed (name overload-list)
  `(defun ,name (&rest body)
     (cond
       ,@(loop
            for (lambda-list . forms) in overload-list
            collect
              `((check-argument-types body
                                      (quote ,(mapcar (lambda (pair) (second pair))
                                                      lambda-list)))
                (let* ,(loop
                          for (argument-name argument-type) in lambda-list
                          for i from 0
                          collect `(,argument-name (nth ,i body)))
                  (format t "~a~%" ',lambda-list)
                  ,@forms)))
       (t (error "Lambda list does not match!")))))

;;(defoverload-typed exponent
    ;;((((b number) (p number))
     ;;(expt b p))
     ;;(((p number))
      ;;(exp p))))
