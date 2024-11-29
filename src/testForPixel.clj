(ns testForPixel
  (:require [quil.core :as q]
            [Borders :refer [border]]
            [Obstacles :refer [generate-wall]]))

(q/defsketch testForPixel
  :title "Fill Specific Cells in Grid"
  :size [300 300] ; 300x300 pixels
  :setup (fn []
           (q/background 255) ; Set the background to white
           (let [grid-size 31         ; Number of cells (30x30)
                 cell-size (/ 300 grid-size) ; Size of each cell (10 pixels)
                 target-cells (into (border 30 30) #{[1 16]
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
                                                     [15 4]
                                                     })] ; Set of [row, col] to fill
                 
                 ; Set grid line color to light gray
             (q/stroke 0 255 0)
             (q/stroke-weight 1)

             ; Draw the grid and fill target cells
             (dotimes [row grid-size]
               (dotimes [col grid-size]
                 (let [x (* col cell-size) ; Top-left X of the cell
                       y (* row cell-size)] ; Top-left Y of the cell
                   ; Check if the current cell is a target cell
                   (if (contains? target-cells [row col])
                     (q/fill 0 255 0) ; Fill with green if it's a target cell
                     (q/fill 0)) ; Fill with black otherwise 
                   (q/rect x y cell-size cell-size)))))))
