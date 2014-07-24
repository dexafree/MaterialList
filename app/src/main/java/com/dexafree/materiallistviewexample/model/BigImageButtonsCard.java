package com.dexafree.materiallistviewexample.model;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.widget.TextView;

import com.dexafree.materiallistviewexample.R;
import com.dexafree.materiallistviewexample.controller.OnButtonPressListener;

public class BigImageButtonsCard extends Card {

    private String leftButtonText;
    private String rightButtonText;
    private OnButtonPressListener mListener;

    public BigImageButtonsCard(){
        super();
        this.setLayout(R.layout.material_image_with_buttons_card);
    }

    public BigImageButtonsCard(String title, String description, Bitmap bitmap) {
        super(title, description, bitmap);
        this.setLayout(R.layout.material_image_with_buttons_card);
    }

    public BigImageButtonsCard(String title, String description, int resourceId) {
        super(title, description, resourceId);
        this.setLayout(R.layout.material_image_with_buttons_card);
    }

    public BigImageButtonsCard(String title, String description, Drawable drawable) {
        super(title, description, drawable);
        this.setLayout(R.layout.material_image_with_buttons_card);
    }

    public String getLeftButtonText() {
        return leftButtonText;
    }

    public void setLeftButtonText(String leftButtonText) {
        this.leftButtonText = leftButtonText;
    }

    public String getRightButtonText() {
        return rightButtonText;
    }

    public void setRightButtonText(String rightButtonText) {
        this.rightButtonText = rightButtonText;
    }

    public OnButtonPressListener getOnButtonPressedListener() {
        return mListener;
    }

    public void setOnButtonPressListener(OnButtonPressListener listener) {
        this.mListener = listener;
    }
}
