package com.dexafree.materialList.cards.internal;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.v7.widget.CardView;
import android.support.v7.widget.MyRoundRectDrawableWithShadow;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import com.dexafree.materialList.cards.BasicCard;
import com.dexafree.materialList.model.CardItemView;
import com.dexafree.materialList.R;

public abstract class BaseCardItemView<T extends BasicCard> extends CardItemView<T> {

    public BaseCardItemView(Context context) {
        super(context);
    }

    public BaseCardItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public BaseCardItemView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public void build(T card) {
        super.build(card);
        // Background Color
        CardView cardView = (CardView) findViewById(R.id.cardView);

        if (cardView != null) {
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
                MyRoundRectDrawableWithShadow backgroundDrawable = new MyRoundRectDrawableWithShadow(
                        getContext().getResources(),
                        card.getBackgroundColor(),
                        cardView.getRadius(),
                        6f,
                        6f
                );
                cardView.setBackgroundDrawable(backgroundDrawable);
            } else {
                cardView.setBackgroundColor(card.getBackgroundColor());
                cardView.setCardElevation(dpToPx(6));
            }
        }
    }

    public float dpToPx(int dp) {
        DisplayMetrics displayMetrics = getContext().getResources().getDisplayMetrics();
        return Math.round(dp * (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));
    }

    //public float pxToDp(int px) {
    //    DisplayMetrics displayMetrics = getContext().getResources().getDisplayMetrics();
    //    return Math.round(px / (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));
    //}

    public float spToPx(int sp) {
        DisplayMetrics displayMetrics = getContext().getResources().getDisplayMetrics();
        return Math.round(sp * (displayMetrics.scaledDensity / DisplayMetrics.DENSITY_DEFAULT));
    }
}
