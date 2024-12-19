(ns test
  "Seperating some test cases to see if it works"
  (:require [Maze :refer [test-problem]]
            [Search :refer :all]))

(extract-solution (Search-Algorithm test-problem) ())



 (EXPAND test-problem 
         {:state [1 1]
          :parent nil
          :action nil})

 (EXPAND test-problem 
         {:state [1 3]
          :parent {:action :S, :parent {:action nil, :parent nil, :state [1 1]}, :state [1 2]}
          :action :S})


;(apply vector (cons [5 5] [[1 2] [2 4]]))
;(pop (apply vector (cons [5 5] [[1 2] [2 4]])))