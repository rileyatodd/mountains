(ns mountains.core
  (:require [quil.core :as q]
            [quil.middleware :as m])
  (:require [mountains.dynamic :as dyn])
  (:require [genartlib.util :as u])
  (:gen-class))

(q/defsketch sketch
  :title "Magnificent Mountains"
  :size [500 500]
  ; setup function called only once, during sketch initialization.
  :setup dyn/setup
  ; update-state is called on each iteration before draw-state.
  :update dyn/update-state
  :draw dyn/draw-state
  :features [:keep-on-top]
  ; This sketch uses functional-mode middleware.
  ; Check quil wiki for more info about middlewares and particularly
  ; fun-mode.
  :middleware [m/fun-mode])

(defn refresh []
  (use :reload 'mountains.dynamic)
  (.redraw sketch))

(defn get-applet []
  sketch)