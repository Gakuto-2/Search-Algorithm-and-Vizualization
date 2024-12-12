
(ns Search
  (:require [Maze :refer [test-problem]])
  )

;; (border 30 30) で 0と30に壁がある、31x31のpixel (obs will range from 1 to 29)

;________________________________________________________________________________________________________________________________________________________________

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
      frontier (list initial-node) ;; we store nodes because nodes are EXPANDed
      reached #{(:INITIAL problem)}]
      (first (drop-while vector? (iterate searchStep [problem frontier reached])))))
    
(defn extract-solution [node solution]
  (if node
    (extract-solution (:parent node) (conj solution (:state node)))
    solution))



;(extract-solution (Search-Algorithm test-problem) ())



;; (EXPAND test-problem 
;;         {:state [1 1]
;;          :parent nil
;;          :action nil})

;; (EXPAND test-problem 
;;         {:state [1 3]
;;          :parent {:action :S, :parent {:action nil, :parent nil, :state [1 1]}, :state [1 2]}
;;          :action :S})


;(apply vector (cons [5 5] [[1 2] [2 4]]))
;(pop (apply vector (cons [5 5] [[1 2] [2 4]])))

