(load-file "C:\\alex\\code\\clj-poker\\deck.clj")
(def deck (ref []))
(def hands [(ref [])(ref [])])

(defn new-game []
  (dosync
    (ref-set deck (shuffle all-cards))
    (doseq [hand hands]
      (ref-set hand []))
    (println "New game")))


(defn take-card [deck]
  (list (first deck) (drop 1 deck)))

(defn deal []
  (doseq [hand hands] 
    (dosync 
      (let [[card new-deck] (take-card @deck)] 
        (alter hand conj card)
        (ref-set deck new-deck)))))
      
(defn show-hands []
  (do
    (println "Hands now:")
    (doseq [h hands] (println @h))))

(show-hands)
(new-game)
(show-hands)
(deal)
(show-hands)
(deal)
(show-hands)
(deal)
(show-hands)
(deal)
(show-hands)
(deal)
(show-hands)




