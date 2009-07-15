(load-file "C:\\alex\\code\\clj-poker\\deck.clj")

(defn new-game []
  (dosync
    (ref-set deck (shuffle all-cards))
    (def hands [(ref []) (ref []) (ref [])])
    (println "New game")))


(defn take-card []
  (dosync 
    (let [card (first @deck)] 
      (alter deck #(drop 1 %))
      card)))

(defn deal []
  (dosync (doall  ; doall needed to ensure map occurs inside transaction
    (map 
      (fn [hand] (alter hand conj (take-card))) 
      hands)) nil))

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
