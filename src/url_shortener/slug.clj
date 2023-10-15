(ns url-shortener.slug)

(def charset "ABCDEFGHIJKLMNOPQRSTUVWXYZ")

(defn generate-slug
  []
  (->> (repeatedly #(rand-nth charset))
       (take 4)
       (apply str)))