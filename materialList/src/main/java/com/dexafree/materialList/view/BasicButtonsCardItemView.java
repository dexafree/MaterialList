package com.dexafree.materialList.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dexafree.materialList.R;
import com.dexafree.materialList.model.BasicButtonsCard;

public class BasicButtonsCardItemView extends ButtonsCardItemView<BasicButtonsCard> {
    public BasicButtonsCardItemView(Context context) {
        super(context);
    }

    public BasicButtonsCardItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public BasicButtonsCardItemView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }
}
