
(ns Search
  (:require [Maze :refer [problem]])
  )

#{[1 16]
  [2 2] [2 3] [2 4] [2 5] [2 9] [2 10] [2 11] [2 12] [2 16] [2 22] [2 27] [2 28]
  [3 4] [3 16] [3 22]
  [4 4] [4 7] [4 20] [4 21] [4 22] [4 23]
  [5 4] [5 3] [5 7] [5 8] [5 9] [5 10] [5 11] [5 12] [5 13] [5 28] [5 29]
  [6 10] [6 16]
  [7 10] [7 13] [7 14] [7 15] [7 16]
  [8 1] [8 2] [8 5] [8 6] [8 7] [8 8] [8 9] [8 10] [8 16]
  [9 5]
  [10 3] [10 4] [10 5]
  [11 9] [11 13] [11 14] [11 15]
  [12 1] [12 2] [12 4] [12 5] [12 13]
  [13 4]
  [14 4]
  [15 4]}

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


;______________________________________________________________________________________________________________________________________________________________________


(defn Search-Algorithm
  "receives the problem, and returns a solution node or a failure"
  [problem] 

  (def node
    {:state (:Initial problem)
     :parent ()
     :action ()})

  (let [frontier [(:Initial problem) ]] 
    (let [reached [(:Initial problem) ]]
      (while (not (empty? frontier))
        (def (:state (pop frontier)))
        
        ) ;; change node
      )
    ) 
  )


(def node 
  {:state (:Initial problem)
   :parent ()
   :action ()})

