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

    public Map<PassengerType, Integer> getPassengerTypeCount() {
        return passengerTypeCount;
    }

    public void updateDiscount(int discount) {
        this.discount = discount;
    }

    public void updateTotalCharges(int totalCharges) {
        this.totalCharges += totalCharges;
    }

    public void summary() {
        System.out.printf("TOTAL_COLLECTION %s %d %d\n", this.name, this.totalCharges, this.discount);
        System.out.println("PASSENGER_TYPE_SUMMARY");
        for(Map.Entry<PassengerType, Integer> passengerType: this.passengerTypeCount.entrySet()){
            System.out.printf("%s %d\n", passengerType.getKey().toString(), passengerType.getValue());
        }
    }
}
