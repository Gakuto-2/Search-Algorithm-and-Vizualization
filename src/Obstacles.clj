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
(empty-space 5 5)
;(seq (empty-space 3 3))


(rand-nth (seq (empty-space 3 3)))

(defn choose-node
  "generating the start node, given the limit for input (we expect 30 30)"
  [x y]
  (let [options (seq (empty-space x y))]
  (rand-nth options)))

(choose-node 10 10)
(choose-node 5 5)


(defn end-node 
  "generate end node, given the border and start node"
  [x y start] ;; startcord is a vector
  (let [hz (rand-int 2)]
    (if (= hz 0)
      (let [new-y (inc (rand-int (- y 1)))]
        (into (conj [] (nth start 0)) [new-y]))
      (let [new-x (inc (rand-int (- x 1)))]
        (into [new-x] (rest start))))
    )
  )

(end-node 5 5 [4 1])
(end-node 5 5 [1 3])

;; where and how can I store the nodes?
;; use the start and end node twice: 1. generate the wall   2. to remove from the empty-space before generating a new start node.
;; We also need to record the start node to generate the end node...


(defn inter-wall
  "take the start coords and end coords, and return a hashset with them and the nodes in between"
  [start end]
  (let [walltrack #{}]
    (if (= (rest start) (rest end)) ;; y axis固定 
      (if (<= (nth start 0) (nth end 0))
        (for [i (range (nth start 0) (nth end 0) 1)]
          (conj walltrack [i (nth start 1)]))
        (for [i (range (nth end 0) (nth start 0) 1)]
          (conj walltrack [i (nth start 1)])))

      (if (<= (nth start 1) (nth end 1)) ;; x 固定 
        (for [j (range (nth start 1) (nth end 1) 1)]
          (conj walltrack [(nth start 0) j]))
        (for [j (range (nth end 1) (nth start 1) 1)]
          (conj walltrack [(nth start 0) j]))))))

(reduce into (inter-wall [1 3] [5 3]))
(reduce into (inter-wall [1 3] [1 3])) ;; returns empty vector

(defn line-wall
  "generate start & end point, and all the nodes in between as obstacles"
  [x y]
  (let [start (choose-node x y)]
    (let [end (end-node x y start)] 
      (reduce into (inter-wall start end)))))

(defn line-wall
  "generate start & end point, and all the nodes in between as obstacles"
  [x y]
  (let [start (choose-node x y)]
    (let [end (end-node x y start)]
      (if (= start end)  
      (line-wall x y)  ;; using recursion here!
      (reduce into (inter-wall start end))))))

(line-wall 10 10)
(line-wall 4 4)
;; what can I do to avoid [] (an empty hashset??)
;; check if start node is not the same as the end node
;; use RECURSION!!!!!
(rand-int 2)

(defn scaler
  [x y]
    (if (<= x y)
    x
    y))
(scaler 10 2)


;; うまくいかない
(defn generate-wall
  [x y]
  (let [hashset #{}] 
    (for [i (range 3)] 
      (into hashset (line-wall x y))))
  )

(defn generate-wall
  [x y]
  (for [i (range 3)]
    (line-wall x y)))

(generate-wall 10 10)

; start & end matching in x or y?
(if (= (rest [1 2 3]) (rest [1 2 3]))
  (conj [] 1)
  (conj [] 0))

(generate-wall 3 3)

; (into ['nX] (rest ['x 'y]))
; (into (conj [] (nth ['x 'y] 0)) ['nY])



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
