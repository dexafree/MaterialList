package com.dexafree.materialList.cards.renderer;

import android.content.Context;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.View;

import com.dexafree.materialList.cards.Card;

/**
 * Created by Fabio on 29.07.2015.
 */
public abstract class CardRenderer<T extends CardRenderer> {
    @NonNull
    protected final Context mContext;
    @ColorInt
    private int mBackgroundColor;

    /**
     *
     * @param context
     */
    public CardRenderer(@NonNull final Context context) {
        mContext = context;
    }

    /**
     *
     * @return
     */
    public @ColorInt int getBackgroundColor() {
        return mBackgroundColor;
    }

    /**
     *
     * @param color
     * @return
     */
    public T setBackgroundColor(@ColorInt final int color) {
        mBackgroundColor = color;
        return (T) this;
    }

    /**
     *
     * @param color
     * @return
     */
    public T setBackgroundResourceColor(@ColorRes final int color) {
        mBackgroundColor = mContext.getResources().getColor(color);
        return (T) this;
    }

    /**
     *
     * @param view
     * @param card
     */
    public abstract void render(@NonNull final View view, @NonNull final Card card);

    /**
     * @return
     */
    @LayoutRes
    public abstract int getLayout();
}
