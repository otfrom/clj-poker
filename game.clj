(def deck (ref (shuffle all-cards)))
(def hands [(ref []) (ref []) (ref [])])

(defn take-card []
  (dosync 
    (let [card (first @deck)] 
    (alter deck #(drop 1 %))
      card)))
(defn deal []
  (dosync (doall  ; doall needed to ensure map occurs inside transaction
    (map 
      (fn [hand] (alter hand conj take-card)) 
      hands))))

