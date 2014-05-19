(ns ring.middleware.ssl
  "Middleware for managing handlers operating over HTTPS."
  (:require [ring.util.response :as resp]
            [ring.util.request :as req]
            [clojure.string :as str]))

(def default-scheme-header
  "The default header used in wrap-forwarded-scheme."
  "x-forwarded-proto")

(defn wrap-forwarded-scheme
  "Middleware that changes the :scheme of the request map to the value present
  in a request header. This is useful if your application sits behind a
  reverse proxy or load balancer that handles the SSL connection."
  ([handler]
     (wrap-forwarded-scheme handler default-scheme-header))
  ([handler header]
     (fn [req]
       (let [default (name (:scheme req))
             scheme  (str/lower-case (get-in req [:headers header] default))]
         (assert (or (= scheme "http") (= scheme "https")))
         (handler (assoc req :scheme (keyword scheme)))))))

(defn- get-request? [{method :request-method}]
  (or (= method :head)
      (= method :get)))

(defn wrap-ssl-redirect
  "Middleware that redirects any HTTP request to the equivalent HTTPS URL."
  [handler]
  (fn [request]
    (if (= (:scheme request) :https)
      (handler request)
      (-> (resp/redirect (req/request-url (assoc request :scheme :https)))
          (resp/status   (if (get-request? request) 301 307))))))

(defn- build-hsts-header
  [{:keys [max-age include-subdomains?]
    :or   {max-age 31536000, include-subdomains? true}}]
  (str "max-age=" max-age
       (if include-subdomains? "; includeSubDomains")))

(defn wrap-hsts
  "Middleware that adds the Strict-Transport-Security header to the response
  from the handler. This ensures the browser will only use HTTPS for future
  requests to the domain."
  {:arglists '([handler] [handler options])}
  [handler & [{:as options}]]
  (fn [request]
    (-> (handler request)
        (resp/header "Strict-Transport-Security" (build-hsts-header options)))))
