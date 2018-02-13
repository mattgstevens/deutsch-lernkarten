(ns deutsch-lernkarten.routes
  (:require [bidi.bidi :as bidi]
            [pushy.core :as pushy]
            [re-frame.core :as re-frame]))

(def route-hooks (atom {}))
(def route-table (atom {}))

(defn register-szene-hooks! [szene hooks]
  (swap! route-hooks assoc szene hooks))

(defn get-szene-hook [szene hook]
  (get-in @route-hooks [szene hook] identity))

(defn- match-route [url]
  (bidi/match-route @route-table url))

(defn- dispatch-route [matched-route]
  (let [szene-name (:handler matched-route)]
    (re-frame/dispatch [:navigation/menü-aktiv false])
    (re-frame/dispatch [:routes/szene-setzen szene-name])))

(def history
  (pushy/pushy dispatch-route match-route))

; todo: should pre condition that the incoming routing-table looks like a bidi table
(defn init! [routing-table]
  (reset! route-table routing-table)
  (pushy/start! history))

(defn set-url [url]
  (pushy/set-token! history url))

(defn url-for [route]
  (bidi/path-for @route-table route))
