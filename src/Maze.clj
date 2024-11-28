(ns Maze
  "Possible actions are [:N :E :S :W]"
  (:require [Obstacles :refer [generate-wall empty-space OBS]]))


(defn check? [obstacles state](into #{} (filter (fn [element] (= (first element) (inc (first state)))) obstacles)))




(defn possible-actions 
  "Obstacles are hashset of coordinate pairs, and state is a coordinate pair"
  [obstacles state] 
  (let [PosA []]
    )
  )

(def maze1 (generate-wall 5 5))

(defn take-action
  "Search Algorithm gives a state and a possible action"
  [state action]
  (if (= action :N)
    )
  )

;; partial allows us to make it into one input

;; initial と endが　wallにないようにgenerate

(def problem {:Initial (rand-nth (seq (empty-space 30 30)))
              :Goal (rand-nth (seq (empty-space 30 30)))
              :Actions (partial possible-actions maze1)
              :Results take-action})

(def hundred-times (partial * 100 10))
(hundred-times 4)

(:Initial problem)
(:Goal problem)
((:Actions problem) [1 1])

