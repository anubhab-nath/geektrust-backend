package com.example.geektrust;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Station central = new Station("CENTRAL");
        Station airport = new Station("AIRPORT");
        MetroCardRegister register = new MetroCardRegister();

        try(FileInputStream fis = new FileInputStream(args[0]);
            Scanner sc = new Scanner(fis)
        ){
            while(sc.hasNextLine()) {
                String[] line = sc.nextLine().split(" ");
                String command = line[0];
                switch (command) {
                    case "BALANCE": {
                        String cardId = line[1];
                        int balance = Integer.parseInt(line[2]);
                        register.add(cardId, new MetroCard(balance));
                        break;
                    }
                    case "CHECK_IN": {
                        String cardId = line[1];
                        MetroCard card = register.getCard(cardId);
                        PassengerType passengerType = PassengerType.valueOf(line[2]);
                        String station = line[3];
                        if(station.matches("CENTRAL"))
                            Passenger.setInstance(card, passengerType)
                                    .calculateTravelCharge(central);
                        else if(station.matches("AIRPORT"))
                            Passenger.setInstance(card, passengerType)
                                    .calculateTravelCharge(airport);
                        else
                            throw new IllegalArgumentException("Invalid Station names!!");
                        break;
                    }
                    case "PRINT_SUMMARY":
                        central.summary();
                        airport.summary();
                        break;
                    default:
                        throw new IllegalArgumentException("Invalid Command!!");
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
