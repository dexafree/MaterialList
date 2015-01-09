package com.dexafree.materialList.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dexafree.materialList.R;
import com.dexafree.materialList.model.BigImageButtonsCard;

public class BigImageButtonsCardItemView extends ButtonsCardItemView<BigImageButtonsCard>{
    public BigImageButtonsCardItemView(Context context) {
        super(context);
    }

    public BigImageButtonsCardItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public BigImageButtonsCardItemView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }
}
