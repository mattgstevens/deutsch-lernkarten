(ns deutsch-lernkarten.keyboard
  (:require [goog.events :as events]
            [clojure.set]))

(def keycode-registry (atom {}))

; The only hotkeys being used for now
(def word->code {:space 32
                 :one 49
                 :two 50
                 :three 51})

(def code->word (clojure.set/map-invert word->code))

(defn- key-code [e]
  (.-keyCode e))

(defn- reset-registry! []
  (reset! keycode-registry {}))

; Allowing one subscribe to be active at a time for now
(defn register! [keys->events]
  (reset! keycode-registry keys->events))

(defn- keydown-subscription [e]
  (let [key-word (code->word (key-code e))
        registered-fn (get @keycode-registry key-word)]
    (if (not (nil? registered-fn))
      (apply registered-fn nil))))

(def keydown-listener (events/listen (.-body js/document) (.-KEYDOWN events/EventType) keydown-subscription))
