(ns Obstacles)

; brainstorm

(rand-int 3)
;; Returns a random integer between 0 (inclusive) and n (exclusive).
;; Use this to generate random starting points, if we are going to make them walls.

;; We can also generate the ending point (of the wall generation) by either having the x coordinates or y coordinates fixed, and the other is determined by the rand-int
;  function

;___________________________________________________________________________________

;; removing the starting & end point disjoin from the obstacle hashset
;; seq is used to make it into a list

(rand-nth (seq #{[1 2] [6 9] [2 5]})) ;hashset has no order
(rand-nth [1 2 3 4])

(first #{[1 2] [6 9] [2 5]})
;; set membership

(conj #{[1 2]} [(rand-int 30) (rand-int 30)])

;; how you remove an element from a hashset
(disj #{[1 2] [3 4]} [1 2])


;__________________________________________________________________________________________

;; CODING
; we have the borders hashset
(defn empty-space
  "fn that keeps track of the options of generating the start node"
  [x y]
  (let [coords 
        (for [i (range (dec x)) 
              j (range (dec y))]
          [(inc i) (inc j)])
        ]
    (into #{} coords))
  )

(empty-space 3 3)
(def trackempty (seq (empty-space 3 3)))


(rand-nth (seq (empty-space 3 3)))

(defn choose-node
  "generating the start node, given the limit for input (we expect 30 30)"
  [x y]
  (let [options trackempty]
  (rand-nth options)))
(choose-node 3 3)

; (disj #{[2 3] [4 5] [1 1]} [1 1])
;     (disj trackempty start)



(defn end-node
  [x y] 
  (let [hz (rand-int 2)]
    (if (= hz 0)
      (let [new-y (inc (rand-int (- y 1)))]
        (into (conj [] (nth [1 2] 0)) [new-y]))
      (let [new-x (inc (rand-int (- x 1)))]
        (into [new-x] (rest [1 2])))))
  )
(end-node 5 5)

(defn generate-wall
  [x y]
  (let [start (choose-node x y)]
    (let [hz (rand-int 2)]
      (if (= hz 0) 
        (let [new-y (inc (rand-int (- y 1)))]
          (into (conj [] (nth start 0)) [new-y]))
        (let [new-x (inc (rand-int (- x 1)))]
          (into [new-x] (rest start)))) 
      )))

(generate-wall 3 3)

;(into ['nX] (rest ['x 'y]))
;(into (conj [] (nth ['x 'y] 0)) ['nY])



(defn remove-node
  "generating the start node, given the limit for input (we expect 30 30)"
  [x y]
  (let [options (emptyspace 3 3)]
    (disj options s-node)))

(remove-node 3 3)




(for [i (range 3)]
  (for [j (range 3)]
  (conj #{} [i j])
    ))
