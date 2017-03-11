(ns deutsch-lernkarten.dev-server
  (:require [clojure.java.io :as io]))

(defn http-handler [request]
  {:status 200
   :headers {"Content-Type" "text/html; charset=utf-8"}
   :body (io/input-stream (io/resource "public/index.html"))})
