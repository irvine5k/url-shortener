(ns url-shortener.db
  (:require [clojure.java.jdbc :as j]
            [honey.sql :as sql]
            [honey.sql.helpers :refer :all]
            [url-shortener.env :refer [env]]))

(def pg-db {:dbtype "postgresql"
            :dbname (env :DBNAME)
            :host (env :HOST)
            :user (env :USER)
            :password (env :PASSWORD)})

(defn query
  [q]
  (j/query pg-db q))

(defn insert!
  [q]
  (j/db-do-prepared pg-db q))

(defn insert-redirect!
  [slug url]
  (insert! (-> (insert-into :redirects)
               (columns :slug :url)
               (values [[slug url]])
               (sql/format))))

(defn get-url!
  [slug]
  (-> (query (-> (select :*)
             (from :redirects)
             (where [:= :slug slug])
             (sql/format)))
      first
      :url))


(comment 
  (insert-redirect! "test" "https://google.com")
  (get-url! "test"))



;; db.qqmucqlppexppukbyvqo.supabase.co
;; USERNAME: postgres PORT: 5432