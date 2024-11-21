(ns mini.finalproject.test-for-pixel2
  (:require [quil.core :as q]))

(q/defsketch example
  :title "Fill Specific Cells in Grid"
  :size [300 300] ; 300x300 pixels
  :setup (fn []
           (q/background 255) ; Set the background to white
           (let [grid-size 30          ; Number of cells (30x30)
                 cell-size (/ 300 grid-size) ; Size of each cell (10 pixels)
                 target-cells #{[10 15] [20 25]}] ; Set of [row, col] to fill

             ; Draw the grid and fill target cells
             (dotimes [row grid-size]
               (dotimes [col grid-size]
                 (let [x (* col cell-size) ; Top-left X of the cell
                       y (* row cell-size)] ; Top-left Y of the cell
                   ; Check if the current cell is a target cell
                   (if (target-cells [row col])
                     (q/fill 255 255 0) ; Fill with black if it's a target cell
                     (q/fill 255)) ; Fill with white otherwise 
                   (q/rect x y cell-size cell-size)))))))


