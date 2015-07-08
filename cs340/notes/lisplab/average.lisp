; average.lisp
; compute the average of a list of numbers
; no input error checks

(defun average (numList)
  (/ (apply '+ numList) (length numList)))
(defun average2 (numList)
  (loop for e in numList
	count e into length
	sum e into sum
	finally (return (/ sum length))))
(defun power (x y )
  (if (= y 0) 1
    (* x (power x (1- y)))))