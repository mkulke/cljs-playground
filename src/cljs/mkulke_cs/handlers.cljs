(ns mkulke-cs.handlers
  (:require [re-frame.core :as re-frame]
            [mkulke-cs.db :as db]))

(re-frame/register-handler
 :initialize-db
 (fn  [_ _]
   db/default-db))

(re-frame/register-handler
 :set-name
 (fn
   [db [_ value]]
   (assoc db :name (clojure.string/capitalize value))))

(re-frame/register-handler
 :set-color
 (fn
   [db [_ value]]
   (assoc db :color value)))
