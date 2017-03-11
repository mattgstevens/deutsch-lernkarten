(ns deutsch-lernkarten.subs
    (:require-macros [reagent.ratom :refer [reaction]])
    (:require [re-frame.core :as re-frame]))

; fakten

(re-frame/reg-sub
 :Fakten/Nomen
 (fn [db]
   (get-in db [:Fakten :Nomen])))

(re-frame/reg-sub
 :Fakten/Verben
 (fn [db]
   (get-in db [:Fakten :Verben])))

; routes

(re-frame/reg-sub
  :routes/aktiv-szene
  (fn [db]
    (:routes/aktiv-szene db)))

; navigation

(re-frame/reg-sub
  :navigation/menü-aktiv
  (fn [db]
    (:navigation/menü-aktiv db)))

; nomen-artikel-fragen

(re-frame/reg-sub
  :nomen-artikel-fragen/aktiv-nomen
  (fn [db]
    (:nomen-artikel-fragen/aktiv-nomen db)))

(re-frame/reg-sub
  :nomen-artikel-fragen/aktiv-antwort
  (fn [db]
    (:nomen-artikel-fragen/aktiv-antwort db)))

(re-frame/reg-sub
  :nomen-artikel-fragen/glückwunsch
  (fn [db]
    (:nomen-artikel-fragen/glückwunsch db)))
