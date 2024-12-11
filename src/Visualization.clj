(ns Visualization
  (:require [quil.core :as q] 
            [Borders :refer [border]] 
            [Obstacles :refer [generate-wall]]
            [Search :refer [searchStep extract-solution]]
            [Maze :refer [possible-actions take-action]]))

(def width 300)
(def height 300)

(defn csetup []
  (let [borders (border 30 30)
        problem {:INITIAL [1 1]
                 :GOAL [3 3]
                 :ACTIONS (partial possible-actions borders)
                 :RESULTS take-action}
        initial-node {:state (:INITIAL problem)
                      :parent nil
                      :action nil}
        frontier [initial-node] ;; we store nodes because nodes are EXPANDed
        reached #{(:INITIAL problem)}
        ]
    {:frontier frontier
     :reached reached
     :borders borders
     :problem problem}))

(defn update-cstate [cstate]
  (let [frontier (:frontier cstate)
        reached  (:reached cstate)
        problem (:problem cstate)
        nstate (searchStep [problem frontier reached])]
    (cond 
      (nil? nstate) cstate
      (vector? nstate) (into cstate [[:frontier (second nstate)] [:reached (nth nstate 2)]])
     :else (:solution (extract-solution nstate ())))
    ))

(defn draw-cstate [cstate]
  )

(q/defsketch Visualization
  :title "Fill Specific Cells in Grid"
  :size [width height] ; 300x300 pixels
  :setup csetup
  :draw draw-cstate
  :update update-cstate)

