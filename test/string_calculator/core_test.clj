(ns string-calculator.core-test
  (:use midje.sweet)
  (:use [string-calculator.core]))

(println "You should expect to see three failures below.")

(facts "string calculator"
  (fact "1"
    (add "") => 0
    ))
