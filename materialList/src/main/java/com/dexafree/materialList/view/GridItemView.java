package com.dexafree.materialList.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.widget.LinearLayout;

import com.dexafree.materialList.model.Card;


public abstract class GridItemView<T extends Card> extends LinearLayout {
	private T mCard;

    public GridItemView(Context context) {
        super(context);
    }

    public GridItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public GridItemView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

	public void build(T card) {
		mCard = card;
		configureView(card);
	}

	protected abstract void configureView(T card);

	protected T getCard() {
		return mCard;
	}

    protected int dpToPx(int dp) {
        DisplayMetrics displayMetrics = getContext().getResources().getDisplayMetrics();
        int px = Math.round(dp * (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));
        return px;
    }

    protected int pxToDp(int px) {
        DisplayMetrics displayMetrics = getContext().getResources().getDisplayMetrics();
        int dp = Math.round(px / (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));
        return dp;
    }
}