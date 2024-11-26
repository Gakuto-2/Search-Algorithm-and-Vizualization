
(ns Search)

;______________________________________________________________________________________________________________________________________________________________________
;; The queue

;;; IS-EMPTY
(empty? #{})
(empty? #{[2 3] [4 5]})

;;; TOP
(first [[1 1] [2 2] [3 3]])

;;; POP there is a pop function
(vec (rest [[1 1] [2 2] [3 3]]))

;;; ADD
(conj [[1 1] [2 2] [3 3]] [0 0])
(vec (cons [0 0] [[1 1] [2 2] [3 3]]))
;;;; 
; for every i in (count [coll]) --> USE RECURSION!!! with FIRST [coll]
; if node.cost-path(first [coll]) < node.cost-path(s) then (vec (cons [s] [coll]))



;; HOW DO WE DEFINE node.cost-path??
; calculate cost of the parent when we generate nodes?


;______________________________________________________________________________________________________________________________________________________________________
;; a node contains 4 types of information: 
;; node.state
;; 

:goal?