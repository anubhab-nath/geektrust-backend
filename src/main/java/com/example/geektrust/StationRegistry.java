package com.example.geektrust;

import java.util.ArrayList;
import java.util.List;


public class StationRegistry {
    private final List<Station> stations = new ArrayList<>();

    public StationRegistry() {
        stations.add(new Station(StationName.CENTRAL.toString()));
        stations.add(new Station(StationName.AIRPORT.toString()));
    }

    public Station getStation(String stationName) {
        for(Station station: stations) {
            if(station.getName().equals(stationName))
                return station;
        }
        throw new IllegalArgumentException("Invalid Station name!!");
    }

    public List<Station> getAllStations() {
        return stations;
    }
}
