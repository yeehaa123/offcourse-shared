(ns shared.specs.profile
  (:require [cljs.spec :as spec]
            [shared.specs.helpers :as helpers]
            [shared.specs.base :as base]))

(spec/def ::name string?)
(spec/def ::emails (spec/* map?))
(spec/def ::portrait-url string?)
(spec/def ::portrait-data any?)
(spec/def ::revision int?)

(spec/def :profile/portrait  (spec/keys :req-un [::base/user-name ::portrait-url]
                                        :opt-un [::portrait-data]))

(spec/def :profile/valid (spec/keys :req-un [::base/user-name ::revision ::name ::emails]))
