package com.dexafree.materialList.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.TextView;

import com.dexafree.materialList.R;
import com.dexafree.materialList.model.BigImageCard;

public class BigImageCardItemView extends CardItemView<BigImageCard> {
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
