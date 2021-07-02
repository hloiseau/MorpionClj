(ns morpion.views
  (:require [morpion.game :as game]
            [clojure.string :as str]
            [clojure.data.json :as json]
            [hiccup.page :as page]
            [ring.util.anti-forgery :as util]))

(def c (game/make-morpion) )

(defn home-page
    []
    (page/html5
        [:head 
        [:title "Morpion"] 
        (page/include-css "style.css")]
        [:h1 "Morpion"]
        ( 
          game/print-morpion (game/make-morpion) 1 false
        )
    )
)

(defn home-page2
    [{x :x y :y plateau :plateau player :player}]
    (page/html5
        [:head 
        [:title "Morpion"] 
        (page/include-css "style.css")]
        [:body
        [:h1 "Morpion"]
       (if (= false (game/winning-morpion? ( game/set-case-morpion (json/read-str plateau) (Integer/parseInt player) (Integer/parseInt x) (Integer/parseInt y ) ) ) )

        (if (game/legal-move-morpion? (json/read-str plateau) (Integer/parseInt x) (Integer/parseInt y ) ) 
            ( 
                game/print-morpion (game/set-case-morpion (json/read-str plateau) (Integer/parseInt player) (Integer/parseInt x) (Integer/parseInt y ) )
                (if (= (Integer/parseInt player) 1) 2 1)
                false
            )

            ( 
                game/print-morpion (json/read-str plateau) (Integer/parseInt player) false
            ) 
        )

        ( 
            game/print-morpion ( game/set-case-morpion (json/read-str plateau) (Integer/parseInt player) (Integer/parseInt x) (Integer/parseInt y ) )
            (Integer/parseInt player)
            true
        ) 

       )
      [:br 
       [:a {:href (str "/")}
       [:button {:style "width: 380px;"} "Reset"]] 
      ]
      ]
    )
)



