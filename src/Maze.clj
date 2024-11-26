(ns Maze
  (:require [Obstacles :refer [generate-wall empty-space OBS]]))

(def problem {:Initial (rand-nth (seq (empty-space 30 30)))
              :End (rand-nth (seq (empty-space 30 30)))})

(problem :Initial)
(problem :End)


(defn possible-actions 
  "Obstacles are hashset of coordinate pairs, and state is a coordinate pair"
  [obstacles state] 
  (if (contains? OBS (problem: Initial))
    (remove))
  )

;; Do I 保存 the obstacles???

(OBS)