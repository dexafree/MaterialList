package com.dexafree.materialList.events;

import com.dexafree.materialList.model.Card;

public class DismissEvent {

    private final Card dismissedCard;

    public DismissEvent(Card dismissedCard) {
        this.dismissedCard = dismissedCard;
    }

    public Card getDismissedCard() {
        return dismissedCard;
    }
}
