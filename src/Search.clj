
(ns Search
  
  )

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
     [node (first frontier)
      children (EXPAND problem node)
      solutions (filter
                 (fn [child] (= (:state child) (:GOAL problem)))
                 children)
      nreached (into reached (map :state children)) ;; keyword :state as a function  
                ;; might put a function here to draw the reached states
      nfrontier (into (vec (rest frontier)) (remove (fn [child] (reached (:state child))) children))]
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
      frontier (vector initial-node) ;; we store nodes because nodes are EXPANDed
      reached #{(:INITIAL problem)}]
      (first (drop-while vector? (iterate searchStep [problem frontier reached])))))
    
(defn extract-solution [node solution]
  (if node
    (extract-solution (:parent node) (conj solution (:state node)))
    solution))

