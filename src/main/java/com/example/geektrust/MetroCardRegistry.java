package com.example.geektrust;

import java.util.HashMap;
import java.util.Map;

public class MetroCardRegistry {
    private final Map<String, MetroCard> cards = new HashMap<>();

    public void addCard(String cardId, MetroCard card) {
        cards.put(cardId, card);
    }

    public MetroCard getCard(String cardId) {
        return cards.get(cardId);
    }

}
