; a card is a two element vector of keyworks [:suit :rank] e.g. [:S :A] is ace of spades
; a hand is a seq of cards e. g. [[:S :A] [:C :2]] is ace of spades and 2 of clubs

(defn ranks [hand] (map second hand))

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
    (assert (false? (has-pair [[:S :A]])))
    (assert (true? (has-pair [[:S :A], [:H :A]])))
    (assert (true? (has-pair [[:S :A], [:H :A], [:H :K]])))
    (assert (true? (has-pair [[:S :A], [:H :A], [:D :A]])))
    (assert (false? (has-pair [[:S :A], [:H :Q], [:H :K]]))))}
  has-pair [hand]
  (not= (count (distinct (ranks hand))) (count hand)))

(defn
  #^{:test (fn []
    (assert (false? (has-three-of-a-kind [])))
    (assert (false? (has-three-of-a-kind [[:S :A]])))
    (assert (false? (has-three-of-a-kind [[:S :A] [:H :A]])))
    (assert (false? (has-three-of-a-kind [[:S :A] [:H :A] [:H :K]])))
    (assert (true? (has-three-of-a-kind [[:S :A] [:H :A] [:D :A]])))
    (assert (true? (has-three-of-a-kind [[:S :A] [:H :A] [:C :2] [:D :A]])))
    (assert (false? (has-three-of-a-kind [[:S :A] [:H :K] [:C :2] [:D :A]])))
    (assert (false? (has-three-of-a-kind [[:S :A] [:H :Q] [:H :K]]))))}
  has-three-of-a-kind [hand]
  (not= (count (distinct (ranks hand))) (count hand)))


(test #'member-counts)
(test #'has-pair)
(test #'has-three-of-a-kind)
