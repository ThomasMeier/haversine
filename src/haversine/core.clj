(ns haversine.core
  "The haversine method is an approximation of great circle distance.")

;; The assumption is that we are working on a perfect sphere. Obviously,
;; Earth is not a perfect sphere. There will be some discrepencies, typically
;; overestimation, since the great circle will not conform to a rough
;; surface.
(def earth-radius "In kilometers" 6372.8)

(defn alpha
  "Trigonometric calculations for use in haversine."
  [lat1 lat2 delta-lat delta-long]
  (+ (* (Math/sin (/ delta-lat 2)) (Math/sin (/ delta-lat 2)))
     (* (Math/sin (/ delta-long 2)) (Math/sin (/ delta-long 2))
        (Math/cos lat1) (Math/cos lat2))))

(defn haversine
  "Distance in km between two points of lat/long. Supply two maps of latitutde and longitude."
  [{lat1 :latitude long1 :longitude}
   {lat2 :latitude long2 :longitude}]
  (let [delta-lat (Math/toRadians (- lat2 lat1))
        delta-long (Math/toRadians (- long2 long1))
        lat1 (Math/toRadians lat1)
        lat2 (Math/toRadians lat2)]
    (* earth-radius 2 (Math/asin (Math/sqrt (alpha lat1 lat2 delta-lat delta-long))))))

;; This next section is derived from the Haversine, solving for the lat/long
;; required for the distance parameter to be satisfied. This will provide
;; a map of max/min lat/long for the distances provided. This is a rectangular
;; area, rather than a circular radius.
;;
;; This also assumes a basic distance of 111 kilometers for each degree
;; of latitude. It makes the algebra a little easier, but introduces an error
;; of up to ~0.75 kilometers, depending on how far from the equator you
;; go.
(defn anti-alpha
  "Finding the alpha value from distance and known values."
  [distance]
  (Math/sin (/ distance (* earth-radius 2))))

(defn delta-longitude
  "Find the difference in longitude required"
  [lat1 lat2 distance]
  (Math/toDegrees
  (* 2 (Math/asin
        (Math/sqrt
         (/ (- (anti-alpha distance)
               (* (Math/sin (/ (- (Math/toRadians lat1) (Math/toRadians lat2)) 2))
                  (Math/sin (/ (- (Math/toRadians lat1) (Math/toRadians lat2)) 2))))
            (* (Math/cos (Math/toRadians lat1)) (Math/cos (Math/toRadians lat2)))))))))

(defn neighborhood
  "Given a lat/long/distance map, this will provide a min/max lat/long map"
  [{lat :latitude long :longitude distance :distance-from}]
  (let [lat2 (double (/ distance 111))
        delta-long (delta-longitude lat (+ lat lat2) distance)]
     {:maxlatitude  (+ lat lat2)
      :minlatitude  (- lat lat2)
      :maxlongitude (+ long delta-long)
      :minlongitude (- long delta-long)}))
