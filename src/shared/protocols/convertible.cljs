(ns shared.protocols.convertible)

(defprotocol Convertible
  "The Convertible protocol allows data structures to be converted to others
  after they are checked against their specification"
  (-to-models [this])
  (-to-event [this])
  (-to-clj [this])
  (-to-url [this routes] [this course routes]))

(defn to-models
  "Converts raw data to one or more offcourse models, if the object meets the
  corresponding model specification"
  [this]
  (-to-models this))

(defn to-event
  "Converts an js api event to an offcourse event, if the object meets the
  corresponding model specification"
  [this]
  (-to-event this))

(defn to-url
  "Given a set of routes, it converts a model to a url"
  ([this routes] (-to-url this routes))
  ([this course routes] (-to-url this course routes)))

(defn to-clj
  ([this] (-to-clj this)))

