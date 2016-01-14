; HarZe 2016 - Feeding/coloring chocobo utility
(ns chocobo.core
  (:require [chocobo.feeding :as feeding]
            [chocobo.coloring :as coloring])
  (:gen-class))

(defn format-list
  "Returns a string from a seq"
  [coll]
  (reduce
    str
    (map
      #(str % "\n")
      coll)))

(defn -main
  "Main funciton"
  [& args]
  (let [current (first args)
        target (second args)
        current-data (coloring/find-by-name current)
        target-data (coloring/find-by-name target)]
    (do
      (println (str "*** ChocoboColoring 0.1 - http://github.com/HarZe/ChocoboColoring"))
      (println " ")
      (if
        (and
          (= 2 (count args))
          (= current (:name current-data))
          (= target (:name target-data)))
        (do
          (println (str "---> From " (:name current-data) " to " (:name target-data) " you'll need:"))
          (let [chain (feeding/feed-chain (:rgb current-data) (:rgb target-data))]
            (println (format-list (filter #(not (nil? %)) (feeding/feed-chain-totals chain))))
            (println "---> Feed your chocobo in this order:")
            (println (format-list (feeding/feed-chain-names chain))))
          (println "---> That's all, thanks for using ChocoboColoring"))
        (do
          (println "---> Available colors:")
          (println (format-list (map #(:name %) coloring/color-data)))
          (println "ERROR: wrong usage, please type two names listed above")
          (println "Usage example: java -jar chocobo-coloring-0.1.jar \"Desert Yellow\" \"Ash Gray\""))))))
