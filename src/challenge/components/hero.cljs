(ns challenge.components.hero
  (:require
   [re-frame.core :as re-frame]
   [spade.core   :refer [defglobal defclass]]
   [challenge.events :as events]))

(defclass style
  []
  {:color :white :padding "20px" :whidth "100%" :background-color :green})


(defn component
  [{:keys [image link]}]
  [:div {:class (style)}
   [:h2 "HERO"]
   [:p {:key "image"} "image: " (:src image)]
   [:p {:key "link"} "link: " (:href link)]])