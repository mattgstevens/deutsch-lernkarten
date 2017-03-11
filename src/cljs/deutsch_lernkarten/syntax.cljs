(ns deutsch-lernkarten.syntax
  (:require [deutsch-lernkarten.db :as db]))

(defn check-nomen-answer [Nomen])

; hilfsverb might be a vector or a string..
(defn check-verben-answer [Verben Zeit Person answer]
  "Given the tuple [Verben Zeit Person] check that the answer is correct"
  (= (get-in db/Verben [Verben Zeit Person])
     answer))
