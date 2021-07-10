(ns morpion.db
  (:require [clojure.java.jdbc :as jdbc]
            [clojure.string :as str]))
(def db-spec {:dbtype "h2" :dbname "./src/morpion/morpion-db"})

(defn insert-user
  [playerName password]
  (let [results (jdbc/insert! db-spec :player  {:playerName playerName :password password :countPlay 0 :winPlay 0})]
    (assert (= (count results) 1))
    (first (vals (first results)))))

(defn get-user
  [username password]
  (let [results (jdbc/query db-spec
       [(str "select id from player where playerName = '" username "' and password = '" password  "'")])]
    (assert (= (count results) 1))
    (first results)))

(defn get-all-score
  []
  (jdbc/query db-spec "select playerName, winPlay, countPlay from player"))

  (defn update-score
    [hasWin playerName]
    (jdbc/execute! db-spec
      [(str "update player set countPlay = countPlay + 1" (if (= true hasWin) ", winPlay = winPlay + 1") "where playerName = '" playerName "'")]
    )
  ) 
 