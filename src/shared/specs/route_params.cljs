(ns shared.specs.route-params
  (:require [cljs.spec :as spec]
            [shared.specs.base :as base]
            [shared.specs.course :as course]))


(spec/def ::collection (spec/keys :req-un [::base/collection-type
                                           ::base/collection-name]))

(spec/def ::course     (spec/keys :req-un [::base/curator ::base/course-slug]
                                       :opt-un [::base/organization]))

(spec/def ::checkpoint (spec/keys :req-un [::base/checkpoint-slug ::base/curator
                                                ::base/course-slug]
                                       :opt-un [::base/organization]))

(spec/def ::home-view nil?)
(spec/def ::user nil?)

(spec/def ::home (spec/keys :req-un [::home-view]))
(spec/def ::sign-up (spec/keys :req-un [::user]))

(spec/def ::route-params (spec/or :home-view       ::home
                                  :signup-view     ::sign-up
                                  :collection-view ::collection
                                  :checkpoint-view ::checkpoint
                                  :course-view     ::course))
