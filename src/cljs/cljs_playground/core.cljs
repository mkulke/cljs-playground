(ns cljs-playground.core
  (:require [reagent.core :as reagent]
            [re-frame.core :as re-frame]
            [cljs-playground.handlers]
            [page-1.handlers]
            [cljs-playground.subs]
            [page-1.subs]
            [cljs-playground.views :as views]
            [cljs-playground.config :as config]))

(when config/debug?
  (println "dev mode"))

(defn mount-root []
  (reagent/render [views/main-panel]
                  (.getElementById js/document "app")))

(defn ^:export init []
  (re-frame/dispatch-sync [:initialize-db])
  (mount-root))
