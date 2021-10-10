(ns challenge.views
  (:require
   [re-frame.core :as re-frame]
   [challenge.styles :as styles]
   [challenge.subs :as subs]
   [challenge.components.all :as available-components]
   [challenge.components.grid.main :as grid]
   [challenge.utils :as utils]))


;; https://day8.github.io/re-frame/EffectfulHandlers/


(def type-key-to-component {:hero available-components/hero
                            :header available-components/header})

(def layout-name :desktop)
(def debug-components true)

(defn main-panel []
  (let [name (re-frame/subscribe [::subs/name])
        components (re-frame/subscribe [::subs/components])
        layout (re-frame/subscribe [::subs/layout])
        component-valid-id (utils/get-ids-from-layouts @layout layout-name)
        components-instances (utils/get-components-instances {:components @components
                                                              :type-key-to-component type-key-to-component
                                                              :component-valid-id component-valid-id
                                                              :options {:debug debug-components}})]

    [:div
     [:h1
      {:class (styles/level1)}
      "Hello from " @name]
     (utils/render-layout
      {:layouts @layout
       :layout-name layout-name
       :components-instances components-instances
       :component-valid-id component-valid-id
       :row-component grid/row
       :column-component grid/column
       :options {:debug debug-components}})]))
