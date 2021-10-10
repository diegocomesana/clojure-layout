(ns challenge.components.grid.main
  (:require
   [challenge.components.grid.row :as row]
   [challenge.components.grid.column :as column]))

(def row row/component)
(def column column/component)