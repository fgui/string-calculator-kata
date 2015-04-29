(ns string-calculator.core
  (:use [clojure.string :as str :only [split join]]))

(defn re-delimiters []
  #",|\n"
)

(defn str->int [str-num]
  (Integer/parseInt str-num))

(defn add [str-cal]
  (if (empty? str-cal)
    0
    (apply + (map str->int (str/split str-cal (re-delimiters))))))
