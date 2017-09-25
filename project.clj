(defproject com.roomkey/ring-ssl "0.4.0"
  :description "Ring middleware for managing HTTPS requests"
  :url "https://github.com/roomkey/ring-ssl"
  :license {:name "The MIT License"
            :url "http://opensource.org/licenses/MIT"}
  :dependencies [[org.clojure/clojure "1.5.1"]
                 [ring/ring-core "1.6.0"]]
  :plugins [[lein-codox "0.10.3"]]
  :codox {:project {:name "Ring-SSL"}
          :output-path "codox"}
  :aliases {"test-all" ["with-profile" "default:+1.6:+1.7:+1.8:+1.9" "test"]}
  :profiles
  {:dev {:dependencies [[ring/ring-mock "0.3.0"]]}
   :1.6 {:dependencies [[org.clojure/clojure "1.6.0"]]}
   :1.7 {:dependencies [[org.clojure/clojure "1.7.0"]]}
   :1.8 {:dependencies [[org.clojure/clojure "1.8.0"]]}
   :1.9 {:dependencies [[org.clojure/clojure "1.9.0-alpha10"]]}}
  :repositories {"releases" {:url "s3://rk-maven/releases/"}})
