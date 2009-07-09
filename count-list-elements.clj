(defn
  #^{:test (fn []
    (assert (= 1 (count-first [:a :b])))
    (assert (= 2 (count-first [:a :b :a])))
    (assert (= 3 (count-first [:a :b :c :a :a :d :c]))))}
  count-first [coll] 
  (count (filter #{(first coll)} coll)))

(test #'count-first)

(defn
  #^{:test (fn []
    (assert (= {} (member-counts '())))
    (assert (= {:a 1} (member-counts '(:a))))
    (assert (= {:a 2} (member-counts '(:a :a))))
    (assert (= {:a 2 :b 1} (member-counts '(:a :b :a))))
  )}
   member-counts [coll]
   (reduce (fn [result value] (if (contains? result value) (assoc result value (+ 1 (result value))) (assoc result value 1))) {} coll))

(member-counts '(:a :b))
(member-counts '(:a :b :a))
(test #'member-counts)
