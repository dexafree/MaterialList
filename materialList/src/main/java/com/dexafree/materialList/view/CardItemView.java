package com.dexafree.materialList.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dexafree.materialList.R;
import com.dexafree.materialList.model.Card;


public abstract class CardItemView<T extends Card> extends LinearLayout {
	private T mCard;

    public CardItemView(Context context) {
        super(context);
    }

    public CardItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public CardItemView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

	public void build(T card) {
		mCard = card;

        // Title
        ((TextView) findViewById(R.id.titleTextView)).setText(card.getTitle());

        // Description
        ((TextView) findViewById(R.id.descriptionTextView)).setText(card.getDescription());

        // Image
        ((ImageView) findViewById(R.id.imageView)).setImageDrawable(card.getDrawable());
	}

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