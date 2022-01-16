(ns mountains.dynamic
  (:require [quil.core :as q])
  (:require [genartlib.util :as u])
  (:require [genartlib.random :refer :all])
  (:require [genartlib.algebra :refer :all])
  (:require [clojure.math.numeric-tower :refer :all]))

(defonce play-state (atom {:physics false
                           :color false
                           :all false}))

(defn play-pause [k] 
  (swap! play-state #(update-in % [k] not)))

(defn plot [f xs]
  (doseq [x xs]
    (q/point x (f x))))

(defn plot-points [ps]
  (doseq [[x y] ps]
    (q/point x y)))

(defn initial-state []
  {:mountains []})

(defn setup []
  (q/color-mode :hsb)
  (q/smooth)
  (q/no-loop)

  (initial-state))

(defonce reset-state? (atom false))

(defn reset-state []
  (swap! reset-state? (fn [_] true)))

(defn update-state [state]
  (if @reset-state?
    (do
      (swap! reset-state? (fn [_] false))
      (initial-state))
    state))

(defn draw-state [state]
  (q/background 60)
  (q/stroke 255)
  (doseq [c (:mountains state)]
     (q/stroke (:hue c) 35 255)))
  
  
