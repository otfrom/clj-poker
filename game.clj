(use 'clojure.set)

(def *ranks* '(:A :K :Q :J :10))
(def *suits* '(:C :D :H :S))
(def *card-order* {:A 5 :K 4 :Q 3 :J 2 :10 1 :9 0})

(def *cards*
  (for [rank *ranks* suit *suits*]
    [rank suit]))


(def empty-hand ())

(defn 
  #^{:test (fn []
    (assert (= 20 (count (shuffled-hand)))))}
  shuffled-hand [] 
    (let [array-cards (java.util.ArrayList. *cards*)]
    (java.util.Collections/shuffle array-cards)
     array-cards))

(test #'shuffled-hand)

(def *deck* (ref (shuffled-hand)))

(def *players* (ref []))

(defn new-game [n]
  (dosync
    (ref-set *players* (repeat n []))
    (ref-set *deck* (shuffled-hand))))

(defn deal-a-card [deck]   
 (split-at 1 deck))

(defn give-card-to-player [player]
  (dosync
   (let [[next-card remaining] (deal-a-card @*deck*)]
     (ref-set *deck* remaining)
     (conj player (first next-card)))))    
   
(defn deal []
  (dosync 
    (alter *players* #(map give-card-to-player %))))

(defn
  #^{:test (fn []
            (assert (has-pair [[:10 :C] [:10 :S]]))
            (assert (not (has-pair [[:9 :C] [:J :D]])))
            (assert (has-pair @*deck*)))}
    has-pair [hand]
     (not 
       (= (count (into #{} (map first hand))) (count hand))))

(defn
   #^{:test (fn []
      (assert (= {:A 2 :9 1}
                 (count-ranks [[:A :C] [:A :D] [:9 :D]]))))}
    count-ranks [hand]
     (reduce #(let [[rank suit] %2]
            (if (rank %1) 
               (assoc %1 rank (inc (rank %1)) )
               (assoc %1 rank 1)))
       {} hand))

(defn
   #^{:test (fn []
      (assert (= '(:A)
                 (all-pairs [[:A :C] [:A :D] [:9 :D]]))))}
  all-pairs [hand]
    (map first (filter 
      (fn [[rank count]] (> count 1)) 
      (count-ranks hand))))

(defn
   #^{:test (fn []
      (assert (= :A
                 (highest-pair [[:A :C] [:A :D] [:9 :D] [:9 :C]]))))}
  highest-pair [hand]
    ((map-invert 
      *card-order*) 
      (reduce max (map *card-order* (all-pairs hand)))))

     

(new-game 5)

(deal)
(deal)

@players

(test #'has-pair)
(test #'count-ranks)
(test #'all-pairs)
(test #'highest-pair)

