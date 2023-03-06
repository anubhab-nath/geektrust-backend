package com.example.geektrust;

import java.util.Map;
import java.util.TreeMap;

public class Station {
    private final String name;
    private final Map<PassengerType, Integer> passengerTypeCount = new TreeMap<>();
    private int totalCharges = 0;
    private int discount = 0;

    public Station(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void calculateTravelCharge(Passenger passenger) {
        MetroCard card = passenger.getCard();
        PassengerType passengerType = passenger.getPassengerType();

        int travelCharge = passengerType.getStdTravelCharge();
        this.passengerTypeCount.merge(passengerType, 1, Integer::sum);
        card.updateUsage(1);

        if(isReturnJourney(card.getUsage())) {
            travelCharge /= 2;
            this.discount += travelCharge;
        }

        int balance = card.getBalance();
        balance -= travelCharge;
        if(balance < 0) {
            int extra = -1 * balance;
            double serviceFee = extra * StationKeywords.STATION_SERVICE_FEE;
            travelCharge += serviceFee;
        }

        this.totalCharges += travelCharge;
        card.updateBalance(balance);
    }

    private boolean isReturnJourney(int cardUsage) {
        return cardUsage % 2 == 0;
    }

    public void summary() {
        System.out.printf("TOTAL_COLLECTION %s %d %d\n", this.name, this.totalCharges, this.discount);
        System.out.println("PASSENGER_TYPE_SUMMARY");
        for(Map.Entry<PassengerType, Integer> passengerType: this.passengerTypeCount.entrySet()){
            System.out.printf("%s %d\n", passengerType.getKey().toString(), passengerType.getValue());
        }
    }
}
