(ns animal-form-native.views
  (:require
   [re-frame.core :as re-frame]
   [animal-form-native.subs :as subs]
   [animal-form-native.events :as events]
   ))

(def animal-types ["Thanos" "Thor" "Hulk" "Spidey" "Captain" "IronM" "SuperM"])

(defn animal-listing []
  (let[animal-list @(re-frame/subscribe [::subs/animals])]
   [:h2 "Animal List"]
   [:ul
    (map (fn [{:keys [animal-type animal-name]}]
           [:ul
            [:li {:key animal-name} (str animal-name "(" animal-type ")")]]) animal-list)]))

(defn text-input [id]
  (let [value (re-frame/subscribe [::subs/form id])]
    [:div.field
     [:label.label "Name"]
     [:div.control
      [:input.input {:value @value
                     :on-change #(re-frame/dispatch [::events/update-form id (-> % .-target .-value)])
                     :type "text"
                     :placeholder "Text input"}]]])
  )

(defn select-input [id]
  (let [value (re-frame/subscribe [::subs/form id])]
    [:div.field
     [:label.label "Subject"]
     [:div.control
      [:div.select
       [:select {:on-change #(re-frame/dispatch [::events/update-form id (-> % .-target .-value)])
                 :value @value}
        [:option "Select dropdown"]
        (map (fn [opt] [:option {:key opt :value opt} opt]) animal-types)
        ]]]])
  )

(defn main-panel []
  (let [name (re-frame/subscribe [::subs/name])
        is-form-valid? (re-frame/subscribe [::subs/is-form-valid [:animal-name :animal-type]])]
    [:div.section
     [text-input :animal-name]
     [select-input :animal-type]
     [:button.button.is-primary {:disabled (not @is-form-valid?)
                                 :on-click #((re-frame/dispatch [::events/save-form])
                                             ())} "Save"]
    [animal-listing]
     
     ]))



(println "Success")