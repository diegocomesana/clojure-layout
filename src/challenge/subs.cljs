(ns challenge.subs
  (:require
   [re-frame.core :as re-frame]))

(re-frame/reg-sub
 ::name
 (fn [db]
   (:name db)))

(re-frame/reg-sub
 ::components
 (fn [db]
   (:components db)))

(re-frame/reg-sub
 ::layout
 (fn [db]
   (:layout db)))