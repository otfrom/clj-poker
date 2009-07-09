; a card is a two element vector of keyworks [:rank :suit] e.g. [:Ace :Spades] is ace of spades
; a hand is a seq of cards e. g. [[:Ace :Spades] [:Two :Clubs]] is ace of spades and 2 of clubs

(defn ranks [hand] (map first hand))

(defn #^{:test (fn []
    (assert (= {} (member-counts '())))
    (assert (= {:a 1} (member-counts '(:a))))
    (assert (= {:a 2} (member-counts '(:a :a))))
    (assert (= {:a 2 :b 1} (member-counts '(:a :b :a))))
    (assert (= {:a 1 :b 1 :c 1 :d 1} (member-counts '(:a :b :c :d))))
    (assert (= {:a 2 :b 1 :c 2 :d 1} (member-counts '(:a :b :c :c :a :d))))
  )}
  member-counts
  [coll]
    (reduce (fn [result value] (assoc result value 
      (if (contains? result value) 
        (+ 1 (result value)) 
        1)
      )) {} coll)
)

(defn sets [how-many] (fn [hand] (set (map first (filter #(= how-many (second %)) (member-counts (ranks hand)))))))

(defn
  #^{:test (fn []
    (assert (= #{} (pairs [])))
    (assert (= #{} (pairs [[:Ace :Spades]])))
    (assert (= #{:Ace} (pairs [[:Ace :Spades], [:Ace :Hearts]])))
    (assert (= #{:Ace} (pairs [[:Ace :Spades], [:Ace :Hearts], [:King :Hearts]])))
    (assert (= #{:Ace :King} (pairs [[:Ace :Spades], [:Ace :Hearts], [:King :Hearts], [:King :Clubs]])))
    (assert (= #{} (pairs [[:Ace :Spades], [:Ace :Hearts], [:Ace :Diamonds]])))
    (assert (= #{} (pairs [[:Ace :Spades], [:Queen :Hearts], [:King :Hearts]]))))}
  pairs [hand]
  ((sets 2) hand))

(defn
  #^{:test (fn []
    (assert (= #{} (threes [])))
    (assert (= #{} (threes [[:Ace :Spades]])))
    (assert (= #{} (threes [[:Ace :Spades] [:Ace :Hearts]])))
    (assert (= #{} (threes [[:Ace :Spades] [:Ace :Hearts] [:King :Hearts]])))
    (assert (= #{:Ace} (threes [[:Ace :Spades] [:Ace :Hearts] [:Ace :Diamonds]])))
    (assert (= #{:Ace} (threes [[:Ace :Spades] [:Ace :Hearts] [:Two :Clubs] [:Ace :Diamonds]])))
    (assert (= #{} (threes [[:Ace :Spades] [:King :Hearts] [:Two :Clubs] [:Ace :Diamonds]])))
    (assert (= #{:Ace :Two} (threes [[:Ace :Spades] [:Ace :Hearts] [:Two :Clubs] [:Ace :Diamonds] [:Two :Hearts] [:Two :Diamonds]])))
    (assert (= #{} (threes [[:Ace :Spades] [:Queen :Hearts] [:King :Hearts]]))))}
  threes [hand]
  ((sets 3) hand))

(test #'member-counts)
(test #'pairs)
(test #'threes)
