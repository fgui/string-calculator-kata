(ns string-calculator.core
  (:use [clojure.string :as str :only [join]]))

;; After finishing the kata I thought that there was a much
;; easier way to do it, taking a shortcut:
;; Parse numbers considering anything else delimiters.
;; This code takes the shortcut.

(defn str->int [str-num]
  (Integer/parseInt str-num))

(defn extract-numbers [str-cmd]
  (if-let [n (re-seq #"([-\d]+)" str-cmd)]
    (map str->int (map second n))
    [0]))

(defn check-pos [numbers]
  (if (some neg? numbers)
    (throw (Exception. (str/join " " (filter neg? numbers))))))

(defn add [str-cmd]
  (let [numbers (filter #(< % 1000) (extract-numbers str-cmd))]
    (check-pos numbers)
    (apply + numbers)))
