package com.dexafree.materialList.card.event;

import android.support.annotation.NonNull;

import com.dexafree.materialList.card.Card;

public class DismissEvent {
    private final Card mCard;

    public DismissEvent(@NonNull final Card card) {
        mCard = card;
    }

    public Card getCard() {
        return mCard;
    }
}
