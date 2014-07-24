package com.dexafree.materiallistviewexample.model;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;

import com.dexafree.materiallistviewexample.R;
import com.dexafree.materiallistviewexample.controller.OnButtonPressListener;

public class BasicImageButtonsCard extends Card {

    private String leftButtonText;
    private String rightButtonText;
    private OnButtonPressListener mListener;

    public BasicImageButtonsCard(){
        super();
        this.setLayout(R.layout.material_basic_image_buttons_card_layout);
    }

    public BasicImageButtonsCard(String title, String description, Bitmap bitmap) {
        super(title, description, bitmap);
        this.setLayout(R.layout.material_basic_image_buttons_card_layout);
    }

    public BasicImageButtonsCard(String title, String description, int resourceId) {
        super(title, description, resourceId);
        this.setLayout(R.layout.material_basic_image_buttons_card_layout);
    }

    public BasicImageButtonsCard(String title, String description, Drawable drawable) {
        super(title, description, drawable);
        this.setLayout(R.layout.material_basic_image_buttons_card_layout);
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
