(ns challenge.components.header
  (:require
   [re-frame.core :as re-frame]
   [spade.core   :refer [defglobal defclass]]
   [challenge.events :as events]))


(defclass style
  []
  {:color :white :padding "20px" :whidth "100%" :background-color :pink})


(defn component
  [[logo links]]
  [:div {:class (style)}
   [:h2 "HEADER"]
   [:p "logo: " logo]
  ;;  [:p "links: " (str links)]
   ])