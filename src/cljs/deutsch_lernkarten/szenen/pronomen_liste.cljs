(ns deutsch-lernkarten.szenen.pronomen-liste
  (:require [re-frame.core :as re-frame]))

(defn stamm []
  (let [Pronomina (re-frame/subscribe [:Fakten/Pronomina])]
    (fn []
      (let [pronominaMap @Pronomina]
        [:section {:className "section"}
         [:div {:className "columns is-multiline is-mobile"}
          (for [Pronomen (keys pronominaMap)]
            (let [{:keys [English Maskulin Neutrum Feminin Plural]} (get pronominaMap Pronomen)]
              ^{:key Pronomen}
              [:div {:className "column is-4-desktop is-12-tablet is-12-mobile" :on-click #(.log js/console (str "Clicked " Pronomen))}
               [:div {:className "tile is-ancestor"}
                [:div {:className "tile is-vertical is-parent"}
                 [:div {:className "notification is-info"} (str Pronomen)]
                 [:div {:className "notification"} (str "(" English ")")]
                 [:div {:className "notification is-warning"}
                  (let [kasusList [:Nominativ :Akkusativ :Dativ :Genitiv]]
                    [:table {:className "table"}
                     [:thead
                      [:tr
                        [:th]
                        (for [kasus kasusList]
                          ^{:key (str "thead-" kasus)}
                           [:th (name kasus)])]]
                     [:tbody
                      (for [genderTuple [["Maskulin" Maskulin]
                                         ["Neutrum" Neutrum]
                                         ["Feminin" Feminin]
                                         ["Plural" Plural]]]
                        ^{:key (str "tbody-" (first genderTuple))}
                        [:tr
                         [:th (str (first genderTuple))]
                         (for [kasus kasusList]
                           ^{:key (str "tbody-" kasus)}
                           [:th (str (or (get (second genderTuple) kasus) "-"))])])]])]]]]))]]))))
