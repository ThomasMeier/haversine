# Haversine

Given two points composed of latitude and longitude, this library will find the distance between them in kilometers.

Additionally, you can supply one point of latitude and longitude and a distance in kilometers, it will provide boundary latitude and longitude.

## Installation

To install, add the following to your project `:dependencies`:

    [haversine "0.1.1"]

## Usage

Find the distance (in km) between two points of latitude and longitude

    (haversine/haversine {:latitude 30 :longitude -80} {:latitude 32 :longitude -110})
    => 2859.7117596927574
    
Find a range of latitude and longitude for a distance around a point of latitude and longitude.

    (haversine/neighborhood {:latitude 44 :longitude 100 :distance-from 80})
    => {:maxlatitude 44.72072072072072
        :minlatitude 43.27927927927928
        :maxlongitude 101.00605611299432
        :minlongitude 98.99394388700568}

## Documentation

See ./docs/haversine.html

## License

Copyright © 2014 Thomas Meier

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
