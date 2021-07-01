(ns morpion.views
    (:require [clojure.string :as str]
              [hiccup.page :as page]
              [ring.util.anti-forgery :as util]
              [selmer.parser :as selmer]))
(selmer/set-resource-path! "templates")

(defn home-page
  []
  (selmer/render-file "game.html" {:content1 "X"}))