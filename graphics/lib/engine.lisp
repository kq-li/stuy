(load 'util)
(load 'matrix)
(load 'draw)
(load 'display)
(load 'mdl)

(in-package :graphics)

(defvar *edge-matrix* (make-matrix :dimensions '(4 0)))

(defvar *triangle-matrix* (make-matrix :dimensions '(4 0)))

(defvar *coordinate-systems* (list (make-identity-matrix)))

(defvar *transformation-matrix* (make-identity-matrix))

(defvar *x-resolution* 500)

(defvar *y-resolution* 500)

(defvar *background-color* '(0 0 0))

(defvar *screen* (make-array (list *x-resolution* *y-resolution*)
                             :initial-element *background-color*))
