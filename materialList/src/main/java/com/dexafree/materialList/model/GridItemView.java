package com.dexafree.materialList.model;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;


public abstract class GridItemView<T extends Card> extends LinearLayout implements View.OnClickListener{

    public GridItemView(Context context) {
        super(context);
    }

    public GridItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public GridItemView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public abstract void configureView(T newItem);

    @Override
    public void onClick(View view) {

    }
}