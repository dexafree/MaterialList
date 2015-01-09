package com.dexafree.materialList.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.TextView;

import com.dexafree.materialList.R;
import com.dexafree.materialList.model.SmallImageCard;

public class SmallImageCardItemView extends CardItemView<SmallImageCard> {
    public SmallImageCardItemView(Context context) {
        super(context);
    }

    public SmallImageCardItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SmallImageCardItemView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }
}
