(ns deutsch-lernkarten.szenen.personal-pronomen
  (:require [re-frame.core :as re-frame]))

; Übung für dritte person:
; - given a Nomen choose PersonalPronomen in Nominativ, Akkusativ, Dativ, Genitiv
; => this tests knowledge of what artikel the Nomen is, and mapping it into PersonalPronomen
; => Beispiel: (Nominativ form, Maskulin Nomen) Das Auto    => Es Auto
;              (Akkusativ form, Maskulin Nomen) Den Man     => Ihn Man
;              (Dativ form, Feminin Nomen)      Der Frau    => Ihr Frau
;              (Genitiv form, Plural Nomen)     Der Bücher  => Ihrer Bücher

(defn stamm []
  (let [PersonalPronomen (re-frame/subscribe [:Fakten/PersonalPronomen])]))
