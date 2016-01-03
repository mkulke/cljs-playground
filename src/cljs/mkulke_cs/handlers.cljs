(ns mkulke-cs.handlers
  (:require-macros [cljs.core.async.macros :refer [go]])
  (:require [re-frame.core :as re-frame]
            [cljs.core.async :as async :refer [<!]]
            [cljs-http.client :as http]
            [mkulke-cs.db :as db]))

; (defonce time-updater (js/setInterval
;   #(re-frame/dispatch [:fetch-user]) 10000))

(def endpoint "http://jsonplaceholder.typicode.com/users")

(defn mgns
  [i]
  (go (let [response (<! (cljs-http.client/get (str endpoint "/" i)))]
    (print (:status response))
    (print (-> response :body :name)))))

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

(re-frame/register-handler
 :fetch-user
 (fn
   [db [_ value]]
   (mgns value)))
