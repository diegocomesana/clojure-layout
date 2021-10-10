(ns challenge.components.grid.row
  (:require
   [spade.core   :refer [defclass]]))

(defclass style
  []
  {:color :white :padding "10px" :margin "10px" :whidth "100%" :background-color :orangered})

(defn component [{:keys [name]} & columns]
  [:div {:class (style)}
   [:p "ROW --- " name]
   [:div columns]])