(ns shared.protocols.eventful
  (:require [cljs.core.async :as async :refer [<! close!]]
            [shared.models.event.index :as event]
            [shared.protocols.loggable :as log]
            [shared.protocols.specced :as sp])
  (:require-macros [cljs.core.async.macros :refer [go-loop]]))

(defprotocol Eventful
  "The Eventful protocol allows offcourse components and adapters to handle
  and respond to events"
  (-listen [this])
  (-mute [this])
  (-send [this event])
  (-react [this event])
  (-respond [this event]))

(defn send
  "Sends an event to a remote endpoint (async)"
  [this event] (-send this (event/create event)))

(defn respond
  "Puts an event on the output channel of a component"
  [{:keys [channels component-name  responses] :as this} [status payload]]
  (let [response (event/create [component-name status payload])]
    (if (sp/valid? response)
      (async/put! (:output channels) (log/pipe response))
      (log/error response (sp/errors response)))))

(defn react
  "Has a service react to an event based on the event's specification type"
  [this event]
  (-react this event))

(defn- listener [{:keys [channels component-name triggers] :as this}]
  (go-loop []
    (let [[type :as event] (<! (:input channels))]
      (when (contains? (into #{} triggers) type) (react this event))
      (recur))))

(defn listen
  "Has a component listen for events on the input channel, and reacts when the event is relevant"
  [{:keys [channels component-name reactions] :as this}]
  (assoc this :listener (listener this)))

(defn mute
  "Makes a component stop listening for events"
  [{:keys [channels] :as this}]
  (close! (:input channels))
  (-> this
      (dissoc :listener)))
