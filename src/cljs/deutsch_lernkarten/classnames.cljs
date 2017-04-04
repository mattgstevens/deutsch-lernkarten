(ns deutsch-lernkarten.classnames
  (:require [clojure.string :as string]))

(defn- classes-reducer [acc x]
  (condp #(%1 %2) x
    string? (assoc! acc x true)
    keyword? (assoc! acc (name x) true)
    number? (assoc! acc x true)
    map? (reduce #(assoc! %1 (name (key %2)) (val %2)) acc x)
    acc))

(defn- parse-classes [classes]
  (persistent! (reduce classes-reducer (transient {}) classes)))

(defn style [& classes]
  (->> classes
       (parse-classes)
       (filter #(val %))
       (keys)
       (string/join " ")))
