package com.dexafree.materialList.card;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import java.util.Observable;
import java.util.Observer;

/**
 * The CardLayout is a container for a CardView and it's content.
 */
public class CardLayout extends LinearLayout implements Observer {
    private Card mCard;
    private boolean mObserves;

    /**
     * Creates a new CardLayout.
     *
     * @param context
     *         for the Layout.
     */
    public CardLayout(Context context) {
        super(context);
    }

    /**
     * Creates a new CardLayout.
     *
     * @param context
     *         for the Layout.
     * @param attrs
     *         for the Layout.
     */
    public CardLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * Creates a new CardLayout.
     *
     * @param context
     *         for the Layout.
     * @param attrs
     *         for the Layout.
     * @param defStyle
     *         for the Layout.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public CardLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    /**
     * Renders the Card content and style to the layout.
     *
     * @param card
     *         to render.
     */
    public void build(@NonNull final Card card) {
        mCard = card;

        if (!mObserves) {
            mCard.getProvider().addObserver(this);
            mObserves = true;
        }

        mCard.getProvider().render(this, card);
    }

    /**
     * Get the card of this layout.
     *
     * @return the card.
     */
    public Card getCard() {
        return mCard;
    }

    @Override
    public void update(final Observable observable, final Object data) {
        if(data == null) {
            build(mCard);
            ((CardProvider) observable).notifyDataSetChanged(getCard());
        }
    }
}
