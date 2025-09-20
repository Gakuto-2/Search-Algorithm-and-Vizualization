(ns Maze
  "Possible actions are [:N :E :S :W]"
  (:require [Obstacles :refer [generate-wall empty-space]] 
            [Borders :refer [border]]))


(defn check? [obstacles state](into #{} (filter (fn [element] (= (first element) (inc (first state)))) obstacles)))

(defn toNorth
  [obs [x y]]
  (when-not (obs [x (dec y)])
    :N))

(toNorth #{[1 1]} [1 2])

(defn toWest
  [obs [x y]]
    (when-not (obs [(dec x) y]) 
      :W))

(defn toSouth
  [obs [x y]]
  (when-not (obs [x (inc y)])
     :S))

(defn toEast
  [obs [x y]]
  (when-not (obs [(inc x) y]) 
    :E))

(defn possible-actions 
  "Obstacles are hashset of coordinate pairs, and state is a coordinate pair"
  [obstacles state] 
  (remove nil?
    (map (fn [f] (f obstacles state)) [toNorth toEast toSouth toWest])) 
  )

(possible-actions #{} [2 3])
(possible-actions #{[3 3] [2 4]} [2 3])
(def maze1 (generate-wall 5 5))



(defn take-action
  "Gives a state and an action, and receives the result state of that action"
  [ [x y] action]
  (cond 
    (= action :N) [x (dec y)]
    (= action :S) [x (inc y)] 
    (= action :E) [(inc x) y]
    (= action :W) [(dec x) y]
    ) 
  )

(take-action [1 2] :N)
(take-action [1 2] :S)
(take-action [1 2] :W)
(take-action [1 2] :E)

;; partial allows us to make it into one input

;; initial と endが　wallにないようにgenerate

(def test-problem
  (let [borders (border 5 5)]
    {:INITIAL [1 1]
     :GOAL [3 3] 
     :ACTIONS (partial possible-actions borders) 
     :RESULTS take-action}))

(:INITIAL test-problem)
(:GOAL test-problem)
((:ACTIONS test-problem) [1 1])
((:RESULTS test-problem) [1 1] :E)


(def problem {:INITIAL (rand-nth (seq (empty-space 30 30)))
              :GOAL (rand-nth (seq (empty-space 30 30)))
              :ACTIONS (partial possible-actions maze1)
              :RESULTS take-action})

(def hundred-times (partial * 100 10))
(hundred-times 4)

(:INITIAL problem)
(:GOAL problem)
((:ACTIONS problem) [1 1])
((:RESULTS problem) [1 1] :N)


;; map
#{[]}