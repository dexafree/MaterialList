package com.dexafree.materialList.card.provider;

import android.graphics.Color;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.view.View;

import com.dexafree.materialList.R;
import com.dexafree.materialList.card.Card;
import com.dexafree.materialList.card.CardConfig;
import com.dexafree.materialList.card.CardRenderer;

/**
 * The CardProvider will only set the background color of the Card.
 */
public abstract class CardProvider<T extends CardProvider> extends CardConfig implements CardRenderer {
    @ColorInt
    private int mBackgroundColor = Color.WHITE;

    /**
     * Get the background color.
     *
     * @return the background color.
     */
    @ColorInt
    public int getBackgroundColor() {
        return mBackgroundColor;
    }

    /**
     * Set the background color with an real color (e.g. {@code Color.WHITE}).
     *
     * @param color
     *         as real.
     * @return the renderer.
     */
    @NonNull
    @SuppressWarnings("unchecked")
    public T setBackgroundColor(@ColorInt final int color) {
        mBackgroundColor = color;
        notifyDataSetChanged();
        return (T) this;
    }

    /**
     * Set the background color with an resource color (e.g. {@code android.R.color.white}).
     *
     * @param color
     *         as resource.
     * @return the renderer.
     */
    @NonNull
    public T setBackgroundResourceColor(@ColorRes final int color) {
        return setBackgroundColor(getContext().getResources().getColor(color));
    }

    /**
     * Renders the content and style of the card to the view.
     *
     * @param view
     *         to display the content and style on.
     * @param card
     *         to render.
     */

    public void render(@NonNull final View view, @NonNull final Card card) {
        ((CardView)view.findViewById(R.id.cardView)).setCardBackgroundColor(getBackgroundColor());
    }
}
