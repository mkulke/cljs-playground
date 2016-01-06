(ns page-1.subs
  (:require-macros [reagent.ratom :refer [reaction]])
  (:require [re-frame.core :as re-frame]))

(re-frame/register-sub
 :caption
 (fn [db]
   (reaction (-> @db :page-1 :caption))))
