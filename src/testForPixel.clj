(ns testForPixel
  (:require [quil.core :as q]
            [Borders :refer [border]]))

(q/defsketch testForPixel
  :title "Fill Specific Cells in Grid"
  :size [300 300] ; 300x300 pixels
  :setup (fn []
           (q/background 255) ; Set the background to white
           (let [grid-size 31         ; Number of cells (30x30)
                 cell-size (/ 300 grid-size) ; Size of each cell (10 pixels)
                 target-cells (border 30 30)] ; Set of [row, col] to fill
                 
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
