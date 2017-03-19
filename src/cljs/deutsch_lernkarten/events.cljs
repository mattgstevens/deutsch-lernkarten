(ns deutsch-lernkarten.events
    (:require [re-frame.core :as re-frame]
              [deutsch-lernkarten.keyboard :as keyboard]
              [deutsch-lernkarten.db :as db]
              [deutsch-lernkarten.routes :as routes]))

; init

(re-frame/reg-event-db
  :initialize-db
  (fn  [_ _]
    db/default-db))

; routes

(re-frame/reg-event-db
  :routes/szene-setzen
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
  (fn [db [_ url]]
    (routes/set-url url)
    db))

; navigation

(re-frame/reg-event-db
  :navigation/menü-aktiv
  (fn [db [_ aktiv?]]
    (if (not (= (:navigation/menü-aktiv db) aktiv?))
      (assoc db :navigation/menü-aktiv aktiv?)
      db)))

(re-frame/reg-event-db
  :navigation/menü-umschalten
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
  (fn [db _]
    (nomen-artikel-fragen-next-aktiv-nomen db)))


(re-frame/reg-event-db
  :nomen-artikel-fragen/sauber
  (fn [db _]
    (nomen-artikel-fragen-sauber db)))

(re-frame/reg-event-db
  :nomen-artikel-fragen/aktiv-antwort
  (fn [db [_ antwort]]
    (let [aktiv-antwort (:nomen-artikel-fragen/aktiv-antwort db)]
      (if (nil? aktiv-antwort)
        (assoc db :nomen-artikel-fragen/aktiv-antwort antwort)
        db))))

(re-frame/reg-event-db
  :nomen-artikel-fragen/gesehen-aktiv-nomen
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
