(ns deutsch-lernkarten.db
  (:require [clojure.spec :as spec]
            [clojure.string :as string]))

(def why-learn [{:Deutsch "Die Nomen und Artikel sind für immer zusammen. Es gibt keine Alternative."
                 :English "The noun and article are forever together. There is no alternative."}
                {:Deutsch "Es gibt verschiedene Pluralendungen. Die Pluralendungen muss man auswendig lernen."
                 :English "There are different plural endings. The plural endings you must learn by heart."}
                {:Deutsch "Ich will spreche fließend Deutsch."
                 :English "I want to speak fluent German."}
                {:Deutsch "Ich weiß es nicht, aber lasst uns herausfinde."
                 :English "I do not know, but let's find out."}])

(def phrases (into (sorted-map)
                   {"Aber gut geht er mir." "But I am doing well."
                    "Das schaffe ich." "I can do it."
                    "Ich bin hell wach!" "I am so awake!"
                    "Ich habe es nie gehabt." "I have never had it."
                    "Ich liebe Lampe." "I love lamp."
                    "Mein Hund fraß eine Katze." "My dog ate a cat."
                    "Mein Löffel ist zu groß." "My spoon is to big."}))

(def Adverb (into (sorted-map)
                  {"doch" "but"
                   "ja" "yes"
                   "jawohl" "yes sir"
                   "nein" "no"
                   "noch" "still"
                   "nur" "only"
                   "vielleicht" "maybe / perhaps"
                   "wieder" "again"}))

(def W-Frage (into (sorted-map)
                   {"Wann" "When"
                    "Warum" "Why"
                    "Was" "What"
                    "Welche" "Which"
                    "Wer" "Who"
                    "Wie" "How"
                    "Wo" "Where"}))

(def Adjektiv (into (sorted-map)
                    {"arbeitslos" "unemployed"
                     "allein" "alone"
                     "erfolgreiches" "successful"
                     "falsch" "incorrect"
                     "gemütlich" "cozy"
                     "genau" "exactly"
                     "günstig" "cheap"
                     "richtig" "correct"
                     "schon" "already"
                     "schwach" "weak"
                     "super" "great"
                     "teuer" "expensive"
                     "toll" "great"
                     "verschiedene" "various"
                     "wichtige" "important"}))

(def Lokale-Präpositionen (into (sorted-map)
                                {"auf" "on"
                                 "an" "on the side of"
                                 "neben" "near"
                                 "vor" "in front"
                                 "hinter" "behind"
                                 "zwischen" "between"
                                 "über" "over"
                                 "unter" "under"
                                 "in" "inside"}))

(def Temporale-Präpositionen (into (sorted-map)
                                   {"ab" {:English "as of" :Beispiel {:Deutsch "Keine weiteren Berichte ab 10:00 Uhr."
                                                                      :English "No further reports as of 10:00 am."}}
                                    "an" {:English "at" :Beispiel {:Deutsch "An welchem Tag hast du Geburtstag?"
                                                                   :English "At which day is your birthday?"}}
                                    "auf" {:English "on" :Beispiel {:Deutsch "Lass uns auf dem Bahnsteig am Hauptbahnhof treffen."
                                                                    :English "Let's meet on the platform at the Central station."}}
                                    "aus" {:English "from" :Beispiel {:Deutsch "Dieser Stil der Moderne stammt aus der Bauhauszeit."
                                                                      :English "This style of modernism is from the Bauhaus period."}}
                                    "außerhalb" {:English "outside" :Beispiel {:Deutsch "Dieses Produkt wird außerhalb dieser Saison versandt."
                                                                               :English "This product will be shipped outside this season."}}
                                    "bei" {:English "near" :Beispiel {:Deutsch "Bei meiner Ankunft habe ich eine Blumendusche erhalten."
                                                                      :English "On my arrival I was showered in flowers."}}
                                    "binnen" {:English "within" :Beispiel {:Deutsch "Binnen von drei Wochen hatte das Team eine stabile Demo produziert."
                                                                           :English "Within three weeks the team had produced a stable demo."}}
                                    "bis" {:English "until" :Beispiel {:Deutsch "Warten Sie, bis ich komme."
                                                                       :English "Wait until I come."}}
                                    "für" {:English "for" :Beispiel {:Deutsch "Der Schnee bleibt für vier Monate."
                                                                     :English "The snow stays for four months."}}
                                    "gegen" {:English "around" :Beispiel {:Deutsch "Wir kommen gegen 7:00 Uhr an."
                                                                          :English "We arrive around 7:00 am"}}
                                    "in" {:English "in" :Beispiel {:Deutsch "Die show beginnt in einer Stunde."
                                                                   :English "The show begins in one hour."}}
                                    "innerhalb" {:English "within" :Beispiel {:Deutsch "Sie kommen innerhalb von einer Stunde an."
                                                                              :English "They will arrive within one hour"}}
                                    "mit" {:English "at" :Beispiel {:Deutsch "Mit einem Mal waren wir von einem Kissenkampf umgeben."
                                                                    :English "All of a sudden we were surrounded by a pillow fight."}}
                                    "nach" {:English "after" :Beispiel {:Deutsch "Nach der Show werden wir für ein Bier gehen."
                                                                        :English "After the the show we will go for a beer."}}
                                    "seit" {:English "since" :Beispiel {:Deutsch "Ich habe hier gelebt, seit ich zwanwig war."
                                                                        :English "I have lived here since I was twenty."}}
                                    "über" {:English "over" :Beispiel {:Deutsch "Es wurde über das Internet veröffentlicht."
                                                                       :English "It was transmitted all over the Internet."}}
                                    "um" {:English "around" :Beispiel {:Deutsch "Bitte kommen Sie (genau) um zehn."
                                                                       :English "Please come at ten (sharp)."}}
                                    "unter" {:English "under" :Beispiel {:Deutsch "Der erste Fahrer beendete das Tenne unter der zwei Stunden Marke."
                                                                         :English "The first driver finished the race under the two hour mark."}}
                                    "von" {:English "from" :Beispiel {:Deutsch "Die Hacker kamen von Berlin nach Hamburg for the C3-Konferenz."
                                                                      :English "The Hackers came from Berlin to Hamburg for the C3 conference."}}
                                    "vor" {:English "before" :Beispiel {:Deutsch "Vor ihrem zehnten Geburtstag wurden ihre Fähigkeiten aufgedeckt."
                                                                        :English "Before her tenth birthday her skills were revealed."}}
                                    "während" {:English "during" :Beispiel {:Deutsch "Ich werde es während der Woche liefern."
                                                                            :English "I will deliver it during the week."}}
                                    "zu" {:English "at" :Beispiel {:Deutsch "Wir kamen zu einer späten Stunde an."
                                                                   :English "We arrived at a late hour."}}
                                    "zwischen" {:English "between" :Beispiel {:Deutsch "Der Landezeit wird zwischen ein und zwei Stunden geschätzt."
                                                                              :English "The landing time is estimated between one and two hours."}}}))

(def Kurzform-Präpositionen {:dem {:im "in dem"
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
; http://canoo.net/services/OnlineGrammar/InflectionRules/FRegeln-P/Pron-Pers.html
(def PersonalPronomen {:Singular {:Erste-Person {:Nominativ "ich"
                                                 :Akkusativ "mich"
                                                 :Dativ "mir"
                                                 :Genitiv "meiner"}
                                  :Zweite-Person {:Nominativ "du"
                                                  :Akkusativ "dich"
                                                  :Dativ "dir"
                                                  :Genitiv "deiner"}
                                  :Dritte-Person-Maskulin {:Nominativ "er"
                                                           :Akkusativ "ihn"
                                                           :Dativ "ihm"
                                                           :Genitiv "seiner"}
                                  :Dritte-Person-Neutrum {:Nominativ "es"
                                                          :Akkusativ "es"
                                                          :Dativ "ihm"
                                                          :Genitiv "seiner"}
                                  :Dritte-Person-Feminin {:Nominativ "sie"
                                                          :Akkusativ "sie"
                                                          :Dativ "ihr"
                                                          :Genitiv "ihrer"}}
                       :Plural {:Erste-Person {:Nominativ "wir"
                                               :Akkusativ "uns"
                                               :Dativ "uns"
                                               :Genitiv "unser"}
                                :Zweite-Person {:Nominativ "ihr"
                                                :Akkusativ "euch"
                                                :Dativ "euch"
                                                :Genitiv "euer"}
                                :Dritte-Person {:Nominativ "sie"
                                                :Akkusativ "sie"
                                                :Dativ "ihnen"
                                                :Genitiv "ihrer"}}})

(def Komposition {:Verben-plus-Nomen (into (sorted-map)
                                           {"Bestellanforderung" "Ordering Requirement"})})

; Some additional info:
; http://canoo.net/services/OnlineGrammar/Wort/Artikel/Artikelwort/Liste.html
(def Pronomina (into (sorted-map)
                     {"das" {:English "the"
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
                                       :Genitiv "seiner"}}}))

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
                   "bekommen" {:English "get"
                               :Hilfsverb :haben-sein
                               :Präsens {:ich "bekomme"
                                         :du "bekommst"
                                         :er-sie-es "bekommt"
                                         :wir "bekommen"
                                         :ihr "bekommt"
                                         :sie-Sie "bekommen"}
                               :Perfekt "bekommen"}
                   "bestellen" {:English "to order"
                                :Hilfsverb :haben
                                :Präsens {:ich "bestelle"
                                          :du "bestellst"
                                          :er-sie-es "bestellt"
                                          :wir "bestellen"
                                          :ihr "bestellt"
                                          :sie-Sie "bestellen"}
                                :Perfekt "bestellt"}

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
                   "fehlen" {:English "missing"
                             :Hilfsverb :haben
                               :Präsens {:ich "fehle"
                                         :du "fehlst"
                                         :er-sie-es "fehlt"
                                         :wir "fehlen"
                                         :ihr "fehlt"
                                         :sie-Sie "fehlen"}
                               :Perfekt "gefehlt"}

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
                   "gefallen" {:English "like"
                               :Hilfsverb :haben
                               :Präsens {:ich "gefalle"
                                         :du "gefällst"
                                         :er-sie-es "gefällt"
                                         :wir "gefallen"
                                         :ihr "gefallt"
                                         :sie-Sie "gefallen"}
                               :Perfekt "gefallen"}
                   "gehen" {:English "go"
                            :Hilfsverb :sein
                            :Präsens {:ich "gehe"
                                      :du "gehst"
                                      :er-sie-es "geht"
                                      :wir "gehen"
                                      :ihr "geht"
                                      :sie-Sie "gehen"}
                            :Perfekt "gegangen"}
                   "gehören" {:English "belong"
                               :Hilfsverb :haben
                               :Präsens {:ich "gehöre"
                                         :du "gehörst"
                                         :er-sie-es "gehört"
                                         :wir "gehören"
                                         :ihr "gehört"
                                         :sie-Sie "gehören"}
                               :Perfekt "gehört"}
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
                   "hinweisen" {:English "point out"
                                :Hilfsverb :haben
                                :Präsens {:ich "weise hin"
                                          :du "weist hin"
                                          :er-sie-es "weist hin"
                                          :wir "weisen hin"
                                          :ihr "weist hin"
                                          :sie-Sie "weisen hin"}
                                :Perfekt "hingewiesen"}
                   "holen" {:English "fetch"
                            :Hilfsverb :haben
                            :Präsens {:ich "hole"
                                      :du "holst"
                                      :er-sie-es "holt"
                                      :wir "holen"
                                      :ihr "holt"
                                      :sie-Sie "holen"}
                            :Perfekt "geholt"}
                   "kriegen" {:English "get"
                              :Hilfsverb :haben
                              :Präsens {:ich "kriege"
                                        :du "kriegst"
                                        :er-sie-es "kriegt"
                                        :wir "kriegen"
                                        :ihr "kriegt"
                                        :sie-Sie "kriegen"}
                              :Perfekt "gekriegt"}
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
                   "raten" {:English "advice / guess"
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
                   "übertragen" {:English "trasnit"
                                 :Hilfsverb :haben
                                 :Präsens {:ich "übertrage"
                                           :du "übertragst"
                                           :er-sie-es "übertragt"
                                           :wir "übertragen"
                                           :ihr "übertragt"
                                           :sie-Sie "übertragen"}
                                 :Perfekt "übertragen"}
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
                   "veröffentlichen" {:English "publish"
                                      :Hilfsverb :haben
                                      :Präsens {:ich "veröffentliche"
                                                :du "veröffentlichst"
                                                :er-sie-es "veröffentlicht"
                                                :wir "veröffentlichen"
                                                :ihr "veröffentlicht"
                                                :sie-Sie "veröffentlichen"}
                                      :Perfekt "veröffentlicht"}
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
                   "wollen" {:English "will"
                             :Hilfsverb :haben
                             :Präsens {:ich "will"
                                       :du "willst"
                                       :er-sie-es "will"
                                       :wir "wollen"
                                       :ihr "wollt"
                                       :sie-Sie "wollen"}
                             :Perfekt "gewollt"}
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
                   "Anforderung" {:Artikel :die
                                  :English "Requirement"}
                   "Angebot" {:Artikel :das
                              :English "Offer"}
                   "Antwort" {:Artikel :die
                              :English "Answer"}
                   "Arbeitgeber" {:Artikel :der
                                  :English "Employer"}
                   "Artikel" {:Artikel :der
                              :English "Item"}
                   "Arzt" {:Artikel :der
                           :English "Doctor"
                           :Singular {:Nominativ {:Artikel :der
                                                  :Nomen "Arzt"}
                                      :Akkusativ {:Artikel :den
                                                  :Nomen "Arzt"}
                                      :Dativ {:Artikel :dem
                                              :Nomen "Arzt"}
                                      :Genitiv {:Artikel :des
                                                :Nomen "Arztes"}}
                           :Plural {:Nominativ {:Artikel :die
                                                  :Nomen "Ärzte"}
                                      :Akkusativ {:Artikel :die
                                                  :Nomen "Ärzte"}
                                      :Dativ {:Artikel :den
                                              :Nomen "Ärzten"}
                                      :Genitiv {:Artikel :der
                                                :Nomen "Ärzte"}}}
                   "Ausbildung" {:Artikel :die
                                 :English "Training"}
                   "Auto" {:Artikel :das
                           :English "Car"}
                   "Ausnahme" {:Artikel :die
                               :English "Exception"}
                   "Bank" {:Artikel :die
                           :English "Bank"}
                   "Bahnhof" {:Artikel :der
                              :English "Train Station"}
                   "Balkon" {:Artikel :der
                             :English "Balcony"}
                   "Baum" {:Artikel :der
                           :English "Tree"}
                   "Berater" {:Artikel :der
                              :English "Consultant"
                              :Singular {:Nominativ {:Artikel :der
                                                     :Nomen "Berater"}
                                         :Akkusativ {:Artikel :den
                                                     :Nomen "Berater"}
                                         :Dativ {:Artikel :dem
                                                 :Nomen "Berater"}
                                         :Genitiv {:Artikel :des
                                                   :Nomen "Beraters"}}
                              :Plural {:Nominativ {:Artikel :die
                                                     :Nomen "Berater"}
                                         :Akkusativ {:Artikel :die
                                                     :Nomen "Berater"}
                                         :Dativ {:Artikel :den
                                                 :Nomen "Beratern"}
                                         :Genitiv {:Artikel :des
                                                   :Nomen "Beraters"}}}
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
                                :English "Town Hall"}
                   "Café" {:Artikel :das
                           :English "Cafe"}
                   "Computer" {:Artikel :der
                               :English "Computer"}
                   "Couch" {:Artikel :die
                            :English "Couch"}
                   "Datenbank" {:Artikel :die
                                :English "Database"
                                :Singular {:Nominativ {:Artikel :die
                                                       :Nomen "Datenbank"}
                                           :Akkusativ {:Artikel :die
                                                       :Nomen "Datenbank"}
                                           :Dativ {:Artikel :der
                                                   :Nomen "Datenbank"}
                                           :Genitiv {:Artikel :der
                                                     :Nomen "Datenbank"}}
                                :Plural {:Nominativ {:Artikel :die
                                                     :Nomen "Datenbanken"}
                                         :Akkusativ {:Artikel :die
                                                     :Nomen "Datenbanken"}
                                         :Dativ {:Artikel :den
                                                 :Nomen "Datenbanken"}
                                         :Genitiv {:Artikel :der
                                                   :Nomen "Datenbanken"}}}
                   "Disco" {:Artikel :die
                            :English "Disco"}
                   "Dom" {:Artikel :der
                          :English "Cathedral"}
                   "Drittmittel" {:Artikel :die
                                  :English "Third-party Funds"}
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
                           :English "Case"}
                   "Familie" {:Artikel :die
                              :English "Family"}
                   "Familienstand" {:Artikel :der
                                    :English "Marital Status"}
                   "Farbe" {:Artikel :die
                            :English "Colour"}
                   "Fenster" {:Artikel :das
                              :English "Window"}
                   "Flur" {:Artikel :der
                           :English "Hallway"}
                   "Fluss" {:Artikel :der
                            :English "River"}
                   "Forschung" {:Artikel :die
                                :English "Research"}
                   "Forschungsgemeinschaft" {:Artikel :die
                                             :English "Research Foundation"}
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
                                :English "Cloak Room"}
                   "Garten" {:Artikel :der
                             :English "Garden"}
                   "Gegenstände" {:Artikel :der
                                  :English "Object"}
                   "Geld" {:Artikel :das
                           :English "Money"}
                   "Gemeinschaft" {:Artikel :die
                                   :English "Community"}
                   "Genus" {:Artikel :das
                            :English "Grammatical Gender"}
                   "Geschwister" {:Artikel plural-artikel
                                  :English "Siblings"}
                   "Glas" {:Artikel :das
                           :English "Glass"}
                   "Handy" {:Artikel :das
                            :English "Mobile Phone"}
                   "Haus" {:Artikel :das
                           :English "House"}
                   "Heilkräuter" {:Artikel :das
                                  :English "Medicinal Herbs"}
                   "Heim" {:Artikel :das
                           :English "Home"}
                   "Hinweise" {:Artikel :der
                               :English "Hint"}
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
                   "Moderator" {:Artikel :der
                                :English "Moderator"
                                :Singular {:Nominativ {:Artikel :der
                                                       :Nomen "Moderator"}
                                           :Akkusativ {:Artikel :den
                                                       :Nomen "Moderator"}
                                           :Dativ {:Artikel :dem
                                                   :Nomen "Moderator"}
                                           :Genitiv {:Artikel :des
                                                     :Nomen "Moderators"}}
                                :Plural {:Nominativ {:Artikel :die
                                                     :Nomen "Moderatoren"}
                                           :Akkusativ {:Artikel :die
                                                       :Nomen "Moderatoren"}
                                           :Dativ {:Artikel :den
                                                   :Nomen "Moderatoren"}
                                           :Genitiv {:Artikel :der
                                                     :Nomen "Moderatoren"}}}
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
                                   :English "Plural Ending"}
                   "Polizei" {:Artikel :die
                              :English "Police"}
                   "Post" {:Artikel :die
                           :English "Post Office"}
                   "Praktikum" {:Artikel :das
                                :English "Internship"}
                   "Preis" {:Artikel :der
                            :English "Price"}
                   "Projektleiter" {:Artikel :der
                                    :English "Project Manager"}
                   "Rat" {:Artikel :der
                          :English "Advice"}
                   "Rätsel" {:Artikel :das
                             :English "Riddle"}
                   "Rathaus" {:Artikel :das
                              :English "Town Hall"}
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
                               :English "Kind Of Sport"}
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
                                   :English "Telephone Number"}
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
                   "Vermittler" {:Artikel :der
                                 :English "Facilitator"
                                 :Singular {:Nominativ {:Artikel :der
                                                        :Nomen "Vermittler"}
                                            :Akkusativ {:Artikel :den
                                                        :Nomen "Vermittler"}
                                            :Dativ {:Artikel :dem
                                                    :Nomen "Vermittler"}
                                            :Genitiv {:Artikel :des
                                                      :Nomen "Vermittlers"}}
                                 :Plural {:Nominativ {:Artikel :die
                                                        :Nomen "Vermittler"}
                                            :Akkusativ {:Artikel :die
                                                        :Nomen "Vermittler"}
                                            :Dativ {:Artikel :den
                                                    :Nomen "Vermittlern"}
                                            :Genitiv {:Artikel :der
                                                      :Nomen "Vermittler"}}}
                   "Vitrine" {:Artikel :die
                              :English "Display Cabinet"}
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
                                 :English "Living Room"}
                   "Wunsch" {:Artikel :der
                             :English "Wish"}
                   "Zeit" {:Artikel :die
                           :English "Time"}
                   "Zentrum" {:Artikel :das
                              :English "Center"}
                   "Zusammenfassung" {:Artikel :die
                                      :English "Summary"}}))

; specs

(defn capitalize-all-words-in-string
  [x]
  (string/join " "
               (map #(string/capitalize %)
                    (string/split x #"\s+"))))

(defn upper-case?
  [x]
  #(= x (capitalize-all-words-in-string x)))

(defn lower-case?
  [x]
  #(= x (string/lower-case x)))

(defn null-or-string?
  [x]
  (or (nil? x) (string? x)))

(def Pronomen-Gender (spec/keys :req-un [:Pronomen/Nominativ :Pronomen/Akkusativ :Pronomen/Dativ :Pronomen/Genitiv]))

(spec/def :Pronomen/Nominativ null-or-string?)
(spec/def :Pronomen/Akkusativ null-or-string?)
(spec/def :Pronomen/Dativ null-or-string?)
(spec/def :Pronomen/Genitiv null-or-string?)
(spec/def :Pronomen/Plural Pronomen-Gender)
(spec/def :Pronomen/Feminin Pronomen-Gender)
(spec/def :Pronomen/Neutrum Pronomen-Gender)
(spec/def :Pronomen/Maskulin Pronomen-Gender)
(spec/def :Pronomen/English (spec/and string?
                                    lower-case?))
(spec/def :Fakten/Pronomen (spec/keys :req-un [:Pronomen/English :Pronomen/Maskulin :Pronomen/Neutrum :Pronomen/Feminin :Pronomen/Plural]))
(spec/def :Fakten/Pronomina (spec/and #(instance? PersistentTreeMap %)
                                      (spec/map-of string? :Fakten/Pronomen)))


(spec/def :Verb/Hilfsverb #{:haben :sein :haben-sein})
(spec/def :Verb/PersonalPronomen #{:ich :du :er-sie-es :wir :ihr :sie-Sie})
(spec/def :Verb/English (spec/and string?
                                  lower-case?))

(spec/def :Verb/Perfekt string?)
(spec/def :Verb/Präsens (spec/map-of :Verb/PersonalPronomen string?))
(spec/def :Fakten/Verb (spec/keys :req-un [:Verb/English :Verb/Hilfsverb :Verb/Präsens :Verb/Perfekt]))
(spec/def :Fakten/Verben (spec/and #(instance? PersistentTreeMap %)
                                   (spec/map-of string? :Fakten/Verb)))

(spec/def :Nomen/Artikel #{:die :der :das})
(spec/def :Nomen/English (spec/and string?
                                   upper-case?))
(spec/def :Fakten/Nomen (spec/keys :req-un [:Nomen/Artikel :Nomen/English]))
(spec/def :Fakten/Nomina (spec/and #(instance? PersistentTreeMap %)
                                   (spec/map-of string? :Fakten/Nomen)))

(spec/def ::Fakten (spec/keys :req-un [:Fakten/Nomina :Fakten/Pronomina :Fakten/Verben]))

(spec/def ::app-db (spec/keys :req-un [::Fakten]))

; db init
(def default-db {:Fakten {:Nomina Nomina
                          :Pronomina Pronomina
                          :Verben Verben}
                 :routes/aktiv-szene :heim
                 :navigation/menü-aktiv false})
