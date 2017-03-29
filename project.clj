(defproject deutsch-lernkarten "0.1.0-SNAPSHOT"
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [org.clojure/clojurescript "1.9.229"]
                 [bidi "2.0.16"]
                 [kibu/pushy "0.3.6"]
                 [reagent "0.6.0"]
                 [re-frame "0.9.1"]]

  :plugins [[lein-cljsbuild "1.1.5"]]

  :min-lein-version "2.5.3"

  :source-paths ["src/clj"]

  :clean-targets ^{:protect false} ["resources/public/js/compiled" "target"]

  :figwheel {:css-dirs ["resources/public/css"]
             :ring-handler deutsch-lernkarten.dev-server/http-handler
             :nrepl-middleware [cemerick.piggieback/wrap-cljs-repl]
             :nrepl-port 7888}


  :profiles {:dev {:dependencies [[binaryage/devtools "0.8.2"]
                                  [com.cemerick/piggieback "0.2.1"]
                                  [re-frisk-remote "0.4.1"]]
                   :plugins      [[lein-figwheel "0.5.9"]
                                  [lein-re-frisk "0.4.4"]]}}

  :cljsbuild {:builds {:dev {:source-paths ["src/cljs"]
                             :figwheel     {:on-jsload "deutsch-lernkarten.core/mount-root"}
                             :compiler     {:main                 deutsch-lernkarten.core
                                            :output-to            "resources/public/js/compiled/app.js"
                                            :output-dir            "resources/public/js/compiled/out"
                                            :asset-path           "js/compiled/out"
                                            :source-map-timestamp true
                                            :preloads             [devtools.preload]
                                            :external-config      {:devtools/config {:features-to-install :all}}}}
                       :min {:source-paths ["src/cljs"]
                             :compiler     {:main            deutsch-lernkarten.core
                                            :output-to       "resources/public/js/compiled/app.js"
                                            :optimizations   :advanced
                                            :closure-defines {goog.DEBUG false}
                                            :pretty-print    false}}}})
