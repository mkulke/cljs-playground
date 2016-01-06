(ns cljs-playground.db)

(defonce colors
  {:magenta "#859900"
   :blue "#268bd2"
   :violet "#6c71c4"
   :red "#dc322f"})

(def default-db
  {:name "francis"
   :color (colors :red)
   :fetching? false
   :current-page :page-1})
