package com.dexafree.materialList.cards;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.dexafree.materialList.cards.renderer.CardRenderer;

/**
 * A simple card with nothing on it to configure.
 */
public class Card {
    @NonNull
    private final Context mContext;
    @NonNull
    private CardRenderer mCardRenderer;
    @Nullable
    private Object mTagObject;
    private boolean mDismissible;

    /**
     * @param builder
     */
    private Card(@NonNull final Builder builder) {
        mContext = builder.mContext;
        mCardRenderer = builder.mCardRenderer;
        mTagObject = builder.mTagObject;
        mDismissible = builder.mDismissible;
    }

    /**
     * @return the card style.
     */
    @NonNull
    public CardRenderer getRenderer() {
        return mCardRenderer;
    }

    /**
     * @param object
     */
    public void setTag(@Nullable final Object object) {
        mTagObject = object;
    }

    /**
     * @return
     */
    @Nullable
    public Object getTag() {
        return mTagObject;
    }

    /**
     * @param dismissible
     */
    public void setDismissible(final boolean dismissible) {
        mDismissible = dismissible;
    }

    /**
     * @return
     */
    public boolean isDismissible() {
        return mDismissible;
    }

    /**
     * @return
     */
    @NonNull
    public Context getContext() {
        return mContext;
    }

    /**
     *
     */
    public static class Builder {
        @NonNull
        private final Context mContext;
        private CardRenderer mCardRenderer;
        @Nullable
        private Object mTagObject;
        private boolean mDismissible;

        /**
         * @param context
         */
        public Builder(@NonNull final Context context) {
            mContext = context;
        }

        /**
         * @param object
         */
        public Builder setTag(@Nullable final Object object) {
            mTagObject = object;
            return this;
        }

        /**
         *
         */
        public Builder setDismissible() {
            mDismissible = true;
            return this;
        }

        /**
         * @param cardRenderer
         * @return
         */
        public Card build(@NonNull final CardRenderer cardRenderer) {
            mCardRenderer = cardRenderer;
            return new Card(this);
        }
    }
}
