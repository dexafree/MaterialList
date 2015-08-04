package com.dexafree.materialList.card;

import android.view.View;

/**
 * The OnButtonClickListener will be notified if a Button is clicked on the Card.
 */
public interface OnButtonClickListener {
    /**
     * A button is clicked.
     *
     * @param view
     *         which was clicked.
     * @param card
     *         where the view was clicked on.
     */
    void onButtonClicked(View view, Card card);
}
