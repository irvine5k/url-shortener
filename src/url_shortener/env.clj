(ns url-shortener.env
  (:require [clojure.edn :refer [read-string]]))

(def envvars (read-string (slurp "env.edn")))

(defn env
  [key]
  (or (key envvars) (System/getenv (name key))))