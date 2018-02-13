(ns deutsch-lernkarten.layout
    (:require [re-frame.core :as re-frame]
              [deutsch-lernkarten.classnames :as classnames]
              [deutsch-lernkarten.routes :as routes]
              [deutsch-lernkarten.routing.table :as routing-table]))

(defn- top-nav-bar []
  (let [aktiv-szene (re-frame/subscribe [:routes/aktiv-szene])
        menü-aktiv (re-frame/subscribe [:navigation/menü-aktiv])]
    (fn []
      [:div {:className "container"}
        [:nav {:className "nav"}
         [:div {:className "nav-left"}
          [:a {:className "nav-item" :href (routes/url-for :heim)} "Heim"]]
         [:div {:className (classnames/style "nav-right" "nav-menu" {:is-active @menü-aktiv})}
          [:a {:className (classnames/style "nav-item" {:is-active (= :nomen-liste @aktiv-szene)}) :href (routes/url-for :nomen-liste)} "Nomen Liste"]
          [:a {:className (classnames/style "nav-item" {:is-active (= :verben-liste @aktiv-szene)}) :href (routes/url-for :verben-liste)} "Verben Liste"]
          [:a {:className (classnames/style "nav-item" {:is-active (= :pronomen-liste @aktiv-szene)}) :href (routes/url-for :pronomen-liste)} "Pronomen Liste"]]
         [:span {:className "nav-toggle" :on-click #(re-frame/dispatch [:navigation/menü-umschalten])}
          [:span]
          [:span]
          [:span]]]])))

(defn- aktuell-szene []
  (let [aktiv-szene (re-frame/subscribe [:routes/aktiv-szene])]
    (fn []
      (let [render-szene (routing-table/get-render-for-szene @aktiv-szene)]
          [render-szene]))))


(defn stamm-szene []
  [:div {:className "app-root"}
    [top-nav-bar]
    [aktuell-szene]])
