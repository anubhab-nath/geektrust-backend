package com.example.geektrust.commands;

import com.example.geektrust.Station;
import com.example.geektrust.StationRegistry;

public class PrintSummaryCommand implements Command {
    private StationRegistry registry;

    public PrintSummaryCommand(StationRegistry registry) {
        this.registry = registry;
    }

    @Override
    public void execute() {
        for (Station station: registry.getAllStations()) {
            station.summary();
        }
    }
}
