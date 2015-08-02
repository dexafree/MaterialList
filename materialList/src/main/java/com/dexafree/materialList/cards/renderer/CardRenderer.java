package com.dexafree.materialList.cards.renderer;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.View;

import com.dexafree.materialList.R;
import com.dexafree.materialList.cards.Card;

import java.util.Observable;

/**
 * The CardRenderer will only set the background color of the Card.
 */
public abstract class CardRenderer<T extends CardRenderer> extends Observable {
    @NonNull
    protected final Context mContext;
    @ColorInt
    private int mBackgroundColor = Color.WHITE;

    /**
     * Creates a basic CardRenderer.
     *
     * @param context
     *         to access the resources.
     */
    public CardRenderer(@NonNull final Context context) {
        mContext = context;
    }

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
        notifyDataSetChanged(this);
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
        return setBackgroundColor(mContext.getResources().getColor(color));
    }

    /**
     * Notifies the MaterialListAdapter that the content changed.
     */
    public void notifyDataSetChanged() {
        notifyDataSetChanged(null);
    }

    /**
     * Notifies the Card that the content changed.
     */
    public void notifyDataSetChanged(final Object object) {
        setChanged();
        notifyObservers(object);
    }

    /**
     * Renders the content and style of the card to the view.
     *
     * @param view to display the content and style on.
     * @param card to render.
     */
    public void render(@NonNull final View view, @NonNull final Card card) {
        view.findViewById(R.id.cardView).setBackgroundColor(getBackgroundColor());
    }

    /**
     * Get the card layout as resource.
     *
     * @return the card layout.
     */
    @LayoutRes
    public abstract int getLayout();
}
