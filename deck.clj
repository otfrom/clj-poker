(def suits '(:C :D :H :S))
(def ranks '(:A :K :Q :J :10))
(def all-cards  (for [suit suits rank ranks] [rank suit]))
(defn #^{:test (fn []
    (assert (true? (is-card [:A :C])))
    (assert (false? (is-card [:A :A])))
  )}
  is-card [card] (not (nil? (some #{card} all-cards))))

(defn #^{:test (fn []
    (assert (= 20 (count all-cards)))
    (assert (every? #(= % 1) (map #(count (filter #{%1} all-cards)) all-cards)))
  )}
  test-all-cards [] nil)


(test #'test-all-cards)
(test #'is-card)
