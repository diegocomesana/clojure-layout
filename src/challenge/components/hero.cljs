(ns challenge.components.hero
  (:require
   [re-frame.core :as re-frame]
   [spade.core   :refer [defglobal defclass]]
   [challenge.events :as events]))

(defclass style
  []
  {:color :black :padding "20px" :whidth "100%" :background-color :magenta})


(defn component
  [{:keys [image link]}]
  [:div {:class (style) :style {:background (str "no-repeat center url(" (:src image) ")")}}
   [:h2 "HERO"]
   [:p {:key "image"} "image: " (:src image)]
   [:p {:key "link"} "link: " (:href link)]])