package com.example.geektrust;

import com.example.geektrust.commands.CommandLineRunner;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        StationRegistry stationRegistry = new StationRegistry();
        MetroCardRegistry cardRegistry = new MetroCardRegistry();

        try(FileInputStream fis = new FileInputStream(args[0]);
            Scanner sc = new Scanner(fis)
        ){
            while(sc.hasNextLine()) {
                String[] command = sc.nextLine().split(" ");
//                String command = line[0];
//                switch (command) {
//                    case "BALANCE": {
//                        String cardId = line[1];
//                        int balance = Integer.parseInt(line[2]);
//                        cardRegistry.addCard(cardId, new MetroCard(balance));
//                        break;
//                    }
//                    case "CHECK_IN": {
//                        String cardId = line[1];
//                        MetroCard card = cardRegistry.getCard(cardId);
//
//                        PassengerType passengerType = PassengerType.valueOf(line[2]);
//                        Passenger passenger = Passenger.setInstance(card, passengerType);
//
//                        String stationName = line[3];
//                        Station station = stationRegistry.getStation(stationName);
//                        station.calculateTravelCharge(passenger);
//                        break;
//                    }
//                    case "PRINT_SUMMARY":
//                        break;
//                    default:
//                        throw new IllegalArgumentException("Invalid Command!!");
//                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
