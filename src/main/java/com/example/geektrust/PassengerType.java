package com.example.geektrust;

public enum PassengerType {
    ADULT(200),
    SENIOR_CITIZEN(100),
    KID(50);

    private final int stdTravelCharge;
    PassengerType(int stdTravelCharge) {
        this.stdTravelCharge = stdTravelCharge;
    }

    public int getStdTravelCharge() {
        return stdTravelCharge;
    }
}
