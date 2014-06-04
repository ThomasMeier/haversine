(ns haversine.core-test
  (:require [clojure.test :refer :all]
            [haversine.core :refer :all]))

(deftest test-alpha
  (is (= 0.7461574666247278 (alpha 30 32 2 20))))

(deftest test-haversine
  (is (= 2859.7117596927574 (haversine {:latitude 30 :longitude -80} {:latitude 32 :longitude -110}))))

(deftest test-anti-alpha
  (is (= 0.0235353612219711 (anti-alpha 300))))

(deftest test-delta-longitude
  (is (= 12.795111506707372 (delta-longitude 33 34 111))))

(deftest test-neighborhood
  (is (= {:maxlatitude 34.8018018018018
          :minlatitude 31.1981981981982
          :maxlongitude -26.774774136870302
          :minlongitude -61.225225863129694}
         (neighborhood {:latitude 33 :longitude -44 :distance-from 200}))))
