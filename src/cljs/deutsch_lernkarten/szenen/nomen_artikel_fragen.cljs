(ns deutsch-lernkarten.szenen.nomen-artikel-fragen
  (:require [re-frame.core :as re-frame]
            [deutsch-lernkarten.classnames :as classnames]
            [deutsch-lernkarten.keyboard :as keyboard]
            [deutsch-lernkarten.routes :as routes]))

(routes/register-szene-hooks!
  :nomen-artikel-fragen
  {:on-exit #(re-frame/dispatch [:nomen-artikel-fragen/sauber])})

(defn- render-anfang []
  (keyboard/register! {:space #(re-frame/dispatch [:nomen-artikel-fragen/initialisieren])})
  [:div {:className "card"}
    [:header {:className "card-header"}
     [:p {:className "card-header-title"} "Nomen mit Artikel"]]
    [:div {:className "card-content"}
     [:div {:className "content"} "Beginnen Sie mit dem Training"]]
    [:footer {:className "card-footer"}
     [:a {:className "card-footer-item" :on-click #(re-frame/dispatch [:nomen-artikel-fragen/initialisieren])}
      "Beginnen Sie mit dem Training"
      [:span {:className "hotkey-indicator"} "space"]]]])

(defn- render-glückwunsch []
  (keyboard/register! {:one #(re-frame/dispatch [:nomen-artikel-fragen/sauber])
                       :two #(re-frame/dispatch [:routes/url-setzen "/"])})
  [:div {:className "card"}
    [:header {:className "card-header"}
     [:p {:className "card-header-title"} "Nomen mit Artikel"]]
    [:div {:className "card-content"}
     [:div {:className "content"} "Herzlichen Glückwunsch, dass du alle Karten beendet hast."]]
    [:footer {:className "card-footer"}
     [:a {:className "card-footer-item" :on-click #(re-frame/dispatch [:nomen-artikel-fragen/sauber])}
      "Übe mehr"
      [:span {:className "hotkey-indicator"} 1]]
     [:a {:className "card-footer-item" :href (routes/url-for :heim)}
      "Heim"
      [:span {:className "hotkey-indicator"} 2]]]])

(defn- render-karte [aktiv-nomen aktiv-antwort nomen-fakten]
  (keyboard/register! {:space #(re-frame/dispatch [:nomen-artikel-fragen/gesehen-aktiv-nomen])
                       :one #(re-frame/dispatch [:nomen-artikel-fragen/aktiv-antwort :der])
                       :two #(re-frame/dispatch [:nomen-artikel-fragen/aktiv-antwort :das])
                       :three #(re-frame/dispatch [:nomen-artikel-fragen/aktiv-antwort :die])})
  [:div {:className "card"}
    [:header {:className "card-header"}
     [:p {:className "card-header-title"} "Nomen mit Artikel"]]
    [:div {:className "card-content"}
     [:div {:className "content"}
       (if (nil? aktiv-antwort)
         (name aktiv-nomen)
         [:div
          (str (name (:Artikel nomen-fakten)) " " (name aktiv-nomen) " (" (:English nomen-fakten) ")")
          [:span {:className "fira-icon is-primary is-pulled-right" :on-click #(re-frame/dispatch [:nomen-artikel-fragen/gesehen-aktiv-nomen])} "->>"]])]]

    [:footer {:className "card-footer"}
     [:a {:className (classnames/style "card-footer-item" {:is-active (= aktiv-antwort :der)
                                                           :is-disabled (not (nil? aktiv-antwort)) :is-correct (and (not (nil? aktiv-antwort))(= (:Artikel nomen-fakten) :der))
                                                           :is-incorrect (and (= aktiv-antwort :der) (not (= (:Artikel nomen-fakten) aktiv-antwort)))})
          :on-click #(re-frame/dispatch [:nomen-artikel-fragen/aktiv-antwort :der])}
      "Der"
      [:span {:className "hotkey-indicator"} 1]]
     [:a {:className (classnames/style "card-footer-item" {:is-active (= aktiv-antwort :das)
                                                           :is-disabled (not (nil? aktiv-antwort)) :is-correct (and (not (nil? aktiv-antwort))(= (:Artikel nomen-fakten) :das))
                                                           :is-incorrect (and (= aktiv-antwort :das) (not (= (:Artikel nomen-fakten) aktiv-antwort)))})
          :on-click #(re-frame/dispatch [:nomen-artikel-fragen/aktiv-antwort :das])}
      "Das"
      [:span {:className "hotkey-indicator"} 2]]
     [:a {:className (classnames/style "card-footer-item" {:is-active (= aktiv-antwort :die)
                                                           :is-disabled (not (nil? aktiv-antwort)) :is-correct (and (not (nil? aktiv-antwort))(= (:Artikel nomen-fakten) :die))
                                                           :is-incorrect (and (= aktiv-antwort :die) (not (= (:Artikel nomen-fakten) aktiv-antwort)))})
          :on-click #(re-frame/dispatch [:nomen-artikel-fragen/aktiv-antwort :die])}
      "Die"
      [:span {:className "hotkey-indicator"} 3]]]])

(defn stamm []
  (let [aktiv-nomen (re-frame/subscribe [:nomen-artikel-fragen/aktiv-nomen])
        aktiv-antwort (re-frame/subscribe [:nomen-artikel-fragen/aktiv-antwort])
        alle-nomen-fakten (re-frame/subscribe [:Fakten/Nomen])
        glückwunsch (re-frame/subscribe [:nomen-artikel-fragen/glückwunsch])]
    (fn []
      [:div {:className "columns is-mobile push-top"}
        [:div {:className "column is-4-desktop is-offset-4-desktop
                                  is-6-tablet is-offset-3-tablet
                                  is-8-mobile is-offset-2-mobile"}
          (if (true? @glückwunsch)
            [render-glückwunsch]
            (if (nil? @aktiv-nomen)
              [render-anfang]
              [render-karte @aktiv-nomen @aktiv-antwort (get @alle-nomen-fakten @aktiv-nomen)]))]])))
