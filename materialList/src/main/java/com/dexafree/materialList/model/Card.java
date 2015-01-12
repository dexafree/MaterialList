package com.dexafree.materialList.model;

/**
 * The Card is the base class of all Card Models.
 */
public abstract class Card {
    private boolean mDismissible;

    public boolean isDismissible() {
        return mDismissible;
    }

    public void setDismissible(boolean canDismiss) {
        this.mDismissible = canDismiss;
    }

    public abstract int getLayout();
}
