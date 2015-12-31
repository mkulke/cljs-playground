(ns mkulke-cs.core
    (:require [reagent.core :as reagent]
              [re-frame.core :as re-frame]
              [mkulke-cs.handlers]
              [mkulke-cs.subs]
              [mkulke-cs.views :as views]
              [mkulke-cs.config :as config]))

(when config/debug?
  (println "dev mode"))

(defn mount-root []
  (reagent/render [views/main-panel]
                  (.getElementById js/document "app")))

(defn ^:export init [] 
  (re-frame/dispatch-sync [:initialize-db])
  (mount-root))
