package com.example.geektrust;

public class MetroCard {
    private int balance;
    private int cardUsage;

    public MetroCard(int balance) {
        this.balance = balance;
        this.cardUsage = 0;
    }

    public int getBalance() {
        return balance;
    }

    public void updateBalance(int balance) {
        this.balance += balance;
    }

    public int getCardUsage() {
        return cardUsage;
    }

    public void updateCardUsage(int cardUsage) {
        this.cardUsage += cardUsage;
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) return true;
        if(obj == null) return false;
        if(this.getClass() != obj.getClass()) return false;

        MetroCard card = (MetroCard) obj;
        return this.balance == card.balance &&
                this.cardUsage == card.cardUsage;
    }
}
