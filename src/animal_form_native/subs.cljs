(ns animal-form-native.subs
  (:require
   [re-frame.core :as re-frame]))

(re-frame/reg-sub
 ::name
 (fn [db]
   (:name db)))


(re-frame/reg-sub
 ::form
 (fn [db [_ id]]
   (get-in db [:form id] "")))

(re-frame/reg-sub
 ::is-form-valid
 (fn [db [_ animal]]
   (every? #(get-in db [:form %]) animal)))

(re-frame/reg-sub
 ::animals
 (fn [db]
   (get-in db [:animals])))
