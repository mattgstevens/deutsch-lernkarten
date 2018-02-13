(ns deutsch-lernkarten.szenen.heim
  (:require [deutsch-lernkarten.routes :as routes]))

(defn stamm []
  [:section {:className "section"}
   [:div {:className "hero is-primary"}
    [:div {:className "container"}
     [:div {:className "hero-body"}
      [:div {:className "columns"}
       [:div {:className "column"}
        [:div {:className "title"} "Heim"]
        [:div {:className "subtitle"} "Ist es nicht gemütlich?"]]]]]]
   [:aside {:className "menu" :style {:text-align "center" :margin-top "20px"}}
    [:p {:className "menu-label"} "Nomen Fragen"]
    [:ul {:className "menu-list"}
     [:li [:a {:href (routes/url-for :nomen-artikel-fragen)} "Nomen mit Artikel Fragen"]]]
    [:p {:className "menu-label"} "Verben Fragen"]
    [:ul {:className "menu-list"}]]])
