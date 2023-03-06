package com.example.geektrust.commands;

import com.example.geektrust.MetroCard;
import com.example.geektrust.MetroCardRegistry;

public class BalanceCommand implements Command {
    private String cardId;
    private int balance;
    private MetroCardRegistry register;

    public BalanceCommand(String cardId, int balance,
                          MetroCardRegistry register
    ){
        this.cardId = cardId;
        this.balance = balance;
        this.register = register;
    }

    @Override
    public void execute() {
        register.addCard(cardId, new MetroCard(balance));
    }
}
