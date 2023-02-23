package com.example.geektrust;

public class Passenger {
    private MetroCard card;
    private PassengerType passengerType;

    private Passenger(MetroCard card, PassengerType passengerType) {
        this.card = card;
        this.passengerType = passengerType;
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

    public void calculateTravelCharge(Station station) {
        int travelCharge = this.passengerType.getStdTravelCharge();
        station.getPassengerTypeCount()
                .merge(this.passengerType, 1, Integer::sum);
        card.updateCardUsage(1);

        if(isReturnJourney()) {
            travelCharge /= 2;
            station.updateDiscount(travelCharge);
        }

        int balance = card.getBalance();
        balance -= travelCharge;
        if(balance < 0) {
            int extra = -1 * balance;
            int serviceFee = (extra * 2)/100;
            travelCharge += serviceFee;
            balance = 0;
        }

        station.updateTotalCharges(travelCharge);
        card.updateBalance(balance);
    }

    private boolean isReturnJourney() {
        int cardUsage = this.card.getCardUsage();
        return cardUsage % 2 == 0;
    }
}
