(def suits [:S :H :D :C])
(def ranks [:A :K :Q :J :10])
(defstruct card :suit :rank)

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


