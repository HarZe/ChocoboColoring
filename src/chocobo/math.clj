; HarZe 2016 - Auxiliar math functions
(ns chocobo.math)

(defn absolute
  "Absolute value of a number"
  [n]
  (if (< n 0)
    (- n)
    n))

(defn abs
  "Seq of absolute values of its members"
  [coll]
  (map absolute coll))

(defn sum
  "Sum of values of a seq"
  [coll]
  (reduce + coll))

(defn entropy
  "Returns the entropy (sum of absolute values)"
  [coll]
  (sum (abs coll)))

(defn smallest-entropy
  "Returns the collection with smallest entropy"
  [coll]
  (first (sort-by entropy coll)))