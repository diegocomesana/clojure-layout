(ns challenge.components.grid.column
  (:require
   [spade.core   :refer [defclass]]))

(defclass style
  []
  {:color :white :padding "10px" :margin "10px" :whidth "100%" :background-color :blueviolet})

(defn component [{:keys [name width]} & children]
  [:div {:class (style)}
   [:p "COLUMN --- " name " width:" width]
   [:div children]])
