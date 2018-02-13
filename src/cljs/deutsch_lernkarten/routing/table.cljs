(ns deutsch-lernkarten.routing.table
  (:require [deutsch-lernkarten.szenen.adverb-fragen :as adverb-fragen-szene]
            [deutsch-lernkarten.szenen.heim :as heim-szene]
            [deutsch-lernkarten.szenen.nomen-liste :as nomen-liste-szene]
            [deutsch-lernkarten.szenen.nomen-artikel-fragen :as nomen-artikel-fragen-szene]
            [deutsch-lernkarten.szenen.pronomen-liste :as pronomen-liste-szene]
            [deutsch-lernkarten.szenen.verben-liste :as verben-liste-szene]
            [deutsch-lernkarten.szenen.vier-null-vier :as vier-null-vier-szene]))

(def szene-map {:adverb-fragen adverb-fragen-szene/stamm
                :heim heim-szene/stamm
                :nomen-artikel-fragen nomen-artikel-fragen-szene/stamm
                :nomen-liste nomen-liste-szene/stamm
                :pronomen-liste pronomen-liste-szene/stamm
                :verben-liste verben-liste-szene/stamm
                :vier-null-vier vier-null-vier-szene/stamm})

(def routes ["/" {""                      :heim

                  "adverb-fragen"         :adverb-fragen

                  "nomen-liste"           :nomen-liste
                  "nomen-artikel-fragen"  :nomen-artikel-fragen

                  "pronomen-liste"        :pronomen-liste

                  "verben-liste"          :verben-liste}])

(defn get-render-for-szene [szene]
  (let [render-szene (get szene-map szene)]
    (if (nil? render-szene)
      (get szene-map :vier-null-vier)
      render-szene)))
