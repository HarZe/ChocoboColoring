; HarZe 2016 - "Feeding chocobo problem" logic

(ns chocobo.feeding
  (:require [chocobo.math :as math]
            [chocobo.rgb :as rgb]))

(def berry-data [{:rgb [5 -5 -5] :name "Xelphatol Apple"},
                 {:rgb [-5 5 -5] :name "Mamook Pear"},
                 {:rgb [-5 -5 5] :name "O'Ghomoro Berries"},
                 {:rgb [-5 5 5] :name "Doman Plum"},
                 {:rgb [5 -5 5] :name "Valfruit"},
                 {:rgb [5 5 -5] :name "Cieldalaes Pineapple"}])

(defn find-by-color
  "Returns the berry data based on rgb color"
  [color]
  (first
    (filter
      #(rgb/equal color (:rgb %))
      berry-data)))

(defn find-name
  "Transforms rgb color data to a berry name"
  [color]
  (:name (find-by-color color)))

(def closeness 6)

(defn close-enough
  "Returns true if the given rgb values have almost equal components"
  [x y]
  (every?
    (partial > closeness)
    (math/abs
      (rgb/diff x y))))

(defn feed
  "Returns a collection with each color obtained by using the berries on the given rgb color"
  [color]
  (map
    (partial rgb/add color)
    (map :rgb berry-data)))

(defn best-choice
  "Returns the next berry that should be feed to the Chocobo in order to achieve the target color from a the current one"
  [current target]
  (rgb/diff target
    (rgb/add current
      (math/smallest-entropy
        (map
          (partial rgb/diff target)
          (filter
            rgb/valid
            (feed current)))))))

(defn feed-chain
  "Return the sorted list of berries (rgb values) needed to feed a Chocobo from the current color to the target"
  ([current target]
   (feed-chain current target []))
  ([current target berries]
   (if (close-enough current target)
     berries
     (let [next (best-choice current target)]
       (recur
         (rgb/add current next)
         target
         (conj berries next))))))

(defn feed-chain-totals
  "Return the total of berries required for a chain"
  [chain]
  (map
    #(let [total (rgb/counter % chain)
           name (find-name %)]
      (if (> total 0)
        (str name " x" total)))
    (map #(:rgb %) berry-data)))

(defn feed-chain-names
  "Parses a list of rgb values to berry names"
  [chain]
  (map find-name chain))