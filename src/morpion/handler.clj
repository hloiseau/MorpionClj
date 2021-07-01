(ns morpion.handler
  (:require [morpion.views :as views]
            [compojure.core :refer :all]
            [compojure.route :as route]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]))

(defroutes app-routes
  (GET "/" [] (views/home-page))
  (route/not-found "Not Found"))

(def app
  (wrap-defaults app-routes site-defaults))