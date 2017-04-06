(ns deutsch-lernkarten.views
    (:require [re-frame.core :as re-frame]
              [deutsch-lernkarten.classnames :as classnames]
              [deutsch-lernkarten.layout :as layout]
              [deutsch-lernkarten.routes :as routes]
              [deutsch-lernkarten.szenen.nomen-artikel-fragen :as nomen-artikel-fragen-szene]))

; szene liste

(defn heim-szene []
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

(defn nomen-liste-szene []
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

(defn pronomen-liste-szene []
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

(defn verben-liste-szene []
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

(defn vier-null-vier-szene []
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

; TODO: this should live in routes
(def szeneMap {:heim [heim-szene]

               :nomen-artikel-fragen [nomen-artikel-fragen-szene/stamm]
               :nomen-liste [nomen-liste-szene]

               :pronomen-liste [pronomen-liste-szene]

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
