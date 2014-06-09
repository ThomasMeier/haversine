# Haversine

Given two points composed of latitude and longitude, this library will find the distance between them in kilometers.

Additionally, you can supply one point of latitude and longitude and a distance in kilometers, it will provide boundary latitude and longitude.

## Installation

To install, add the following to your project `:dependencies`:

    [haversine "0.1.0"]

## Usage

Find the distance (in km) between two points of latitude and longitude

    (haversine/haversine {:latitude 30 :longitude -80} {:latitude 32 :longitude -110})
    => 2859.7117596927574
    
Find a range of latitude and longitude for a distance around a point of latitude and longitude.

    (haversine/neighborhood {:latitude 33 :longitude -44 :distance-from 200})
    => {:maxlatitude 34.8018018018018
        :minlatitude 31.1981981981982
        :maxlongitude -26.774774136870302
        :minlongitude -61.225225863129694}

## Documentation

See ./docs/haversine.html

## License

Copyright © 2014 Thomas Meier

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
