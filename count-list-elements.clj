(defn
  #^{:test (fn []
    (assert (= 1 (count-first [:a :b])))
    (assert (= 2 (count-first [:a :b :a])))
    (assert (= 3 (count-first [:a :b :c :a :a :d :c]))))}
  count-first [coll] 
  (count (filter #{(first coll)} coll)))

(test #'count-first)

