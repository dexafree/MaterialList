package com.dexafree.materialList.controller;

import android.support.annotation.NonNull;

import com.dexafree.materialList.cards.Card;

public interface OnDismissCallback {
    void onDismiss(@NonNull final Card card, int position);
}
