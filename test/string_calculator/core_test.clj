(ns string-calculator.core-test
  (:use midje.sweet)
  (:use [string-calculator.core]))

(facts "string calculator"
       (fact "1"
             (add "") => 0
             (add "1") => 1
             (add "1,2") => 3)
       (fact "2"
             (add "1,2,3,4,5") => 15)
       (fact "3"
             (add "1\n2,3") => 6)
       (fact "4"
             (add "//;\n1;2") => 3)
       (fact "5"
             (add "1,-2,3,-4") => (throws Exception "-2 -4"))
       (fact "6"
             (add "2,1001") => 2)
       (fact "7"
             (add "//[***]\n1***2***3") => 6
             (add "//[sep]\n1sep2sep3") => 6
             )
       (fact "8"
             (add "//[*][%]\n1*2%3") => 6
             )

)
