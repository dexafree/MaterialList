package com.dexafree.materialList.events;

import android.view.View;

import com.dexafree.materialList.model.Card;

public class DismissEvent {

    private Card dismissedCard;
    private View cardView;

    public DismissEvent(Card dismissedCard, View cardView) {
        this.dismissedCard = dismissedCard;
        this.cardView = cardView;
    }

    public Card getDismissedCard() {
        return dismissedCard;
    }

    public void setDismissedCard(Card dismissedCard) {
        this.dismissedCard = dismissedCard;
    }

    public View getCardView() {
        return cardView;
    }

    public void setCardView(View cardView) {
        this.cardView = cardView;
    }
}
