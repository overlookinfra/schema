(defproject puppetlabs/schema "0.2.0-pre-2"
  :description "This is an old, unmaintained fork of prismatic/schema, a \"Clojure(Script) library for declarative data description and validation\".  Changes have been merged upstream, please use the latest version from Prismatic"
  :url "http://github.com/prismatic/schema"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}

  :dependencies [[potemkin "0.3.2"]]

  :profiles {:dev {:dependencies [[org.clojure/clojure "1.5.1"]
                                  [org.clojure/clojurescript "0.0-2030"]
                                  [prismatic/cljs-test "0.0.6"]
                                  [com.keminglabs/cljx "0.3.1"]]
                   :plugins [[com.cemerick/austin "0.1.3"]
                             [lein-cljsbuild "1.0.0-alpha2"]
                             [com.keminglabs/cljx "0.3.1"]]
                   :repl-options {:nrepl-middleware [cemerick.piggieback/wrap-cljs-repl
                                                     cljx.repl-middleware/wrap-cljx]}
                   :cljx {:builds [{:source-paths ["src/cljx"]
                                    :output-path "target/generated/src/clj"
                                    :rules :clj}
                                   {:source-paths ["src/cljx"]
                                    :output-path "target/generated/src/cljs"
                                    :rules :cljs}
                                   {:source-paths ["test/cljx"]
                                    :output-path "target/generated/test/clj"
                                    :rules :clj}
                                   {:source-paths ["test/cljx"]
                                    :output-path "target/generated/test/cljs"
                                    :rules :cljs}]}}}

  :jar-exclusions [#"\.cljx|\.swp|\.swo|\.DS_Store"]

  :lein-release {:deploy-via :shell
                 :shell ["lein" "deploy" "clojars"]}

  :prep-tasks ["cljx" "javac" "compile"]

  :source-paths ["target/generated/src/clj" "src/clj"]

  :resource-paths ["target/generated/src/cljs"]

  :test-paths ["target/generated/test/clj" "test/clj"]

  :cljsbuild {:builds
              {:dev {:source-paths ["src/clj" "target/generated/src/cljs"]
                     :compiler {:output-to "target/main.js"
                                :optimizations :whitespace
                                :pretty-print true}}
               :test {:source-paths [ "src/clj" "test/clj"
                                      "target/generated/src/cljs" "target/generated/test/cljs"]
                      :compiler {:output-to "target/unit-test.js"
                                 :optimizations :whitespace

                                 :pretty-print true}}}})
