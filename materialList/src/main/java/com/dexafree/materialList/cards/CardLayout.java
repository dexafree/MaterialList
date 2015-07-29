package com.dexafree.materialList.cards;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.widget.LinearLayout;

/**
 * Created by Fabio on 29.07.2015.
 */
public class CardLayout extends LinearLayout {
    private Card mCard;

    public CardLayout(Context context) {
        super(context);
    }

    public CardLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public CardLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    /**
     * @param card
     */
    public void build(@NonNull final Card card) {
        mCard = card;
        card.getRenderer().render(this, card);
    }

    /**
     *
     * @return
     */
    public Card getCard() {
        return mCard;
    }
}
