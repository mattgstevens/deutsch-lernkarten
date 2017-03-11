(ns deutsch-lernkarten.routes
  (:require [bidi.bidi :as bidi]
            [pushy.core :as pushy]
            [re-frame.core :as re-frame]))

(def routes ["/" {""                      :heim

                  "verben-liste"          :verben-liste

                  "nomen-liste"           :nomen-liste
                  "nomen-artikel-fragen"  :nomen-artikel-fragen}])

(def route-hooks (atom {}))

(defn register-szene-hooks! [szene hooks]
  (swap! route-hooks assoc szene hooks))

(defn get-szene-hook [szene hook]
  (get-in @route-hooks [szene hook] identity))

(defn- parse-url [url]
  (bidi/match-route routes url))

(defn- dispatch-route [matched-route]
  (let [szene-name (:handler matched-route)]
    (re-frame/dispatch [:navigation/menü-aktiv false])
    (re-frame/dispatch [:routes/szene-setzen szene-name])))

(def history
  (pushy/pushy dispatch-route parse-url))

(defn init! []
  (pushy/start! history))

(defn set-url [url]
  (pushy/set-token! history url))

(def url-for (partial bidi/path-for routes))
