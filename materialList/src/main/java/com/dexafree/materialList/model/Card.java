package com.dexafree.materialList.model;

/**
 * The Card is the base class of all Card Models.
 */
public abstract class Card {

    private Object tag;

    private boolean mDismissible;

    public boolean isDismissible() {
        return mDismissible;
    }

    public void setDismissible(boolean canDismiss) {
        this.mDismissible = canDismiss;
    }

    public abstract int getLayout();

    public Object getTag(){
        return tag;
    }

    public void setTag(Object tag){
        this.tag = tag;
    }
}
