(ns mkulke-cs.views
  (:require [re-frame.core :as re-frame]
            [reagent.core :as reagent]
            [mkulke-cs.db :as db]))

(defn name-display []
  (let [name (re-frame/subscribe [:name])
        color (re-frame/subscribe [:color])]
    (fn []
      [:div.text "Hello from "
       [:strong {:style {:color @color}} @name]])))

(defn name-input []
  (let [value (reagent/atom "default")
        dispatch #(re-frame/dispatch [:set-name @value])]
    [:input {:type "text"
             :on-change #(reset! value (-> % .-target .-value))
             :on-key-down #(if (= (.-which %) 13) (dispatch))}]))

(defn color-tiles []
  (let [dispatch (fn [c] #(re-frame/dispatch [:set-color c]))
        tile (fn [c] [:div.tile {:style {:background-color c}
                                 :on-click (dispatch c)}])
        tiles (map #(-> % second tile) db/colors)]
    [:div.tile-wrapper tiles]))

(defn main-panel []
  [:div
   [name-display]
   [color-tiles]
   [name-input]])
