(require '[clojure.java.jdbc :as jdbc])
(jdbc/with-db-connection [conn {:dbtype "h2" :dbname "./src/morpion/morpion-db"}]

  (jdbc/db-do-commands conn
    (jdbc/create-table-ddl :player
      [[:id "bigint primary key auto_increment"]
       [:playerName "varchar(32)"]
       [:password "varchar(32)"]
       [:countPlay "integer"]
       [:winPlay "integer"]
       ]))

  (jdbc/insert! conn :player
    {:playerName "DCHICHE" :password "AZERTY" :countPlay 9999 :winPlay 9999}))