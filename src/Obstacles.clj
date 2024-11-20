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
  [x y start] ;; startcord is a vector
  (let [hz (rand-int 2)]
    (if (= hz 0)
      (let [new-y (inc (rand-int (- y 1)))]
        (into (conj [] (nth start 0)) [new-y]))
      (let [new-x (inc (rand-int (- x 1)))]
        (into [new-x] (rest start)))))
  )
(end-node 5 5 [1 3])

;; where and how can I store the nodes?
;; use the start and end node twice: 1. generate the wall   2. to remove from the empty-space before generating a new start node.
;; We also need to record the start node to generate the end node...

(defn generate-wall
  [x y]
  (let [start (choose-node x y)]
    (let [end (end-node x y start)]
      (if (= (rest start) (rest end)) ;; y axis固定
        (if (<= (nth start 0) (nth end 0))
          (for [i (range (nth start 0) (nth end 0) 1)]
            (into #{} [i (nth start 1)]))
          (for [i (range (nth end 0) (nth start 0) 1)]
               (into #{} [i (nth start 1)])))
        
        (if (<= (nth start 1) (nth end 1)) ;; x 固定
         (for [j (range (nth start 1) (nth end 1) 1)]
           (into #{} [(nth start 0) j]))
         (for [j (range (nth end 1) (nth start 1) 1)]
           (into #{} [(nth start 0) j]))) 
        ))))

(nth [1 2] 1)


; start & end matching in x or y?
(if (= (rest [1 2 3]) (rest [1 2 3]))
  (conj [] 1)
  (conj [] 0))

(generate-wall 3 3)

(into ['nX] (rest ['x 'y]))
(into (conj [] (nth ['x 'y] 0)) ['nY])



;(defn remove-node
;  "generating the start node, given the limit for input (we expect 30 30)"
;  [x y]
;  (let [options (emptyspace 3 3)]
;    (disj options s-node)))

;(remove-node 3 3)




(for [i (range 3)]
  (for [j (range 3)]
  (conj #{} [i j])
    ))
