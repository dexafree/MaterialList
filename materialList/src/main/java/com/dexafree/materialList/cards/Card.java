package com.dexafree.materialList.cards;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.dexafree.materialList.cards.renderer.CardRenderer;

/**
 * A basic Card. The CardRenderer will define the specific content and style.
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
     * Creates a new card.
     *
     * @param builder
     *         to get the card data from.
     */
    private Card(@NonNull final Builder builder) {
        mContext = builder.mContext;
        mCardRenderer = builder.mCardRenderer;
        mTagObject = builder.mTagObject;
        mDismissible = builder.mDismissible;
    }

    /**
     * Get the card renderer.
     *
     * @return the card renderer.
     */
    @NonNull
    public CardRenderer getRenderer() {
        return mCardRenderer;
    }

    /**
     * Set the tag.
     *
     * @param object
     *         as tag.
     */
    public void setTag(@Nullable final Object object) {
        mTagObject = object;
    }

    /**
     * Get the tag.
     *
     * @return the tag.
     */
    @Nullable
    public Object getTag() {
        return mTagObject;
    }

    /**
     * Set the card dismissible.
     *
     * @param dismissible
     *         {@code true} to be able to remove the card or {@code false} otherwise.
     */
    public void setDismissible(final boolean dismissible) {
        mDismissible = dismissible;
    }

    /**
     * Is the card dismissible.
     *
     * @return {@code true} if the card is removeable or {@code false} otherwise.
     */
    public boolean isDismissible() {
        return mDismissible;
    }

    /**
     * Get the context.
     *
     * @return the context.
     */
    @NonNull
    public Context getContext() {
        return mContext;
    }

    /**
     * The Card Builder configures the card.
     */
    public static class Builder {
        @NonNull
        private final Context mContext;
        private CardRenderer mCardRenderer;
        @Nullable
        private Object mTagObject;
        private boolean mDismissible;

        /**
         * Creates a new Builder.
         *
         * @param context
         *         to access resources.
         */
        public Builder(@NonNull final Context context) {
            mContext = context;
        }

        /**
         * Set a tag.
         *
         * @param object
         *         as tag.
         */
        @NonNull
        public Builder setTag(@Nullable final Object object) {
            mTagObject = object;
            return this;
        }

        /**
         * Set the card dismissible - it is then removable.
         */
        @NonNull
        public Builder setDismissible() {
            mDismissible = true;
            return this;
        }

        /**
         * Builds the card and specifies the renderer.
         *
         * @param cardRenderer
         *         to render the card with.
         * @return the card.
         */
        @NonNull
        public Card build(@NonNull final CardRenderer cardRenderer) {
            mCardRenderer = cardRenderer;
            return new Card(this);
        }
    }
}
