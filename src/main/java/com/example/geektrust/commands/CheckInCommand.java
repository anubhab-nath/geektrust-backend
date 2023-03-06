package com.example.geektrust.commands;

import com.example.geektrust.*;

public class CheckInCommand implements Command {
    private MetroCard metroCard;
    private PassengerType passengerType;
    private Station station;

    public CheckInCommand(String card, String passengerType, String station,
                          MetroCardRegistry cardRegistry, StationRegistry stationRegistry
    ) {
        this.metroCard = cardRegistry.getCard(card);
        this.passengerType = PassengerType.valueOf(passengerType);
        this.station = stationRegistry.getStation(station);
    }

    @Override
    public void execute() {
        this.station.calculateTravelCharge(
                Passenger.setInstance(metroCard, passengerType)
        );
    }
}
