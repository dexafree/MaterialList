package com.dexafree.materialList.controller;

import com.dexafree.materialList.model.Card;

public interface OnDismissCallback {
    void onDismiss(Card card, int position);
}
