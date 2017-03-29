(ns deustch-lernkarten.szenen.adverbs
  (:require [re-frame.core :as re-frame]
            [deutsch-lernkarten.classnames :as classnames]
            [deutsch-lernkarten.keyboard :as keyboard]
            [deutsch-lernkarten.routes :as routes]))

; Übung:
; - given an artikel in [Positiv, Komparitiv, Superlativ] spell it correctly in another form

(defn stamm []
  (let [Adjektiv (re-frame/subscribe [:Fakten/Adjektiv])]))
