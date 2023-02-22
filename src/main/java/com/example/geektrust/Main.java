package com.example.geektrust;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class Main {
    static Map<String, Integer> metroCards = new HashMap<>();
    static Map<String, Integer> cardUseCount = new HashMap<>();
    static class Station {
        String name;
        Map<PassengerType, Integer> passengerTypeCount = new TreeMap<>();
        Long totalCharges = 0L;
        Long discount = 0L;

        Station(String name) {
            this.name = name;
        }
    }

    public static void main(String[] args) {
        Station central = new Station("CENTRAL");
        Station airport = new Station("AIRPORT");

        try{
            FileInputStream fis = new FileInputStream(args[0]);
            Scanner sc = new Scanner(fis);
            while(sc.hasNextLine()) {
                String[] line = sc.nextLine().split(" ");
                String command = line[0];
                switch (command) {
                    case "BALANCE": {
                        String cardId = line[1];
                        Integer amount = Integer.parseInt(line[2]);
                        metroCards.put(cardId, amount);
                        break;
                    }
                    case "CHECK_IN": {
                        String cardId = line[1];
                        PassengerType passengerType;
                        if (PassengerType.ADULT.toString().equals(line[2]))
                            passengerType = PassengerType.ADULT;
                        else if (PassengerType.SENIOR_CITIZEN.toString().equals(line[2]))
                            passengerType = PassengerType.SENIOR_CITIZEN;
                        else if (PassengerType.KID.toString().equals(line[2]))
                            passengerType = PassengerType.KID;
                        else
                            throw new IllegalArgumentException("Invalid Passenger Type");

                        String station = line[3];
                        if(station.matches("CENTRAL"))
                            calculate(cardId, passengerType, central);
                        else if(station.matches("AIRPORT"))
                            calculate(cardId, passengerType, airport);
                        else throw new IllegalArgumentException("Invalid Station names!!");
                        break;
                    }
                    case "PRINT_SUMMARY":
                        summary(central);
                        summary(airport);
                        break;
                    default:
                        throw new IllegalArgumentException("Invalid Command!!");
                }
            }

            sc.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void calculate(String cardId, PassengerType passengerType, Station station) {
        station.passengerTypeCount.merge(passengerType, 1, Integer::sum);
        cardUseCount.merge(cardId, 1, Integer::sum);

        switch (passengerType) {
            case ADULT:
                long travelCharges = 200;
                if(isReturnJourney(cardId)) {
                    travelCharges /= 2;
                    station.discount += travelCharges;
                }

                long balance = metroCards.get(cardId);
                balance -= travelCharges;
                if(balance < 0) {
                    double extraAmount = -balance;
                    double serviceFee = extraAmount * 0.02;
                    travelCharges += serviceFee;
                }
                station.totalCharges += travelCharges;
                break;
            case SENIOR_CITIZEN:
                travelCharges = 100;
                if(isReturnJourney(cardId)) {
                    travelCharges /= 2;
                    station.discount += travelCharges;
                }

                balance = metroCards.get(cardId);
                balance -= travelCharges;
                if(balance < 0) {
                    double extraAmount = -balance;
                    double serviceFee = extraAmount * 0.02;
                    travelCharges += serviceFee;
                }
                station.totalCharges += travelCharges;
                break;
            case KID:
                travelCharges = 50;
                if(isReturnJourney(cardId)) {
                    travelCharges /= 2;
                    station.discount += travelCharges;
                }

                balance = metroCards.get(cardId);
                balance -= travelCharges;
                if(balance < 0) {
                    double extraAmount = -balance;
                    double serviceFee = extraAmount * 0.02;
                    travelCharges += serviceFee;
                }
                station.totalCharges += travelCharges;
        }
    }

    private static boolean isReturnJourney(String cardId) {
        int cardUsage = cardUseCount.get(cardId);
        return cardUsage % 2 == 0;
    }

    private static void summary(Station station) {
        System.out.printf("TOTAL_COLLECTION %s %d %d\n", station.name, station.totalCharges, station.discount);
        System.out.println("PASSENGER_TYPE_SUMMARY");
        for(Map.Entry<PassengerType, Integer> passengerType: station.passengerTypeCount.entrySet()){
            System.out.printf("%s %d\n", passengerType.getKey().toString(), passengerType.getValue());
        }
    }
}
