(ns string-calculator.core
  (:use [clojure.string :as str :only [split join]]))

(defn re-quote [s]
  (java.util.regex.Pattern/quote s))

(defn re-delimiters [delimeters]
  (if (empty? delimeters)
    #",|\n"
    (re-pattern (str/join "|"  (cons ",|\\n" (map re-quote delimeters))))))

(defn str->int [str-num]
  (if (empty? str-num)
    0
    (Integer/parseInt str-num)))

(defn extract-delimeters [delimeters]
  (if-let [d (re-seq #"\[([^\]]+)\]" delimeters)]
    (map second d)
    [delimeters]))

(defn str->delimeters-and-numbers-str [str-cmd]
  (if-let [g (re-seq #"^//(.+)\n(.+)" str-cmd)]
    (let [[dels nums]  (rest (first g))]
      [(extract-delimeters dels) nums])
    [[] str-cmd]))

(defn extract-numbers [str-cmd]
  (let [[delimeters str-nums] (str->delimeters-and-numbers-str str-cmd)]
    (map str->int (str/split str-nums (re-delimiters delimeters)))))

(defn check-pos [numbers]
  (if (some neg? numbers)
    (throw (Exception. (str/join " " (filter neg? numbers))))))

(defn add [str-cmd]
  (let [numbers (filter #(< % 1000) (extract-numbers str-cmd))]
    (check-pos numbers)
    (apply + numbers)))
