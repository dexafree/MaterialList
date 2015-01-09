package com.dexafree.materialList.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dexafree.materialList.R;
import com.dexafree.materialList.model.BasicImageButtonsCard;

public class BasicImageButtonsCardItemView extends ButtonsCardItemView<BasicImageButtonsCard> {
    public BasicImageButtonsCardItemView(Context context) {
        super(context);
    }

    public BasicImageButtonsCardItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public BasicImageButtonsCardItemView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }
}
