(ns morpion.views
  (:require [morpion.game :as game]
            [clojure.string :as str]
            [hiccup.page :as page]
            [ring.util.anti-forgery :as util]))

(def c (game/make-morpion) )

(defn home-page
    []
    (page/html5
        [:h1 "Home"]
        ( 
          game/print-morpion (game/make-morpion)
        ) 
    )
)

(defn home-page2
    [{x :x y :y}]
    (page/html5
        [:h1 "Home"]
        ( 
         game/print-morpion (game/set-case-morpion c 1 (Integer/parseInt x) (Integer/parseInt y ) )
        ) 
    )
)



