(ns Borders)

;; Uses 4 defined mini-functions before the ultimate border function
;; (border x y) where input "x" "y" is the boundary of the map.


(defn pre-h-wall 
  "For nodes [i 0] where i 0 -> x"
  [x y] ;; y is not used here
  (let [coords1 
        (for [i (range (inc x))] 
           [i 0])] 
             (into #{} coords1))
    )

(for [i (range (inc 3))]
  [i 0])

(pre-h-wall 3 3)
;; (range 10) => (0 1 2 3 4 5 6 7 8 9)

(defn pre-v-wall 
  "For nodes [0 j] where j 0 -> y"
  [x y] ;; x is not used here
  (let [coords2
        (for [j (range (inc y))]
          [0 j])]
    (into #{} coords2)))

(pre-v-wall 3 3)

;; into does a seek, while conj takes one element (treating it as a list)
(into (pre-v-wall 3 3) (pre-h-wall 3 3))


; this works!
(defn h-wall [x y]
    (let [coords3
        (for [i (range (inc x))]
          [i y])]
    (into #{} coords3)))
;(h-wall 3 3)

(defn h-wall [x y]
  (let [coords3
        (for [i (range (inc x))]
          [i y])]
    (into (pre-h-wall x y) coords3)))

(h-wall 3 3)

(defn v-wall [x y]
  (let [coords4
        (for [j (range (inc y))]
          [x j])
        ]
    (into (pre-v-wall x y) coords4)))

(v-wall 3 3)





;(into #{2 3 4} #{1 2 4})

;;; ________DO NOT TOUCH________
(defn border [x y]
  (into (h-wall x y) (v-wall x y)))
;;; ____________________________


(border 3 3)
(border 30 30)
;;just adding some edge cases
(border 1 1)
(border 0 0)


;(defn generate-coordinates [x y] 
;  (let [coords (for [i (range (inc x)) 
;                     j (range (inc y))] 
;                 [i j])]
;      (conj #{} coords)))

