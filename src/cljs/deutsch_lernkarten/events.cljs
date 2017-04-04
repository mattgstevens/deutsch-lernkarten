(ns deutsch-lernkarten.events
    (:require [clojure.spec :as spec]
              [re-frame.core :as re-frame]
              [deutsch-lernkarten.keyboard :as keyboard]
              [deutsch-lernkarten.db :as db]
              [deutsch-lernkarten.routes :as routes]))

; interceptors
(defn check-and-throw
  "Throw an exception if db doesn't match it's spec."
  [spec db event]
  (when-not (spec/valid? spec db)
    (let [explain-data (spec/explain-data spec db)]
      (throw (ex-info (str "Events => spec check failed for " event ": " explain-data) explain-data)))))

; TODO: in production, this may be useful after receiving data from an API to log
;       data mismatches or detect when localStorage is different than new db spec
;       expectations.. thinking about how this needs to be handled in Elm
(def db-spec-interceptor
  (if goog.DEBUG
    (re-frame/after (partial check-and-throw ::db/app-db))))

; init

(re-frame/reg-event-db
  :initialize-db
  [db-spec-interceptor]
  (fn  [_ _]
    db/default-db))

; routes

(re-frame/reg-event-db
  :routes/szene-setzen
  [db-spec-interceptor]
  (fn [db [_ nächster-szene]]
    (let [aktiv-szene (:routes/aktiv-szene db)
          on-exit (routes/get-szene-hook aktiv-szene :on-exit)
          on-enter (routes/get-szene-hook nächster-szene :on-enter)]
      (apply on-exit nil)
      (keyboard/reset-registry!)
      (apply on-enter nil)
     (assoc db :routes/aktiv-szene nächster-szene))))

(re-frame/reg-event-db
  :routes/url-setzen
  [db-spec-interceptor]
  (fn [db [_ url]]
    (routes/set-url url)
    db))

; navigation

(re-frame/reg-event-db
  :navigation/menü-aktiv
  [db-spec-interceptor]
  (fn [db [_ aktiv?]]
    (if (not (= (:navigation/menü-aktiv db) aktiv?))
      (assoc db :navigation/menü-aktiv aktiv?)
      db)))

(re-frame/reg-event-db
  :navigation/menü-umschalten
  [db-spec-interceptor]
  (fn [db [_ _]]
    (assoc db :navigation/menü-aktiv (if (true? (:navigation/menü-aktiv db)) false true))))

; nomen-artikel-fragen

(defn nomen-artikel-fragen-sauber [db]
  (-> db
      (assoc :nomen-artikel-fragen/aktiv-antwort nil)
      (assoc :nomen-artikel-fragen/aktiv-nomen nil)
      (assoc :nomen-artikel-fragen/gesehen-nomen #{})
      (assoc :nomen-artikel-fragen/glückwunsch false)))

(defn nomen-artikel-fragen-next-aktiv-nomen [db]
  (let [remaining-nomen (into [] (clojure.set/difference (into #{} (keys (get-in db [:Fakten :Nomina])))
                                                         (:nomen-artikel-fragen/gesehen-nomen db)))]
    (if (empty? remaining-nomen)
      (assoc db :nomen-artikel-fragen/glückwunsch true)
      (assoc db :nomen-artikel-fragen/aktiv-nomen (rand-nth remaining-nomen)))))

(re-frame/reg-event-db
  :nomen-artikel-fragen/initialisieren
  [db-spec-interceptor]
  (fn [db _]
    (nomen-artikel-fragen-next-aktiv-nomen db)))


(re-frame/reg-event-db
  :nomen-artikel-fragen/sauber
  [db-spec-interceptor]
  (fn [db _]
    (nomen-artikel-fragen-sauber db)))

(re-frame/reg-event-db
  :nomen-artikel-fragen/aktiv-antwort
  [db-spec-interceptor]
  (fn [db [_ antwort]]
    (let [aktiv-antwort (:nomen-artikel-fragen/aktiv-antwort db)]
      (if (nil? aktiv-antwort)
        (assoc db :nomen-artikel-fragen/aktiv-antwort antwort)
        db))))

(re-frame/reg-event-db
  :nomen-artikel-fragen/gesehen-aktiv-nomen
  [db-spec-interceptor]
  (fn [db _]
    (let [aktiv-nomen (:nomen-artikel-fragen/aktiv-nomen db)
          aktiv-antwort (:nomen-artikel-fragen/aktiv-antwort db)
          gesehen-nomen (:nomen-artikel-fragen/gesehen-nomen db)]
      (if (not (nil? aktiv-antwort))
        (-> db
            (assoc :nomen-artikel-fragen/gesehen-nomen (conj gesehen-nomen aktiv-nomen))
            (assoc :nomen-artikel-fragen/aktiv-antwort nil)
            (nomen-artikel-fragen-next-aktiv-nomen))
        db))))
