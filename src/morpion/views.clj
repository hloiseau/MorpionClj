(ns morpion.views
    (:require [clojure.string :as str]
              [hiccup.page :as page]
              [ring.util.anti-forgery :as util]))
              
              (defn home-page
                []
                (page/html5
                 [:h1 "Home"]
                 [:p "Webapp to store and display some 2D (x,y) locations."]))