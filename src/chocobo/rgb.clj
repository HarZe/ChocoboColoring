; HarZe 2016 - RGB vector functions
(ns chocobo.rgb)

(defn equal
  "Returns true if components are equal"
  [x y]
  (every?
    (partial = true)
    (map = x y)))

(defn add
  "Sum of the components"
  [x y]
  (map + x y))

(defn diff
  "Difference of the components"
  [x y]
  (map - x y))

(defn valid
  "Returns true if all components are within [0,255] range"
  [color]
  (every?
    (partial = true)
    (map
      #(and (>= % 0) (<= % 255))
      color)))

(defn counter
  "Returns the number of ocurrences of the given rgb value in a seq"
  [color coll]
  (count
    (filter
      #(equal color %)
      coll)))