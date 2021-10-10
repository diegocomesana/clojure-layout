(ns challenge.views
  (:require
   [re-frame.core :as re-frame]
   [challenge.styles :as styles]
   [challenge.subs :as subs]
   [challenge.components.all :as available-components]
   [challenge.utils :as utils]
   [clojure.string :as string]))


;; https://day8.github.io/re-frame/EffectfulHandlers/


(def type-key-to-component {:hero available-components/hero
                            :header available-components/header})

(def layout-name :desktop)
(def debug-components true)


;; (def lprueba {:desktop
;;               {:rows
;;                [[{:component "desktop-1", :columns 12}]
;;                 [{:component nil, :columns 3}
;;                  {:component "desktop-2"
;;                   :columns 12}]]}
;;               :mobile
;;               {:rows
;;                [[{:component "mobile1", :columns 12}]
;;                 [{:component "ed6ca899-01bc-4d99-b26d-6672e3ab8bd4", :columns 12}]
;;                 [{:component "8f684ade-e8bd-45fd-b7dc-7f8e6d7d4ecd", :columns 12}]
;;                 [{:component "64f8e186-a2c3-4aed-bac2-147a654d54aa", :columns 12}]
;;                 [{:component "mobile1", :columns 12}]
;;                 [{:component "eef8a50d-7ac8-43ef-9592-5c48a5446c56", :columns 12}]]}})



(defn get-components-instances
  "returns a hashmap of component information by id with rendered output"
  [components type-key-to-component ids & options]
  (let [debug (->> options first true?)]
    (when debug
      (println "----------------------------------------")
      (println "BUILDING COMPONENTS"))

    (->> components
         (map (fn [component-data]
                (let [{id :id} component-data
                      type-key (->> component-data
                                    keys
                                    second)
                      comp (type-key type-key-to-component)
                      {data type-key} component-data
                      props (vals data)]
                  (when (and comp (contains? ids id))
                    (when debug
                      (println (str "---------------- COMPONENT " (string/upper-case (str type-key)) " ----------------"))
                ;;  (println "component-data: " component-data)
                      (println "type-key: " type-key)
                      (println "props: " props)
                      (println "props-type: " (type props)))

                    {id {:id id
                         :type-key type-key
                         :output (comp props)}}))))
         (filter some?)
         (into {}))))





(defn get-ids-from-layouts
  "Processes the layout object returning a set with all the ids for the given layout-name"
  [layouts layout-name]
  (let [{{rows :rows} layout-name} layouts]
    (->> rows
         (map (fn [row] (map :component row)))
         flatten
         (filter string?)
         (into #{}))))





;; (def component-valid-id (get-ids-from-layouts lprueba :mobile))


(defn row [{:keys [name]} & columns]
  [:div
   [:p "row: " name]
   [:div columns]])

(defn column [{:keys [name width]} & children]
  [:div
   [:p "column:" name]
   [:p "column:" name]
   [:div children]])


(defn main-panel []
  (let [name (re-frame/subscribe [::subs/name])
        components (re-frame/subscribe [::subs/components])
        layout (re-frame/subscribe [::subs/layout])
        component-valid-id (get-ids-from-layouts @layout layout-name)
        components-instances (get-components-instances @components type-key-to-component component-valid-id debug-components)]

    ;; (utils/log @layout)

    [:div
     [:h1
      {:class (styles/level1)}
      "Hello from " @name]

    ;;  (utils/log components-instances)


    ;;  get all components output
    ;;  (map (fn [{output :output}] output) (vals components-instances))

    ;;  get component output by id
    ;;  (->> "ed6ca899-01bc-4d99-b26d-6672e3ab8bd4"
    ;;       (get components-instances)
    ;;       :output)




    ;;  [paparulo "diego"
    ;;   [:p {:style {:background-color :red :color :white :padding "20px"}} "un hijo"]]



    ;;  lslsl
     ]))
