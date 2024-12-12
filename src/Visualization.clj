(ns Visualization
  (:require [quil.core :as q] 
            [Borders :refer [border]] 
            [Obstacles :refer [generate-wall]]
            [Search :refer [searchStep extract-solution]]
            [Maze :refer [possible-actions take-action]]))

(def width 800)
(def height 800)

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
  (q/background 255)
  (let [grid-size 31
        cell-size (/ width grid-size)
        obstacle-cells (:borders cstate) ;; into obstacles here
        frontier (:frontier cstate)
        reached (:reached cstate)
        solution (:solution cstate)]  
    
    (q/stroke 0 255 0)
      (q/stroke-weight 1)
    
                 ; Draw the grid and fill target cells
      (dotimes [row grid-size]
        (dotimes [col grid-size]
          (let [x (* col cell-size) ; Top-left X of the cell
                y (* row cell-size)] ; Top-left Y of the cell
                       ; Check if the current cell is a target cell
            (if (= solution nil) ;;直す (if (solution)) でもいいかな？
              (cond 
                (contains? obstacle-cells [row col]) (q/fill 0 255 0) ; Fill with green if it's a target cell
                (contains? reached [row col]) (q/fill 255 255 0)
                (contains? (into #{} frontier) [row col]) (q/fill 173 216 230)
                :else (q/fill 0))
              (cond 
                (contains? obstacle-cells [row col]) (q/fill 0 255 0)
                (contains? (into #{} solution) [row col]) (q/fill 255 192 203)
                :else (q/fill 0))
              ) ; Fill with black otherwise  
            (q/rect x y cell-size cell-size)))))
    )

(q/defsketch Visualization
  :title "Fill Specific Cells in Grid"
  :size [width height] ; 300x300 pixels
  :setup csetup
  :draw draw-cstate
  :update update-cstate)

