(ns deutsch-lernkarten.szenen.vier-null-vier
  (:require [deutsch-lernkarten.routes :as routes]))

(defn stamm []
  (fn []
    [:section {:className "section"}
      [:div {:className "hero is-primary"}
        [:div {:className "container"}
          [:div {:className "hero-body"}
            [:div {:className "columns"}
             [:div {:className "column"}
              [:div {:className "title"} "404"]
              [:div {:className "subtitle"} "Du hast in der Matrix ein Loch gefunden."]
              [:a {:className "button is-primary is-inverted" :href (routes/url-for :heim)} "Heim gehen"]]]]]]]))
