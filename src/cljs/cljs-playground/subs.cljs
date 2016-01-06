(ns cljs.playground.subs
  (:require-macros [reagent.ratom :refer [reaction]])
  (:require [re-frame.core :as re-frame]))

(re-frame/register-sub
 :name
 (fn [db]
   (reaction (:name @db))))

(re-frame/register-sub
 :fetching?
 (fn [db]
   (reaction (:fetching? @db))))

(re-frame/register-sub
 :current-page
 (fn [db]
   (reaction (:current-page @db))))

(re-frame/register-sub
 :color
 (fn [db]
   (reaction (:color @db))))
