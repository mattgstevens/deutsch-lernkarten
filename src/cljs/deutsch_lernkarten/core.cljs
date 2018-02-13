(ns deutsch-lernkarten.core
    (:require [devtools.core :as devtools]
              [reagent.core :as reagent]
              [re-frame.core :as re-frame]
              [re-frisk-remote.core :refer [enable-re-frisk-remote!]]
              [deutsch-lernkarten.config :as config]
              [deutsch-lernkarten.events]
              [deutsch-lernkarten.routes :as routes]
              [deutsch-lernkarten.routing.table :as routing-table]
              [deutsch-lernkarten.subs]
              [deutsch-lernkarten.layout :as layout]))

(defn dev-setup []
  (when config/debug?
    (enable-console-print!)
    (enable-re-frisk-remote!)
    (devtools/install!)
    (println "dev mode active; Sic itur ad astra!")))

(defn mount-root []
  (re-frame/clear-subscription-cache!)
  (reagent/render [layout/stamm-szene]
                  (.getElementById js/document "app")))

(defn ^:export init []
  (dev-setup)
  (routes/init! routing-table/routes)
  (re-frame/dispatch-sync [:initialize-db])
  (mount-root))
