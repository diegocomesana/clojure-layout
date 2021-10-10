(ns challenge.events
  (:require
   [re-frame.core :as re-frame]
   [challenge.db :as db]
   [ajax.core :as ajax]))

(re-frame/reg-event-fx
 ::initialize-db
 (fn [_ _]
   {:db db/default-db
    :dispatch [::fetch-data]}))



(re-frame/reg-event-db
 ::on-fetch-data-success
 (fn [db [_ result]]
   (println "putos componentes: " (:components result))
   (assoc db :components (:components result) :layout (:layout result))))


(re-frame/reg-event-fx
 ::fetch-data
 (fn [cofx event]
   {:db   (assoc (:db cofx) :loading true)
    :http-xhrio {:method :get
                 :uri "https://run.mocky.io/v3/560ff885-1c88-410e-aad6-8a82a4f65ff4"
                 :timeout 8000
                 :response-format (ajax/json-response-format {:keywords? true})
                 :on-success [::on-fetch-data-success]
      ;;  :failure [::on-fetch-data-failure]
                 }}))

