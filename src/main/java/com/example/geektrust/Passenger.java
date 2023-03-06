package com.example.geektrust;

public class Passenger {
    private MetroCard card;
    private PassengerType passengerType;

    private Passenger(MetroCard card, PassengerType passengerType) {
        this.card = card;
        this.passengerType = passengerType;
    }

    public MetroCard getCard() {
        return card;
    }

    public PassengerType getPassengerType() {
        return passengerType;
    }

    // Singleton pattern
    private static Passenger passenger;

    public static Passenger setInstance(MetroCard card, PassengerType passengerType) {
        if(passenger == null)
            passenger = new Passenger(card, passengerType);
        else {
            passenger.card = card;
            passenger.passengerType = passengerType;
        }

        return passenger;
    }
}
