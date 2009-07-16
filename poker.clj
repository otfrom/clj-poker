; a card is a two element vector of keyworks [:rank :suit] e.g. [:A :S] is ace of spades
; a hand is a seq of cards e. g. [[:A :S] [:2 :C]] is ace of spades and 2 of clubs

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

(defn sets [how-many] 
  (fn [hand] 
    (set                                              ; return the result as a set
      (map first (filter                              ; take the ranks
                    #(= how-many (second %))          ; whose count is how-many
                    (member-counts (ranks hand))))))) ; count how many of each rank occur in each hand

(defn
  #^{:test (fn []
    (assert (= #{} (pairs [])))
    (assert (= #{} (pairs [[:A :S]])))
    (assert (= #{:A} (pairs [[:A :S], [:A :H]])))
    (assert (= #{:A} (pairs [[:A :S], [:A :H], [:K :H]])))
    (assert (= #{:A :K} (pairs [[:A :S], [:A :H], [:K :H], [:K :C]])))
    (assert (= #{} (pairs [[:A :S], [:A :H], [:A :D]])))
    (assert (= #{} (pairs [[:A :S], [:Q :H], [:K :H]]))))}
  pairs [hand]
  ((sets 2) hand))

(defn
  #^{:test (fn []
    (assert (= #{} (threes [])))
    (assert (= #{} (threes [[:A :S]])))
    (assert (= #{} (threes [[:A :S] [:A :H]])))
    (assert (= #{} (threes [[:A :S] [:A :H] [:K :H]])))
    (assert (= #{:A} (threes [[:A :S] [:A :H] [:A :D]])))
    (assert (= #{:A} (threes [[:A :S] [:A :H] [:2 :C] [:A :D]])))
    (assert (= #{} (threes [[:A :S] [:K :H] [:2 :C] [:A :D]])))
    (assert (= #{:A :2} (threes [[:A :S] [:A :H] [:2 :C] [:A :D] [:2 :H] [:2 :D]])))
    (assert (= #{} (threes [[:A :S] [:Q :H] [:K :H]]))))}
  threes [hand]
  ((sets 3) hand))

(test #'member-counts)
(test #'pairs)
(test #'threes)

