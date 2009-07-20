(def *ranks* '(:A :K :Q :J :10))
(def *suits* '(:C :D :H :S))

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

(def deck (ref (shuffled-hand)))

(def players (ref []))

(defn new-game [n]
  (dosync
    (ref-set players (repeat n []))
    (ref-set deck (shuffled-hand))
    
  ))

(defn deal-a-card [deck]
  (split-at 1 deck))


(defn deal []
  (let [hand (take @players @deck)]
    (dosync
      (ref-set deck (drop @players @deck)))
  ))

(new-game 5)
(deal-a-card @deck)

;@players
