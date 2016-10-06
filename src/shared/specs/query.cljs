(ns shared.specs.query
  (:require [cljs.spec :as spec]
            [shared.specs.base :as base]
            [shared.specs.viewmodel :as viewmodel]
            [shared.specs.course :as course]
            [shared.specs.resource :as resource]
            [shared.specs.user :as user]))

(spec/def ::collection      (spec/keys :req-un [::base/collection-type ::base/collection-name]))
(spec/def ::course          (spec/keys :req-un [::base/course-slug ::base/curator]))
(spec/def ::checkpoint      (spec/keys :req-un [::base/checkpoint-slug]))
(spec/def ::tags            (spec/keys :req-un [::base/tags]))
(spec/def ::resource        (spec/keys :req-un [::resource/resource-url]
                                       :opt-un [::course/course-id]))

(spec/def ::keys            (spec/* int?))
(spec/def ::bucket-items    (spec/keys :req-un [::keys]))

(spec/def ::query (spec/or :collection ::collection
                           :bucket-items ::bucket-items
                           :tags       ::tags
                           :user       ::user/user
                           :resource   ::resource
                           :resources  (spec/* ::resource)
                           :viewmodel  ::viewmodel/viewmodel
                           :checkpoint ::checkpoint
                           :course     ::course))
