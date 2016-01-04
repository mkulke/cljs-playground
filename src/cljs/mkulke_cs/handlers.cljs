(ns mkulke-cs.handlers
  (:require-macros [cljs.core.async.macros :refer [go]])
  (:require [re-frame.core :as re-frame]
            [cljs.core.async :as async :refer [<!]]
            [cljs-http.client :as http]
            [clojure.string :as string]
            [mkulke-cs.db :as db]))

(def endpoint "http://jsonplaceholder.typicode.com/users")

(defn mgns
  [db i]
  (go (let [response (<! (cljs-http.client/get (str endpoint "/" i)))
            toName #(-> % :body :name)]
        (if (= (response :success) true)
          (re-frame/dispatch [:set-name (toName response)]))
        (re-frame/dispatch [:done-fetching]))))

(re-frame/register-handler
 :initialize-db
 (fn  [_ _]
   db/default-db))

(re-frame/register-handler
 :set-name
 (fn
   [db [_ value]]
   (let [split #(string/split % #" ")]
     (assoc db :name (-> value split first string/capitalize)))))

(re-frame/register-handler
 :set-color
 (fn
   [db [_ value]]
   (assoc db :color value)))

(re-frame/register-handler
 :done-fetching
 (fn
   [db [_ _]]
   (assoc db :fetching? false)))

(re-frame/register-handler
 :fetch-user
 (fn
   [db [_ value]]
   (mgns db value)
   (assoc db :fetching? true)))
