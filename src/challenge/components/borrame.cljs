(ns challenge.components.borrame
  (:require
   [re-frame.core :as re-frame]
   [spade.core   :refer [defglobal defclass]]
;;    [challenge.styles :as styles]
;;    [challenge.subs :as subs]
   [challenge.events :as events]))


(defclass style
  []
  {:color :white :padding "20px" :whidth "100%" :background-color :green})


(defn component
  [image link]
  [:div {:class (style)}
   [:h2 "HERO"]
   [:p "image: " image]
   [:p "link: " link]
   [:button {:on-click #(re-frame/dispatch [::events/fetch-data])} "lalala"]])