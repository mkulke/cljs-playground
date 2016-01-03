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

(defn name-input
  [value dispatch]
  [:input {:type "text"
           :value @value
           :on-change #(reset! value (-> % .-target .-value))
           :on-key-down #(if (= (.-which %) 13) (dispatch))}])

(defn name-comp []
  (let [value (reagent/atom "foo")
        dispatch #(re-frame/dispatch [:set-name @value])]
    (fn []
      [:div
       [name-input value dispatch]])))

(defn color-tiles []
  (let [dispatch (fn [c] #(re-frame/dispatch [:set-color c]))
        tile (fn [c] [:div.tile {:style {:background-color c}
                                 :on-click (dispatch c)}])
        tiles (map #(-> % second tile) db/colors)]
    [:div.tile-wrapper tiles]))

(defn id-input
  [value]
  [:input {:type "number"
           :min "1"
           :on-change #(reset! value (-> % .-target .-value))
           :value @value
           :max "5"}])

(defn id-comp []
  (let [value (reagent/atom "1")
        dispatch #(re-frame/dispatch [:fetch-user @value])]
    (fn []
      [:div
       [:span "Id: "]
       [id-input value]
       [:span " "]
       [:input {:type "button"
                :value "Fetch"
                :on-click dispatch}]])))

(defn main-panel []
  [:div
   [name-display]
   [color-tiles]
   [name-comp]
   [id-comp]])
