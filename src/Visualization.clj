(ns Visualization
  (:require [quil.core :as q] 
            [quil.middleware :as m]
            [Borders :refer [border]] 
            [Obstacles :refer [generate-wall]]
            [Search :refer [searchStep extract-solution]]
            [Maze :refer [possible-actions take-action]]
            ))

(def width 800)
(def height 800)


(defn csetup []
  (let [borders (into (border 30 30) #{[1 6] [1 15]
                                       [2 2] [2 3] [2 4] [2 5] [2 9] [2 10] [2 11] [2 15] [2 16] [2 19] [2 22] [2 26] [2 27] [2 28]
                                       [3 4] [3 7] [3 15] [3 18] [3 22] [3 26]
                                       [4 4] [4 7] [4 18] [4 20] [4 21] [4 22] [4 23]
                                       [5 3] [5 4] [5 7] [5 8] [5 9] [5 10] [5 11] [5 12] [5 13] [5 20] [5 28] [5 29]
                                       [6 3] [6 10] [6 16] [6 19] [6 20] [6 24] [6 25]
                                       [7 10] [7 13] [7 14] [7 15] [7 16] [7 19] [7 22] [7 25]
                                       [8 1] [8 2] [8 5] [8 6] [8 7] [8 8] [8 9] [8 10] [8 16] [8 17] [8 18] [8 19] [8 21] [8 22] [8 25] [8 28]
                                       [9 5] [9 13] [9 19] [9 24] [9 25]
                                       [10 3] [10 4] [10 5] [10 13] [10 18] [10 19] [10 20] [10 23] [10 24]
                                       [11 9] [11 13] [11 14] [11 15] [11 23] [11 27]
                                       [12 1] [12 2] [12 4] [12 5] [12 9] [12 10] [12 11] [12 12] [12 13] [12 23] [12 26] [12 27]
                                       [13 4] [13 9] [13 18] [13 19] [13 20] [13 22] [13 23] [13 24] [13 25] [13 26]
                                       [14 4] [14 7] [14 8] [14 9] [14 19] [14 23]
                                       [15 1] [15 2] [15 3] [15 4] [15 11] [15 12] [15 15] [15 16] [15 17] [15 18] [15 19] [15 27] [15 28] [15 29]
                                       [16 12] [16 28]
                                       [17 7] [17 12] [17 13] [17 14] [17 15] [17 22] [17 23] [17 24] [17 25] [17 26] [17 27] [17 28]
                                       [18 2] [18 3] [18 4] [18 5] [18 6] [18 7] [18 8] [18 9] [18 19]
                                       [19 7] [19 16] [19 17] [19 18] [19 19] [19 25] [19 26]
                                       [20 7] [20 8] [20 9] [20 10] [20 11] [20 17] [20 22] [20 23]
                                       [21 4] [21 5] [21 6] [21 7] [21 14] [21 15] [21 16] [21 17] [21 23] [21 27]
                                       [22 14] [22 23] [22 26] [22 27]
                                       [23 11] [23 14] [23 19] [23 22] [23 23] [23 24] [23 25] [23 26]
                                       [24 2] [24 6] [24 7] [24 8] [24 9] [24 10] [24 11] [24 12] [24 13] [24 14] [24 17] [24 18] [24 19] [24 20] [24 21] [24 22]
                                       [25 2] [25 11] [25 22] [25 27] [25 28] [25 29]
                                       [26 2] [26 8] [26 11] [26 22]  [26 28]
                                       [27 2] [27 3] [27 4] [27 5] [27 6] [27 8] [27 11] [27 12] [27 13] [27 18] [27 19] [27 20] [27 25] [27 26] [27 27] [27 28]
                                       [28 6] [28 20]
                                       [29 20] [29 23]})
        problem {:INITIAL [1 1]
                 :GOAL [28 28]
                 :ACTIONS (partial possible-actions borders)
                 :RESULTS take-action}
        initial-node {:state (:INITIAL problem)
                      :parent nil
                      :action nil}
        frontier [initial-node] ;; we store nodes because nodes are EXPANDed
        reached #{(:INITIAL problem)}
        ]
    (q/frame-rate 20)
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
     :else (conj cstate [:solution (extract-solution nstate ())])
      )
    ))


(defn draw-cstate [cstate]
  (q/background 0)
  (let [grid-size 31
        cell-size (/ width grid-size)
        obstacle-cells (:borders cstate) ;; into obstacles here
        frontier (:frontier cstate)
        reached (:reached cstate)
        solution (:solution cstate)
        problem (:problem cstate)]  
    
    (q/stroke 0 255 0)
    (q/stroke-weight 1) 
  
    ;; drawing reached cells
    (q/fill 255 255 0)
    (doseq [coord reached]
      (q/rect (* (first coord) cell-size) (* cell-size (second coord)) cell-size cell-size))
    
    ;; drawing obstacles
    (q/fill 0 255 0)
    (doseq [coord obstacle-cells]
      (q/rect (* (first coord) cell-size) (* cell-size (second coord)) cell-size cell-size))
    
    
    ;; drawing frontier
    (q/fill 173 216 230)
    (doseq [coord (map :state frontier)]
      (q/rect (* (first coord) cell-size) (* cell-size (second coord)) cell-size cell-size))
    
    
    ;; drawing reached cells
    (when (contains? cstate :solution) 
      (do (q/fill 255 0 0)
          (doseq [coord solution]
            (q/rect (* (first coord) cell-size) (* cell-size (second coord)) cell-size cell-size))
          )) 
     
    (q/fill 0 0 255)
    (let [coord (:INITIAL problem)]
      (q/rect (* (first coord) cell-size) (* cell-size (second coord)) cell-size cell-size))

    (q/fill 255 0 255)
    (let [coord (:GOAL problem)] 
      (q/rect (* (first coord) cell-size) (* cell-size (second coord)) cell-size cell-size))
    ))
    
    ;; (
     
    ;; ;;  (  (contains? obstacle-cells [row col]) (q/fill 0 255 0) ; Fill with green if it's a target cell 
    ;; ;;     (contains? reached [row col]) (q/fill 255 255 0) 
    ;; ;;     (contains? (into #{} frontier) [row col]) (q/fill 173 216 230)  
    ;; ;;     (contains? (into #{} solution) [row col]) (q/fill 255 192 203)
    ;; ;;          ) ; Fill with black otherwise  
    ;;  (q/rect x y cell-size cell-size))))

(defn csetup2 []
  (let [borders (into (border 30 30) (generate-wall 30 30))
        problem {:INITIAL [1 1]
                 :GOAL [28 28]
                 :ACTIONS (partial possible-actions borders)
                 :RESULTS take-action}
        initial-node {:state (:INITIAL problem)
                      :parent nil
                      :action nil}
        frontier [initial-node] ;; we store nodes because nodes are EXPANDed
        reached #{(:INITIAL problem)}]
    (q/frame-rate 20)
    {:frontier frontier
     :reached reached
     :borders borders
     :problem problem}))

(q/defsketch Visualization
  :title "Fill Specific Cells in Grid"
  :size [width height] ; 800x800 pixels
  :setup csetup 
  :draw draw-cstate
  :update update-cstate
  :middleware [m/fun-mode])

