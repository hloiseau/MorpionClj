(ns morpion.handler
  (:require [morpion.views :as views]
            [compojure.core :refer :all]
            [compojure.route :as route]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]))

(defroutes app-routes
  (GET "/" [] (views/main-page))
  (POST "/choose-page" {params :params} (views/choose-page params))
  (GET "/launchGame" [] (views/launch-game-page))
  (GET "/play" {params :params} (views/play-page params) )
  (route/resources "/")
  (route/not-found "Not Found")
)

(def app
  (wrap-defaults app-routes site-defaults))