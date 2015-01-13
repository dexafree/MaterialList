package com.dexafree.materialList.cards.view;

import android.content.Context;
import android.util.AttributeSet;

import com.dexafree.materialList.cards.model.BigImageCard;

public abstract class BigImageCardItemView<T extends BigImageCard> extends BaseTextCardItemView<T> {
    public BigImageCardItemView(Context context) {
        super(context);
    }

    public BigImageCardItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public BigImageCardItemView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }
}
