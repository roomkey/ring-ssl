(defproject ring/ring-ssl "0.2.1"
  :description "Ring middleware for managing HTTPS requests"
  :url "https://github.com/ring-clojure/ring-ssl"
  :license {:name "The MIT License"
            :url "http://opensource.org/licenses/MIT"}
  :dependencies [[org.clojure/clojure "1.5.1"]
                 [ring/ring-core "1.6.0-beta5"]]
  :plugins [[lein-codox "0.9.6"]]
  :codox {:project {:name "Ring-SSL"}
          :output-path "codox"}
  :profiles
  {:dev {:dependencies [[ring/ring-mock "0.3.0"]]}
   :1.6 {:dependencies [[org.clojure/clojure "1.6.0"]]}})
