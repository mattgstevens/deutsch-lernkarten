(ns deutsch-lernkarten.db)

(def why-learn [{:Deutsch "Die Nomen und Artikel sind für immer zusammen. Es gibt keine Alternative."
                 :English "The noun and article are forever together. There is no alternative."}
                {:Deutsch "Es gibt verschiedene Pluralendungen. Die Pluralendungen muss man auswendig lernen."
                 :English "There are different plural endings. The plural endings you must learn by heart."}
                {:Deutsch "Ich will spreche fließend Deutsch."
                 :English "I want to speak fluent German."}
                {:Deutsch "Ich weiß es nicht, aber lasst uns herausfinde."
                 :English "I do not know, but let's find out."}])

(def phrases {"Aber gut geht er mir." "But I am doing well."
              "Das schaffe ich." "I can do it."
              "Ich habe es nie gehabt." "I have never had it."
              "Ich liebe Lampe." "I love lamp."
              "Mein Hund fraß eine Katze." "My dog ate a cat."
              "Mein Löffel ist zu groß." "My spoon is to big."})

(def Adverb {"doch" "but"
             "ja" "yes"
             "jawohl" "yes sir"
             "nein" "no"
             "noch" "still"
             "nur" "only"
             "vielleicht" "maybe / perhaps"
             "wieder" "again"})

(def W-Frage {"Wann" "When"
              "Warum" "Why"
              "Was" "What"
              "Welche" "Which"
              "Wer" "Who"
              "Wie" "How"
              "Wo" "Where"})

(def Adjektiv {"arbeitslos" "unemployed"
               "allein" "alone"
               "falsch" "incorrect"
               "gemütlich" "cozy"
               "genau" "exactly"
               "günstig" "cheap"
               "richtig" "correct"
               "super" "great"
               "teuer" "expensive"
               "toll" "great"
               "verschiedene" "various"})

(def Lokale-Präpositionen {"auf" "on"
                           "an" "on the side of"
                           "neben" "near"
                           "vor" "in front"
                           "hinter" "behind"
                           "zwischen" "between"
                           "über" "over"
                           "unter" "under"
                           "in" "inside"})

; not sure where this goes yet
(def Kurzform {:dem {:im "in dem"
                     :am "am dem"
                     :beim "bei dem"
                     :vom "von dem"
                     :zum "zu dem"}
               :das {:ans "an das"
                     :ins "in das"
                     :aufs "auf das"
                     :fürs "für das"
                     :ums "um das"}})

; Some additional info:
; http://canoo.net/services/OnlineGrammar/Wort/Artikel/Artikelwort/Liste.html
(def Pronomina {"das" {:English "the"
                       :Maskulin {:Nominativ "der"
                                  :Akkusativ "den"
                                  :Dativ "dem"
                                  :Genitiv "dessen"}
                       :Neutrum {:Nominativ  "das"
                                 :Akkusativ "das"
                                 :Dativ "dem"
                                 :Genitiv "dessen"}
                       :Feminin {:Nominativ "die"
                                 :Akkusativ "die"
                                 :Dativ "der"
                                 :Genitiv "der"}
                       :Plural {:Nominativ "die"
                                :Akkusativ "die"
                                :Dativ "den"
                                :Genitiv "der"}}
                "dein" {:English "your"
                        :Maskulin {:Nominativ "dein"
                                   :Akkusativ "deinen"
                                   :Dativ "deinem"
                                   :Genitiv "deines"}
                        :Neutrum {:Nominativ "dein"
                                  :Akkusativ "dein"
                                  :Dativ "deinem"
                                  :Genitiv "deines"}
                        :Feminin {:Nominativ "deine"
                                  :Akkusativ "deine"
                                  :Dativ "deiner"
                                  :Genitiv "deiner"}
                        :Plural {:Nominativ "deine"
                                 :Akkusativ "deine"
                                 :Dativ "deinen"
                                 :Genitiv "deiner"}}
                "ein" {:English "a / an"
                       :Maskulin {:Nominativ "ein"
                                  :Akkusativ "einen"
                                  :Dativ "einem"
                                  :Genitiv "eines"}
                       :Neutrum {:Nominativ "ein"
                                 :Akkusativ "ein"
                                 :Dativ "einem"
                                 :Genitiv "eines"}
                       :Feminin {:Nominativ "eine"
                                 :Akkusativ "eine"
                                 :Dativ "einer"
                                 :Genitiv "einer"}
                       :Plural {:Nominativ nil
                                :Akkusativ nil
                                :Dativ nil
                                :Genitiv nil}}
                "kein" {:English "not"
                        :Maskulin {:Nominativ "kein"
                                   :Akkusativ "keinen"
                                   :Dativ "keinem"
                                   :Genitiv "keines"}
                        :Neutrum {:Nominativ "kein"
                                  :Akkusativ "kein"
                                  :Dativ "keinem"
                                  :Genitiv "keines"}
                        :Feminin {:Nominativ "keine"
                                  :Akkusativ "keine"
                                  :Dativ "keiner"
                                  :Genitiv "keiner"}
                        :Plural {:Nominativ "keine"
                                 :Akkusativ "keine"
                                 :Dativ "keinen"
                                 :Genitiv "keiner"}}
                "sich" {:English "themselves"
                        :Maskulin {:Nominativ nil
                                   :Akkusativ "sich"
                                   :Dativ "sich"
                                   :Genitiv "seiner"}
                        :Neutrum {:Nominativ nil
                                  :Akkusativ "sich"
                                  :Dativ "sich"
                                  :Genitiv "seiner"}
                        :Feminin {:Nominativ nil
                                  :Akkusativ "sich"
                                  :Dativ "sich"
                                  :Genitiv "ihrer"}
                        :Plural {:Nominativ nil
                                 :Akkusativ "sich"
                                 :Dativ "sich"
                                 :Genitiv "ihrer"}}
                "sein" {:English "be"
                        :Maskulin {:Nominativ "sein"
                                   :Akkusativ "seinen"
                                   :Dativ "seinem"
                                   :Genitiv "sines"}
                        :Neutrum {:Nominativ "sein"
                                  :Akkusativ "sein"
                                  :Dativ "seinem"
                                  :Genitiv "seines"}
                        :Feminin {:Nominativ "seine"
                                  :Akkusativ "seine"
                                  :Dativ "seiner"
                                  :Genitiv "seiner"}
                        :Plural {:Nominativ "seine"
                                 :Akkusativ "seine"
                                 :Dativ "seinen"
                                 :Genitiv "seiner"}}})

(def Verben (into (sorted-map)
                  {"arbeiten" {:English "working"
                               :Hilfsverb :haben
                               :Präsens {:ich "arbeite"
                                         :du "arbeitest"
                                         :er-sie-es "arbeitet"
                                         :wir "arbeiten"
                                         :ihr "arbeitet"
                                         :sie-Sie "arbeiten"}
                               :Perfekt "gearbeitet"}
                   "aussteigen" {:English "exit / offboarding"
                                 :Hilfsverb :sein
                                 :Präsens {:ich "steige aus"
                                           :du "steigst aus"
                                           :er-sie-es "steigt aus"
                                           :wir "steigen aus"
                                           :ihr "steigt aus"
                                           :sie-Sie "steigen aus"}
                                 :Perfekt "ausgestiegen"}
                   "bitten" {:English "you are welcome"
                             :Hilfsverb :haben
                             :Präsens {:ich "bitte"
                                       :du "bittest"
                                       :er-sie-es "bittet"
                                       :wir "bitten"
                                       :ihr "bittet"
                                       :sie-Sie "bitten"}
                             :Perfekt "gebeten"}
                   "brauchen" {:English "need"
                               :Hilfsverb :haben
                               :Präsens {:ich "brauche"
                                         :du "brauchst"
                                         :er-sie-es "braucht"
                                         :wir "brauchen"
                                         :ihr "braucht"
                                         :sie-Sie "brauchen"}
                               :Perfekt "gebraucht"}
                   "einsteigen" {:English "enter / boarding"
                                 :Hilfsverb :sein
                                 :Präsens {:ich "steige ein"
                                           :du "steigst ein"
                                           :er-sie-es "steigt ein"
                                           :wir "steigen ein"
                                           :ihr "steigt ein"
                                           :sie-Sie "steigen ein"}
                                 :Perfekt "eingestiegen"}
                   "einen" {:English "an"
                            :Hilfsverb :haben
                            :Präsens {:ich "eine"
                                      :du "einst"
                                      :er-sie-es "eint"
                                      :wir "einen"
                                      :ihr "eint"
                                      :sie-Sie "einen"}
                            :Perfekt "geeint"}
                   "essen" {:English "eat"
                            :Hilfsverb :haben
                            :Präsens {:ich "esse"
                                      :du "isst"
                                      :er-sie-es "isst"
                                      :wir "essen"
                                      :ihr "esst"
                                      :sie-Sie "essen"}
                            :Perfekt "gegessen"}
                   "fahren" {:English "ride"
                             :Hilfsverb :haben
                             :Präsens {:ich "fahre"
                                       :du "fährst"
                                       :er-sie-es "fährt"
                                       :wir "fahren"
                                       :ihr "fahrt"
                                       :sie-Sie "fahren"}
                             :Perfekt "gefahren"}
                   "faulen" {:English "lazy"
                             :Hilfsverb :haben
                             :Präsens {:ich "faule"
                                       :du "faulst"
                                       :er-sie-es "fault"
                                       :wir "faulen"
                                       :ihr "fault"
                                       :sie-Sie "faulen"}
                             :Perfekt "gefault"}

                   "finden" {:English "find"
                             :Hilfsverb :haben
                             :Präsens {:ich "finde"
                                       :du "findest"
                                       :er-sie-es "findet"
                                       :wir "finden"
                                       :ihr "findet"
                                       :sie-Sie "finden"}
                             :Perfekt "gefunden"}
                   "geben" {:English "give"
                            :Hilfsverb :haben
                            :Präsens {:ich "gebe"
                                      :du "gibst"
                                      :er-sie-es "gibt"
                                      :wir "geben"
                                      :ihr "gebt"
                                      :sie-Sie "geben"}
                            :Perfekt "gegeben"}
                   "gehen" {:English "go"
                            :Hilfsverb :sein
                            :Präsens {:ich "gehe"
                                      :du "gehst"
                                      :er-sie-es "geht"
                                      :wir "gehen"
                                      :ihr "geht"
                                      :sie-Sie "gehen"}
                            :Perfekt "gegangen"}

                   "gesunden" {:English "healthy"
                               :Hilfsverb :sein
                               :Präsens {:ich "gesunde"
                                         :du "gesundest"
                                         :er-sie-es "gesundet"
                                         :wir "gesunden"
                                         :ihr "gesundet"
                                         :sie-Sie "gesunden"}
                               :Perfekt "gesundet"}

                   "glauben" {:English "believe"
                              :Hilfsverb :haben
                              :Präsens {:ich "glabe"
                                        :du "glaubst"
                                        :er-sie-es "glaubt"
                                        :wir "glauben"
                                        :ihr "glaubt"
                                        :sie-Sie "glauben"}
                              :Perfekt "geglaubt"}
                   "haben" {:English "have"
                            :Hilfsverb :haben
                            :Präsens {:ich "habe"
                                      :du "hast"
                                      :er-sie-es "hat"
                                      :wir "haben"
                                      :ihr "habt"
                                      :sie-Sie "haben"}
                            :Perfekt "gehabt"}
                   "halten" {:English "hold"
                             :Hilfsverb :haben
                             :Präsens {:ich "halte"
                                       :du "hälst"
                                       :er-sie-es "hält"
                                       :wir "halten"
                                       :ihr "haltet"
                                       :sie-Sie "halten"}
                             :Perfekt "gehalten"}
                   "heißen" {:English "called"
                             :Hilfsverb :haben
                             :Präsens {:ich "heiße"
                                       :du "heißt"
                                       :er-sie-es "heißt"
                                       :wir "heißen"
                                       :ihr "heißt"
                                       :sie-Sie "heißen"}
                             :Perfekt "gehißen"}
                   "holen" {:English "fetch"
                            :Hilfsverb :haben
                            :Präsens {:ich "hole"
                                      :du "holst"
                                      :er-sie-es "holt"
                                      :wir "holen"
                                      :ihr "holt"
                                      :sie-Sie "holen"}
                            :Perfekt "geholt"}
                   "können" {:English "can"
                             :Hilfsverb :haben
                             :Präsens {:ich "kann"
                                       :du "kannst"
                                       :er-sie-es "kann"
                                       :wir "können"
                                       :ihr "könnt"
                                       :sie-Sie "können"}
                             :Perfekt "gekonnt"}
                   "kosten" {:English "cost"
                             :Hilfsverb :haben
                             :Präsens {:ich "koste"
                                       :du "kostest"
                                       :er-sie-es "kostet"
                                       :wir "kosten"
                                       :ihr "kostet"
                                       :sie-Sie "kosten"}
                             :Perfekt "gekostet"}
                   "laufen" {:English "run"
                             :Hilfsverb :sein
                             :Präsens {:ich "laufe"
                                       :du "läufst"
                                       :er-sie-es "läuft"
                                       :wir "laufen"
                                       :ihr "lauft"
                                       :sie-Sie "laufen"}
                             :Perfekt "gelaufen"}
                   "leben" {:English "live"
                            :Hilfsverb :haben
                            :Präsens {:ich "lebe"
                                      :du "lebst"
                                      :er-sie-es "lebt"
                                      :wir "leben"
                                      :ihr "lebt"
                                      :sie-Sie "leben"}
                            :Perfekt "gelebt"}
                   "lesen" {:English "read"
                            :Hilfsverb :haben
                            :Präsens {:ich "lese"
                                      :du "liest"
                                      :er-sie-es "liest"
                                      :wir "lesen"
                                      :ihr "lest"
                                      :sie-Sie "lesen"}
                            :Perfekt "gelesen"}
                   "machen" {:English "do / make"
                             :Hilfsverb :haben
                             :Präsens {:ich "mache"
                                       :du "machst"
                                       :er-sie-es "macht"
                                       :wir "machen"
                                       :ihr "macht"
                                       :sie-Sie "machen"}
                             :Perfekt "gemacht"}
                   "mahlen" {:English "grind"
                             :Hilfsverb :haben
                             :Präsens {:ich "mahle"
                                       :du "mahlst"
                                       :er-sie-es "mahlt"
                                       :wir "mahlen"
                                       :ihr "mahlt"
                                       :sie-Sie "mahlen"}
                             :Perfekt "gemahlen"}
                   "meinen" {:English "mine"
                             :Hilfsverb :haben
                             :Präsens {:ich "meine"
                                       :du "meinst"
                                       :er-sie-es "meint"
                                       :wir "meinen"
                                       :ihr "meint"
                                       :sie-Sie "meinen"}
                             :Perfekt "gemeint"}
                   "möchten" {:English "want"
                              :Hilfsverb :haben
                              :Präsens {:ich "möchte"
                                        :du "möchtest"
                                        :er-sie-es "möchte"
                                        :wir "möchten"
                                        :ihr "möchtet"
                                        :sie-Sie "möchten"}
                              :Perfekt "gemocht"}
                   "mögen" {:English "like"
                            :Hilfsverb :haben
                            :Präsens {:ich "mag"
                                      :du "magst"
                                      :er-sie-es "mag"
                                      :wir "mögen"
                                      :ihr "mögt"
                                      :sie-Sie "mögen"}
                            :Perfekt "gemocht"}
                   "nehmen" {:English "take"
                             :Hilfsverb :haben
                             :Präsens {:ich "nehme"
                                       :du "nimmst"
                                       :er-sie-es "nimmt"
                                       :wir "nehmen"
                                       :ihr "nehmt"
                                       :sie-Sie "nehmen"}
                             :Perfekt "genommen"}
                   "öffnen" {:English "open"
                               :Hilfsverb :haben
                               :Präsens {:ich "öffne"
                                         :du "öffnest"
                                         :er-sie-es "öffnet"
                                         :wir "öffnen"
                                         :ihr "öffnet"
                                         :sie-Sie "öffnen"}
                               :Perfekt "geöffnet"}
                   "raten" {:English "guess"
                            :Hilfsverb :haben
                            :Präsens {:ich "rate"
                                      :du "rätst"
                                      :er-sie-es "rät"
                                      :wir "raten"
                                      :ihr "ratet"
                                      :sie-Sie "raten"}
                            :Perfekt "geraten"}
                   "räumen" {:English "clean"
                               :Hilfsverb :haben
                               :Präsens {:ich "räume"
                                         :du "räumst"
                                         :er-sie-es "räumt"
                                         :wir "räumen"
                                         :ihr "räumt"
                                         :sie-Sie "räumen"}
                               :Perfekt "geräumt"}

                   "sagen" {:English "say"
                            :Hilfsverb :haben
                            :Präsens {:ich "sage"
                                      :du "sagst"
                                      :er-sie-es "sagt"
                                      :wir "sagen"
                                      :ihr "sagt"
                                      :sie-Sie "sagen"}
                            :Perfekt "gesagt"}
                   "sehen" {:English "see"
                            :Hilfsverb :haben
                            :Präsens {:ich "sehe"
                                      :du "siehst"
                                      :er-sie-es "sieht"
                                      :wir "sehen"
                                      :ihr "seht"
                                      :sie-Sie "sehen"}
                            :Perfekt "gesehen"}
                   "sein" {:English "am"
                           :Hilfsverb :sein
                           :Präsens {:ich "bin"
                                     :du "bist"
                                     :er-sie-es "ist"
                                     :wir "sind"
                                     :ihr "seid"
                                     :sie-Sie "sind"}
                           :Perfekt "gewesen"}
                   "sprechen" {:English "speak"
                               :Hilfsverb :haben
                               :Präsens {:ich "spreche"
                                         :du "sprichst"
                                         :er-sie-es "spricht"
                                         :wir "sprechen"
                                         :ihr "sprecht"
                                         :sie-Sie "sprechen"}
                               :Perfekt "gesprochen"}
                   "steigen" {:English "climb"
                              :Hilfsverb :sein
                              :Präsens {:ich "steige"
                                        :du "steigst"
                                        :er-sie-es "steigt"
                                        :wir "steigen"
                                        :ihr "steigt"
                                        :sie-Sie "steigen"}
                              :Perfekt "gestiegen"}
                   "studieren" {:English "study"
                                :Hilfsverb :haben
                                :Präsens {:ich "studiere"
                                          :du "studierst"
                                          :er-sie-es "studiert"
                                          :wir "studieren"
                                          :ihr "studiert"
                                          :sie-Sie "studieren"}
                                :Perfekt "studiert"}
                   "suchen" {:English "search"
                             :Hilfsverb :haben
                             :Präsens {:ich "suche"
                                       :du "suchst"
                                       :er-sie-es "sucht"
                                       :wir "suchen"
                                       :ihr "sucht"
                                       :sie-Sie "suchen"}
                             :Perfekt "gesucht"}
                   "treffen" {:English "meet"
                              :Hilfsverb :haben
                              :Präsens {:ich "treffe"
                                        :du "triffst"
                                        :er-sie-es "trifft"
                                        :wir "treffen"
                                        :ihr "trefft"
                                        :sie-Sie "treffen"}
                              :Perfekt "getroffen"}
                   "treten" {:English "step"
                             :Hilfsverb :haben
                             :Präsens {:ich "trete"
                                       :du "trittst"
                                       :er-sie-es "tritt"
                                       :wir "treten"
                                       :ihr "tretet"
                                       :sie-Sie "treten"}
                             :Perfekt "getreten"}
                   "umsteigen" {:English "change"
                                :Hilfsverb :sein
                                :Präsens {:ich "steige um"
                                          :du "steigst um"
                                          :er-sie-es "steigt um"
                                          :wir "steigen um"
                                          :ihr "steigt um"
                                          :sie-Sie "steigen um"}
                                :Perfekt "umgestiegen"}
                   "unterscheiden" {:English "difference"
                                    :Hilfsverb :haben
                                    :Präsens {:ich "unterscheide"
                                              :du "unterscheidest"
                                              :er-sie-es "unterscheidet"
                                              :wir "unterscheiden"
                                              :ihr "unterscheidet"
                                              :sie-Sie "unterscheiden"}
                                    :Perfekt "unterscheiden"}
                   "vergessen" {:English "forget"
                                :Hilfsverb :haben
                                :Präsens {:ich "vergesse"
                                          :du "vergisst"
                                          :er-sie-es "vergisst"
                                          :wir "vergessen"
                                          :ihr "vergesst"
                                          :sie-Sie "vergessen"}
                                :Perfekt "vergessen"}
                   "verheiraten" {:English "married"
                                  :Hilfsverb :haben
                                  :Präsens {:ich "verheirate"
                                              :du "verheiratest"
                                              :er-sie-es "verhieratet"
                                              :wir "verheiraten"
                                              :ihr "verheiratet"
                                              :sie-Sie "verheiraten"}
                                  :Perfekt "verheiratet"}
                   "werden" {:English "become"
                             :Hilfsverb :sein
                             :Präsens {:ich "werde"
                                       :du "wirst"
                                       :er-sie-es "wird"
                                       :wir "werden"
                                       :ihr "werdet"
                                       :sie-Sie "werden"}
                             :Perfekt "geworden"}

                   "wissen" {:English "know"
                             :Hilfsverb :haben
                             :Präsens {:ich "weiß"
                                       :du "weißt"
                                       :er-sie-es "weiß"
                                       :wir "wissen"
                                       :ihr "wisst"
                                       :sie-Sie "wissen"}
                             :Perfekt "gewusst"}
                   "wohnen" {:English "live"
                             :Hilfsverb :haben
                             :Präsens {:ich "wohne"
                                       :du "wohnst"
                                       :er-sie-es "wohnt"
                                       :wir "wohnen"
                                       :ihr "wonnt"
                                       :sie-Sie "wohnen"}
                             :Perfekt "gewohnt"}
                   "zahlen" {:English "Pay"
                             :Hilfsverb :haben
                             :Präsens {:ich "zahle"
                                       :du "zhalst"
                                       :er-sie-es "zahlt"
                                       :wir "zahlen"
                                       :ihr "zahlt"
                                       :sie-Sie "zahlen"}
                             :Perfekt "gezahlt"}}))



; want a way to resolve the keyword, but still mark it as Plural Nomen
(def plural-artikel :die)

(def Nomina (into (sorted-map)
                  {"Alter" {:Artikel :das
                            :English "Age"}
                   "Ampel" {:Artikel :die
                            :English "Traffic Light"}
                   "Anfang" {:Artikel :der
                             :English "Beginning"}
                   "Angebot" {:Artikel :das
                              :English "offer"}
                   "Antwort" {:Artikel :die
                              :English "Answer"}
                   "Arbeitgeber" {:Artikel :der
                                  :English "Employer"}
                   "Artikel" {:Artikel :der
                              :English "Item"}
                   "Arzt" {:Artikel :der
                           :English "Doctor"}
                   "Ausbildung" {:Artikel :die
                                 :English "Training"}
                   "Auto" {:Artikel :das
                           :English "Car"}
                   "Ausnahme" {:Artikel :die
                               :English "Exception"}
                   "Bank" {:Artikel :die
                           :English "Bank"}
                   "Bahnhof" {:Artikel :der
                              :English "Train station"}
                   "Balkon" {:Artikel :der
                             :English "Balcony"}
                   "Baum" {:Artikel :der
                           :English "Tree"}
                   "Beruf" {:Artikel :der
                            :English "Profession"}
                   "Beschaffenheit" {:Artikel :die
                                     :English "Composition"}
                   "Bett" {:Artikel :das
                           :English "Bed"}
                   "Bezirk" {:Artikel :der
                             :English "District"}
                   "Bild" {:Artikel :das
                           :English "Image"}
                   "Brücke" {:Artikel :die
                             :English "Bridge"}
                   "Bruder" {:Artikel :der
                             :English "Brother"}
                   "Bürgeramt" {:Artikel :das
                                :English "Town hall"}
                   "Café" {:Artikel :das
                           :English "Cafe"}
                   "Computer" {:Artikel :der
                               :English "Computer"}
                   "Couch" {:Artikel :die
                            :English "Couch"}
                   "Disco" {:Artikel :die
                            :English "Disco"}
                   "Dom" {:Artikel :der
                          :English "Cathedral"}
                   "Ehe" {:Artikel :die
                          :English "Marriage"}
                   "Eltern" {:Artikel plural-artikel
                             :English "Parents"}
                   "Endung" {:Artikel :die
                             :English "Ending"}
                   "Enkel" {:Artikel :der
                            :English "Grandson"}
                   "Enkelin" {:Artikel :die
                              :English "Granddaughter"}
                   "Ernährung" {:Artikel :die
                                :English "Nutrition"}
                   "Fahrrad" {:Artikel :das
                              :English "Bicycle"}
                   "Fall" {:Artikel :der
                           :English "case"}
                   "Familie" {:Artikel :die
                              :English "Family"}
                   "Familienstand" {:Artikel :der
                                    :English "Marital status"}
                   "Farbe" {:Artikel :die
                            :English "Colour"}
                   "Fenster" {:Artikel :das
                              :English "Window"}
                   "Flur" {:Artikel :der
                           :English "Hallway"}
                   "Fluss" {:Artikel :der
                            :English "River"}
                   "Form" {:Artikel :die
                           :English "Shape"}
                   "Frage" {:Artikel :die
                            :English "Question"}
                   "Frau" {:Artikel :die
                           :English "Woman"}
                   "Freund" {:Artikel :der
                             :English "Friend"}
                   "Garage" {:Artikel :die
                             :English "Garage"}
                   "Garderobe" {:Artikel :die
                                :English "Cloak room"}
                   "Garten" {:Artikel :der
                             :English "Garden"}
                   "Gegenstände" {:Artikel :der
                                  :English "Object"}
                   "Geld" {:Artikel :das
                           :English "Money"}
                   "Genus" {:Artikel :das
                            :English "Grammatical gender"}
                   "Geschwister" {:Artikel plural-artikel
                                  :English "Siblings"}
                   "Glas" {:Artikel :das
                           :English "Glass"}
                   "Handy" {:Artikel :das
                            :English "Mobile phone"}
                   "Haus" {:Artikel :das
                           :English "House"}
                   "Heilkräuter" {:Artikel :das
                                  :English "Medicinal Herbs"}
                   "Heim" {:Artikel :das
                           :English "Home"}
                   "Hochshule" {:Artikel :die
                                :English "College"}
                   "Holz" {:Artikel :das
                           :English "Wood"}
                   "Hotel" {:Artikel :das
                            :English "Hotel"}
                   "Jahr" {:Artikel :das
                           :English "Year"}
                   "Job" {:Artikel :der
                          :English "Job"}
                   "Kilometer" {:Artikel :der
                                :English "Kilometer"}
                   "Kind" {:Artikel :das
                           :English "Child"}
                   "Kinder" {:Artikel :das
                             :English "Children"}
                   "Kinderzimmer" {:Artikel :das
                                   :English "Childrens Room"}
                   "Kleiderschrank" {:Artikel :der
                                     :English "Wardrobe"}
                   "Kneipe" {:Artikel :die
                             :English "Bar"}
                   "Kollege" {:Artikel :der
                              :English "Colleague"}
                   "Küche" {:Artikel :die
                            :English "Kitchen"}
                   "Künstler" {:Artikel :der
                               :English "Artist"}
                   "Kunststoff" {:Artikel :das
                                 :English "Plastic"}
                   "Lampe" {:Artikel :die
                            :English "Lamp"}
                   "Lebensmittel" {:Artikel :das
                                   :English "Food"}
                   "Loch" {:Artikel :das
                           :English "Hole"}
                   "Mann" {:Artikel :der
                           :English "Man"}
                   "Metall" {:Artikel :das
                             :English "Metal"}
                   "Material" {:Artikel :das
                               :English "Material"}
                   "Matrix" {:Artikel :die
                             :English "Matrix"}
                   "Meter" {:Artikel :der
                            :English "Meter"}
                   "Milch" {:Artikel :die
                            :English "Milk"}
                   "Mitte" {:Artikel :die
                            :English "Center"}
                   "Möbel" {:Artikel plural-artikel
                            :English "Furniture"}
                   "Mobilität" {:Artikel :die
                                :English "Mobility"}
                   "Münze" {:Artikel :die
                            :English "Coin"}
                   "Museum" {:Artikel :das
                             :English "Museum"}
                   "Mutter" {:Artikel :die
                             :English "Mother"}
                   "Musik" {:Artikel :die
                            :English "Music"}
                   "Nähe" {:Artikel :die
                           :English "Close / Near"}
                   "Nomen" {:Artikel :das
                            :English "Noun"}
                   "Oma" {:Artikel :die
                          :English "Grandmother"}
                   "Opa" {:Artikel :der
                          :English "Grandfather"}
                   "Papier" {:Artikel :das
                             :English "Paper"}
                   "Partner" {:Artikel :der
                              :English "Partner"}
                   "Plan" {:Artikel :der
                           :English "Plan"}
                   "Plastik" {:Artikel :das
                              :English "Plastic"}
                   "Pluralendung" {:Artikel :die
                                   :English "Plural ending"}
                   "Polizei" {:Artikel :die
                              :English "Police"}
                   "Post" {:Artikel :die
                           :English "Post Office"}
                   "Praktikum" {:Artikel :das
                                :English "Internship"}
                   "Preis" {:Artikel :der
                            :English "Price"}
                   "Rätsel" {:Artikel :das
                             :English "Riddle"}
                   "Rathaus" {:Artikel :das
                              :English "Town hall"}
                   "Regel" {:Artikel :die
                            :English "Rule"}
                   "Restaurant" {:Artikel :das
                                 :English "Restaurant"}
                   "Salbei" {:Artikel :der
                             :English "Sage"}
                   "Satellit" {:Artikel :der
                               :English "Satellite"}
                   "Satz" {:Artikel :der
                           :English "Sentence"}
                   "Schlafzimmer" {:Artikel :das
                                   :English "Bedroom"}
                   "Schrank" {:Artikel :der
                              :English "Closet"}
                   "Schritt" {:Artikel :der
                              :English "Step"}
                   "Schule" {:Artikel :die
                             :English "School"}
                   "Schwester" {:Artikel :die
                                :English "Sister"}
                   "Seife" {:Artikel :die
                            :English "Soap"}
                   "Sessel" {:Artikel :der
                             :English "Armchair"}
                   "Sport" {:Artikel :der
                            :English "Sport"}
                   "Sportart" {:Artikel :die
                               :English "Kind of sport"}
                   "Straße" {:Artikel :die
                             :English "Street"}
                   "Stelle" {:Artikel :die
                             :English "Place / Spot"}
                   "Stuhl" {:Artikel :der
                            :English "Chair"}
                   "Sofa" {:Artikel :das
                           :English "Sofa"}
                   "Sohn" {:Artikel :der
                           :English "Son"}
                   "Sonderangebot" {:Artikel :das
                                    :English "Special Offer"}
                   "Stadt" {:Artikel :die
                            :English "City"}
                   "Stadtmitte" {:Artikel :die
                                 :English "City Center"}
                   "Stadtplan" {:Artikel :der
                                :English "Map"}
                   "Stunde" {:Artikel :die
                             :English "Hour"}
                   "Schwimmbad" {:Artikel :das
                                 :English "Swimming Pool"}
                   "Sprachschule" {:Artikel :die
                                   :English "Language School"}
                   "Supermarkt" {:Artikel :der
                                 :English "Supermarket"}
                   "Szene" {:Artikel :die
                            :English "Scene"}
                   "Tasche" {:Artikel :die
                             :English "Bag"}
                   "Telefonummer" {:Artikel :der
                                   :English "Telephone number"}
                   "Teppich" {:Artikel :der
                              :English "Carpet"}
                   "Tisch" {:Artikel :der
                            :English "Table"}
                   "Tochter" {:Artikel :die
                              :English "Daughter"}
                   "Treppe" {:Artikel :die
                             :English "Stairs"}
                   "Tur" {:Artikel :die
                          :English "Door"}
                   "Übersetzung" {:Artikel :die
                                  :English "Translation"}
                   "Universität" {:Artikel :die
                                  :English "University"}
                   "Uhr" {:Artikel :die
                          :English "Clock / Watch"}
                   "Urlaub" {:Artikel :der
                             :English "Vacation"}
                   "Vater" {:Artikel :der
                            :English "Father"}
                   "Verb" {:Artikel :der
                           :English "Verb"}
                   "Verkehr" {:Artikel :der
                              :English "Transport"}
                   "Vitrine" {:Artikel :die
                              :English "Glass Cabinet / Display case"}
                   "Wand" {:Artikel :die
                           :English "Wall"}
                   "Wandschrank" {:Artikel :der
                                  :English "Cupboard"}
                   "Weg" {:Artikel :der
                          :English "Way"}
                   "Wegbeschreibung" {:Artikel :die
                                      :English "Directions"}
                   "Wohnung" {:Artikel :die
                              :English "Apartment"}
                   "Wohnzimmer" {:Artikel :das
                                 :English "Living room"}
                   "Wunsch" {:Artikel :der
                             :English "Wish"}
                   "Zeit" {:Artikel :die
                           :English "Time"}
                   "Zentrum" {:Artikel :das
                              :English "Center"}}))

(def default-db {:Fakten {:Nomina Nomina
                          :Pronomina Pronomina
                          :Verben Verben}
                 :routes/aktiv-szene nil
                 :navigation/menü-aktiv false})
