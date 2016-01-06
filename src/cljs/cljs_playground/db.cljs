(ns cljs-playground.db
  (:require [page-1.db :as page-1]))

(def default-db
  {:name "francis"
   :color (page-1/colors :red)
   :fetching? false
   :page-1 page-1/default-db
   :current-page :page-1})
