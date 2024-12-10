
(ns Search
  (:require [Maze :refer [test-problem]])
  )

;; (border 30 30) で 0と30に壁がある、31x31のpixel (obs will range from 1 to 29)

#{[1 6] [1 15]
  [2 2] [2 3] [2 4] [2 5] [2 9] [2 10] [2 11] [2 15] [2 16] [2 19] [2 22] [2 27] [2 28]
  [3 4] [3 7] [3 15] [3 18] [3 22]
  [4 4] [4 7] [4 18] [4 20] [4 21] [4 22] [4 23]
  [5 3] [5 4] [5 7] [5 8] [5 9] [5 10] [5 11] [5 12] [5 13] [5 20] [5 28] [5 29]
  [6 3] [6 10] [6 16] [6 19][6 20]
  [7 10] [7 13] [7 14] [7 15] [7 16] [7 19]
  [8 1] [8 2] [8 5] [8 6] [8 7] [8 8] [8 9] [8 10] [8 16] [8 17] [8 18] [8 19]
  [9 5] [9 13] [9 19]
  [10 3] [10 4] [10 5] [10 13] [10 18] [10 19] [10 20]
  [11 9] [11 13] [11 14] [11 15]
  [12 1] [12 2] [12 4] [12 5] [12 13]
  [13 4]
  [14 4]
  [15 4] }

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

(defn EXPAND
  "takes the problem and node of the current state, and returns a collection of NODES (hash-map), not STATE!!!!"
  [problem, node]
  (let [s (:state node)
        poss-actions ((:ACTIONS problem) s)]
    ;(println s)
    (for [action poss-actions]
      {:state ((:RESULTS problem) s action)
       :parent node
       :action action})))

(defn searchStep [[problem frontier reached]] ;; reached here is a lookup table, so we'll do a hash-set of vectors!! (>> using hash-set as a function, to look up state coordinates) ] 
  (if (empty? frontier)
    nil
    (let
     [node (peek frontier)
      children (EXPAND problem node)
      solutions (filter
                 (fn [child] (= (:state child) (:GOAL problem)))
                 children)
      nreached (into reached (map :state children)) ;; keyword :state as a function  
                ;; might put a function here to draw the reached states
      nfrontier (into (pop frontier) (remove (fn [child] (reached (:state child))) children))]
      (if (not (empty? solutions))
        (first solutions)
        [problem nfrontier nreached])))) ;; returns to the start of the loop with new input 
  

  (defn Search-Algorithm
    "receives the problem, and returns a solution node or a failure"
    [problem] 

    (let
     [initial-node
      {:state (:INITIAL problem)
       :parent nil
       :action nil}
      frontier [initial-node] ;; we store nodes because nodes are EXPANDed
      reached #{(:INITIAL problem)}]
      (first (drop-while vector? (iterate searchStep [problem frontier reached])))))
    
(defn extract-solution [node solution]
  (if node
    (extract-solution (:parent node) (conj solution (:state node)))
    solution))



(extract-solution (Search-Algorithm test-problem) ())



(EXPAND test-problem 
        {:state [1 1]
         :parent nil
         :action nil})

(EXPAND test-problem 
        {:state [1 3]
         :parent {:action :S, :parent {:action nil, :parent nil, :state [1 1]}, :state [1 2]}
         :action :S})


;(apply vector (cons [5 5] [[1 2] [2 4]]))
;(pop (apply vector (cons [5 5] [[1 2] [2 4]])))

(conj [[1 2] [2 4]] [5 5])
(apply vector (pop (apply list (conj [[1 2] [2 4]] [5 5]))))

(peek (apply list [[1 2] [2 4] [5 5]]))
(pop (apply list [[1 2] [2 4] [5 5]]))

;(empty? '())
(empty? [])

