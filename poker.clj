; a card is a two element vector of keyworks [:suit :rank] e.g. [:S :A] is ace of spades
; a hand is a seq of cards e.g. [[:S :A] [:C :2]] is ace of spades and 2 of clubs

(defn ranks [hand] (map (fn [coll] (get coll 1)) hand))

(defn
  #^{:test (fn []
    (assert (false? (has-pair [])))
    (assert (false? (has-pair [[:S :A]])))
    (assert (true? (has-pair [[:S :A], [:H :A]])))
    (assert (true? (has-pair [[:S :A], [:H :A], [:H :K]])))
    (assert (true? (has-pair [[:S :A], [:H :A], [:D :A]])))
    (assert (false? (has-pair [[:S :A], [:H :Q], [:H :K]])))
  )}
  has-pair 
  [hand]
    (not= (count (distinct (ranks hand))) (count hand)))
(test #'has-pair)
