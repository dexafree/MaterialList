package com.dexafree.materialList.card;

import android.content.Context;
import android.support.annotation.LayoutRes;

import java.util.Observable;

public abstract class CardConfig extends Observable {
    private Context mContext;
    private Card.Builder mBuilder;

    /**
     * Do NOT use this method! Only for the {@code Card.Builder}!
     *
     * @param context
     *         to access the resources.
     */
    void setContext(Context context) {
        mContext = context;
        onCreated();
    }

    /**
     * Do NOT use this method! Only for the {@code Card.Builder}!
     *
     * @param builder
     *         to return the {@code Card.Builder} by {@code endConfig}.
     */
    void setBuilder(Card.Builder builder) {
        mBuilder = builder;
    }

    /**
     * Do something after the context is set.
     */
    protected void onCreated() {
    }

    /**
     * Get the context.
     *
     * @return the context.
     */
    protected Context getContext() {
        return mContext;
    }

    /**
     * End with the configuration.
     *
     * @return the {@code Card.Builder}.
     */
    public Card.Builder endConfig() {
        return mBuilder;
    }

    /**
     * Notifies the Card that the content changed.
     */
    public void notifyDataSetChanged() {
        setChanged();
        notifyObservers();
    }

    /**
     * Get the card layout as resource.
     *
     * @return the card layout.
     */
    @LayoutRes
    public abstract int getLayout();
}
