(ns challenge.components.hero
  (:require
   [re-frame.core :as re-frame]
   [spade.core   :refer [defglobal defclass]]
   [challenge.events :as events]))

(defclass style
  []
  {:color :white :padding "20px" :whidth "100%" :background-color :green})


(defn component
  [[image link]]
  [:div {:class (style)}
   [:p image]
   [:h2 "HERO"]
   [:p "image: " (:src image)]
   [:p "link: " (:href link)]])