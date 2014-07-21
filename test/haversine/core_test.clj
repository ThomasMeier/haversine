(ns haversine.core-test
  (:require [clojure.test :refer :all]
            [haversine.core :refer :all]))

(deftest test-haversine
  (is (= 2859.7117596927574 (haversine {:latitude 30 :longitude -80} {:latitude 32 :longitude -110}))))

(deftest test-neighborhood
  (is (= {:maxlatitude 44.72072072072072
          :minlatitude 43.27927927927928
          :maxlongitude 101.00605611299432
          :minlongitude 98.99394388700568}
         (neighborhood {:latitude 44 :longitude 100 :distance-from 80}))))
