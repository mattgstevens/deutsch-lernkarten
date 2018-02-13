(ns deutsch-lernkarten.szenen.nomen-liste
  (:require [re-frame.core :as re-frame]))

(defn stamm []
  (let [Nomina (re-frame/subscribe [:Fakten/Nomina])]
    (fn []
      (let [nominaMap @Nomina]
        [:section {:className "section"}
          [:div {:className "columns is-multiline is-mobile"}
            (for [Nomen (keys nominaMap)]
              (let [{:keys [Artikel English]} (get nominaMap Nomen)]
                ^{:key Nomen}
                [:div {:className "column is-2-desktop is-4-tablet is-6-mobile" :on-click #(.log js/console (str "Clicked " Nomen))}
                 [:div {:className "tile is-ancestor"}
                  [:div {:className "tile is-vertical is-parent"}
                   [:div {:className "notification is-info"} (str (name Artikel) " " Nomen)]
                   [:div {:className "notification"} (str "(" English ")")]]]]))]]))))
