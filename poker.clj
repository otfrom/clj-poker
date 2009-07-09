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

(defn
  #^{:test (fn []
    (assert (false? (has-pair [])))
    (assert (false? (has-pair [[:Ace :Spades]])))
    (assert (true? (has-pair [[:Ace :Spades], [:Ace :Hearts]])))
    (assert (true? (has-pair [[:Ace :Spades], [:Ace :Hearts], [:King :Hearts]])))
    (assert (true? (has-pair [[:Ace :Spades], [:Ace :Hearts], [:Ace :Diamonds]])))
    (assert (false? (has-pair [[:Ace :Spades], [:Queen :Hearts], [:King :Hearts]]))))}
  has-pair [hand]
  (if (some #(> % 1) (vals (member-counts (ranks hand)))) true false))

(defn
  #^{:test (fn []
    (assert (false? (has-three-of-a-kind [])))
    (assert (false? (has-three-of-a-kind [[:Ace :Spades]])))
    (assert (false? (has-three-of-a-kind [[:Ace :Spades] [:Ace :Hearts]])))
    (assert (false? (has-three-of-a-kind [[:Ace :Spades] [:Ace :Hearts] [:King :Hearts]])))
    (assert (true? (has-three-of-a-kind [[:Ace :Spades] [:Ace :Hearts] [:Ace :Diamonds]])))
    (assert (true? (has-three-of-a-kind [[:Ace :Spades] [:Ace :Hearts] [:Two :Clubs] [:Ace :Diamonds]])))
    (assert (false? (has-three-of-a-kind [[:Ace :Spades] [:King :Hearts] [:Two :Clubs] [:Ace :Diamonds]])))
    (assert (false? (has-three-of-a-kind [[:Ace :Spades] [:Queen :Hearts] [:King :Hearts]]))))}
  has-three-of-a-kind [hand]
  (if (some #(> % 2) (vals (member-counts (ranks hand)))) true false))


(test #'member-counts)
(test #'has-pair)
(test #'has-three-of-a-kind)
