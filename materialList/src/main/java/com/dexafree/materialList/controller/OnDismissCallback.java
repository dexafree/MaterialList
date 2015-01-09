package com.dexafree.materialList.controller;

import com.dexafree.materialList.cards.model.Card;

public interface OnDismissCallback {
    void onDismiss(Card card, int position);
}
