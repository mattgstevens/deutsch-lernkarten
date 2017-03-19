(ns deutsch-lernkarten.views
    (:require [re-frame.core :as re-frame]
              [deutsch-lernkarten.classnames :as classnames]
              [deutsch-lernkarten.layout :as layout]
              [deutsch-lernkarten.routes :as routes]
              [deutsch-lernkarten.szenen.nomen-artikel-fragen :as nomen-artikel-fragen-szene]))

(defn render-alle-nomen [nomenMap]
  [:section {:className "section"}
    [:div {:className "columns is-multiline is-mobile"}
      (for [Nomen (keys nomenMap)]
        (let [{:keys [Artikel]} (get nomenMap Nomen)]
          ^{:key Nomen}
          [:div {:className "column is-2-desktop is-4-tablet is-6-mobile" :on-click #(.log js/console (str "Clicked " Nomen))}
           [:div {:className "notification is-info"}(str (name Artikel) " " Nomen)]]))]])

(defn render-alle-verben [verbMap]
  [:section {:className "section"}
   [:div {:className "columns is-multiline is-mobile"}
      (for [Verb (keys verbMap)]
        (let [{:keys [English Präsens]} (get verbMap Verb)]
          ^{:key Verb}
          [:div {:className "column is-2-desktop is-4-tablet is-6-mobile" :on-click #(.log js/console (str "Clicked " Verb))}
           [:div {:className "tile is-ancestor"}
              [:div {:className "tile is-vertical"}
               [:div {:className "tile is-parent"}
                 [:div {:className "tile is-child notification is-info"}
                  [:div (str Verb " (" English ")")]]]
               [:div {:className "tile is-parent"}
                 [:div {:className "tile is-child notification is-warning"}
                  (for [Person (keys Präsens)]
                    (let [verbForm (get Präsens Person)]
                      ^{:key (str "Präsens" Person verbForm)}
                      [:div (str (name Person) ": "verbForm)]))]]]]]))]])

; szene liste

(defn heim-szene []
  [:div {:className "heim"}
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


(defn nomen-liste-szene []
  (let [Nomen (re-frame/subscribe [:Fakten/Nomen])]
    (fn []
      (render-alle-nomen @Nomen))))

(defn verben-liste-szene []
  (let [Verben (re-frame/subscribe [:Fakten/Verben])]
    (fn []
      (render-alle-verben @Verben))))

(defn vier-null-vier-szene []
  (fn []
    [:div
     [:h1 "404"]
     [:div "Du hast in der Matrix ein Loch gefunden."]
     [:a {:className "button" :href (routes/url-for :heim)} "Heim gehen"]]))

; TODO: this should live in routes
(def szeneMap {:heim [heim-szene]

               :nomen-artikel-fragen [nomen-artikel-fragen-szene/stamm]
               :nomen-liste [nomen-liste-szene]

               :verben-liste [verben-liste-szene]

               :vier-null-vier [vier-null-vier-szene]})

(defn stamm-szene []
  (let [aktiv-szene (re-frame/subscribe [:routes/aktiv-szene])]
    (fn []
      (let [szene @aktiv-szene]
        [:div {:className "app-root"}
          [layout/top-nav-bar]
          (if (contains? szeneMap szene)
            (get szeneMap szene)
            (get szeneMap :vier-null-vier))]))))
