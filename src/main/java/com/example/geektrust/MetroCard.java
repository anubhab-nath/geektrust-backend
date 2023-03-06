package com.example.geektrust;

public class MetroCard {
    private int balance;
    private int usage;

    public MetroCard(int balance) {
        this.balance = balance;
        this.usage = 0;
    }

    public int getBalance() {
        return balance;
    }

    public void updateBalance(int balance) {
        this.balance += balance;
        if(balance < 0)
            this.balance = 0;
    }

    public int getUsage() {
        return usage;
    }

    public void updateUsage(int usage) {
        this.usage += usage;
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) return true;
        if(obj == null) return false;
        if(this.getClass() != obj.getClass()) return false;

        MetroCard card = (MetroCard) obj;
        return this.balance == card.balance &&
                this.usage == card.usage;
    }
}
