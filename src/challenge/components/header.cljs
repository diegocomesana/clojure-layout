(ns challenge.components.header
  (:require
   [re-frame.core :as re-frame]
   [spade.core   :refer [defglobal defclass]]
   [challenge.events :as events]))


(defclass style
  []
  {:color :white :padding "20px" :whidth "100%" :background-color :orange})


(defn component
  [{:keys [logo links]}]
  [:div {:class (style)}
   [:h2 "HEADER"]
   [:img {:key "logo" :src (:href logo)}]
   [:ul
    (map (fn [{:keys [text href]}] [:li {:key href} [:a {:href href} text]]) links)]])