(ns deutsch-lernkarten.szenen.verben-liste
  (:require [re-frame.core :as re-frame]))

(defn stamm []
  (let [Verben (re-frame/subscribe [:Fakten/Verben])]
    (fn []
      (let [verbMap @Verben]
        [:section {:className "section"}
         [:div {:className "columns is-multiline is-mobile"}
            (for [Verb (keys verbMap)]
              (let [{:keys [English Präsens Perfekt]} (get verbMap Verb)]
                ^{:key Verb}
                [:div {:className "column is-2-desktop is-4-tablet is-6-mobile" :on-click #(.log js/console (str "Clicked " Verb))}
                 [:div {:className "tile is-ancestor"}
                    [:div {:className "tile is-vertical is-parent"}
                     [:div {:className "notification is-info"} (str Verb)]
                     [:div {:className "notification"} (str "(" English ")")]
                     [:div {:className "tile is-child notification is-warning"}
                      (for [Person (keys Präsens)]
                        (let [verbForm (get Präsens Person)]
                          ^{:key (str "Präsens" Person verbForm)}
                          [:div (str (name Person) ": "verbForm)]))]
                     [:div {:className "tile is-child notification is-warning"} (str "Perfekt: " Perfekt)]]]]))]]))))
