

; brainstorm

(rand-int 3)
;; Returns a random integer between 0 (inclusive) and n (exclusive).
;; Use this to generate random starting points, if we are going to make them walls.

;; We can also generate the ending point (of the wall generation) by either having the x coordinates or y coordinates fixed, and the other is determined by the rand-int
;  function

(defn generate-obs [])

(generate-obs)

;; removing the starting & end point disjoin from the obstacle hashset
;; seq is used to make it into a list

(rand-nth (seq #{[1 2] [6 9] [2 5]})) ;hashset has no order
(rand-nth [1 2 3 4])

(first #{[1 2] [6 9] [2 5]})
;; set membership

(conj #{[1 2]} [(rand-int 30) (rand-int 30)])

;; how you remove an element from a hashset
(disj #{[1 2] [3 4]} [1 2])



;; CODING
; we have the borders hashset
(defn emptyspace
  "fn that keeps track of the options of generating the start node"
  [x y]
  (let [coords 
        (for [i (range (dec x)) 
              j (range (dec y))]
          [(inc i) (inc j)])
        ]
    (into #{} coords))
  )

(emptyspace 3 3)
(def s-node (rand-nth (seq (emptyspace 3 3))))


(defn startnode
  "generating the start node, given the limit for input (we expect 30 30)"
  [x y]
  (let [options (seq (emptyspace 3 3))]
  (rand-nth options)))
;(startnode 3 3)



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

