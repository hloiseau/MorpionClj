(ns morpion.game
    (:require 
        [clojure.string :as str]
        [clojure.data.json :as json]
        [hiccup.page :as page]
        [ring.util.anti-forgery :as util]))

(defn make-morpion []
    (vec (take 9 (repeat 0))))
  
  (defn get-case-morpion [plateau i j]
    (get plateau (+ i (* 3 j))))
  
  
    (defn print-morpion [plateau player win]
        (defn pcase [c]
          (case c
            0 "."
            1 "X"
            2 "O"))
        [:div {:class "wrapper"}
        (for [x (range 3) y (range 3) ]
             (if (= false win) [:a {:href (str "play?x=" x "&&y=" y "&&plateau=" (json/write-str plateau) "&&player=" player)}
              [:button (pcase (get-case-morpion plateau x y))]
             ]
             [:a [:button (pcase (get-case-morpion plateau x y))]]
             )
          )
        ]
      )
    
  
  
  (defn set-case-morpion [plateau player i j]
    (assoc plateau (+ i (* 3 j)) player))
  
    
  (defn legal-move-morpion? [plateau i j]
    (and (>= i 0) 
         (<= i 2) 
         (>= j 0)
         (<= j 2)
         (= (get-case-morpion plateau i j) 0)))
          
  
  ; alignement de 3 cases identiques et differentes de 0
  (defn winning-morpion? [plateau]
    (defn test [a b c] (and (not= a 0) (= a b) (= a c)))
    (or (test (get-case-morpion plateau 0 0)  (get-case-morpion plateau 0 1)  (get-case-morpion plateau 0 2))
        (test (get-case-morpion plateau 1 0)  (get-case-morpion plateau 1 1)  (get-case-morpion plateau 1 2)) 
        (test (get-case-morpion plateau 2 0)  (get-case-morpion plateau 2 1)  (get-case-morpion plateau 2 2))
        
        (test (get-case-morpion plateau 0 0)  (get-case-morpion plateau 1 0)  (get-case-morpion plateau 2 0)) 
        (test (get-case-morpion plateau 0 1)  (get-case-morpion plateau 1 1)  (get-case-morpion plateau 2 1)) 
        (test (get-case-morpion plateau 0 2)  (get-case-morpion plateau 1 2)  (get-case-morpion plateau 2 2))
        
        (test (get-case-morpion plateau 0 0)  (get-case-morpion plateau 1 1)  (get-case-morpion plateau 2 2)) 
        (test (get-case-morpion plateau 2 0)  (get-case-morpion plateau 1 1)  (get-case-morpion plateau 0 2))
        ))
    
  
  (defn move-morpion [plateau player]
    (defn getpos [] (let [x (read-line) n (read-string x)]
                      (cond (not (integer? n)) (do (println "not a number") (getpos))
                            (> n 3) (do (println "trop grand") (getpos))
                            (< n 1) (do (println "trop petit") (getpos))
                            :else (dec n))))
    (println "Player " player "joue !")
    (let [i (do (println "ligne [1-3] :") (getpos))
          j (do (println "colonne [1-3] :") (getpos))]
      (cond (not (legal-move-morpion? plateau i j)) (do (println "illegal move") (move-morpion plateau player))
            :else  (set-case-morpion plateau player i j))))
  
  (defn exchange [player] 
    (- 3 player))

  (defn end-morpion? [plateau]
    (empty? (filter (fn [x] (zero? x)) plateau))) 
  
  (defn play-morpion
    ([] (play-morpion (make-morpion) 1))
    ([plateau player]
     (print-morpion plateau)
     (let [p (move-morpion plateau player)]
       (cond (winning-morpion? p) (do (print-morpion plateau) (println "Player " player " wins !"))
             (end-morpion? p)  (do (print-morpion plateau)(println "draw"))
             :else (play-morpion p (exchange player))))))
     
    
    