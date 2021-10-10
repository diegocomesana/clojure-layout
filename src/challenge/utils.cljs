(ns challenge.utils
  (:require
   [clojure.string :as string]
   [cljs.pprint :as p]))


(def log p/pprint)


(defn get-ids-from-layouts
  "Processes the layout object returning a set with all the ids for the given layout-name"
  [layouts layout-name]
  (let [{{rows :rows} layout-name} layouts]
    (->> rows
         (map (fn [row] (map :component row)))
         flatten
         (filter string?)
         (into #{}))))


(defn get-components-instances
  "returns a hashmap of component information by id with rendered output"
  [{:keys [components type-key-to-component component-valid-id options]}]
  (let [debug (true? (:debug options))]
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
                  (when (and comp (contains? component-valid-id id))
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


(defn render-layout
  "Processes the layout object and renders all components"
  [{:keys [layouts layout-name components-instances component-valid-id row-component column-component options]}]
  (let [{{rows :rows} layout-name} layouts
        debug (true? (:debug options))]
    (->> rows
         (map-indexed (fn [index row]
                        [row-component {:key index :name (str "row" (inc index)) :debug debug}
                         (map-indexed (fn [index {:keys [component columns]}]
                                        [column-component {:key index :name (str "col" (inc index)) :width columns :debug debug}
                                         (let [output (->> component
                                                           (get components-instances)
                                                           :output)]
                                           (if output
                                             output
                                             (when debug [:p {:key (str index component)} "No instance found for " [:span {:style {:font-weight :bold}} component]])))
                                        ;; column-component
                                         ])row)
                        ;; row-component
                         ])))))