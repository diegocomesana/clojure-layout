(ns challenge.components.all
  (:require
   [challenge.components.hero :as hero]
   [challenge.components.header :as header]))

(def hero hero/component)
(def header header/component)