(load-file "C:\\alex\\code\\clj-poker\\deck.clj")

(defn new-game []
  (dosync
    (ref-set deck (shuffle all-cards))
    (def hands [(ref []) (ref []) (ref [])])
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



