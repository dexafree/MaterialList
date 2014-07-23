package com.dexafree.materiallistviewexample.model;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.widget.TextView;

import com.dexafree.materiallistviewexample.R;

public class ImageButtonsCard extends Card {

    public interface OnButtonPressListener{
        void onLeftTextPressed(TextView textView);
        void onRightTextPressed(TextView textView);
    }

    private String leftButtonText;
    private String rightButtonText;
    private OnButtonPressListener mListener;

    public ImageButtonsCard(){
        super();
        this.setLayout(R.layout.image_card_layout_with_buttons);
    }

    public ImageButtonsCard(String title, String description, Bitmap bitmap) {
        super(title, description, bitmap);
        this.setLayout(R.layout.image_card_layout_with_buttons);
    }

    public ImageButtonsCard(String title, String description, int resourceId) {
        super(title, description, resourceId);
        this.setLayout(R.layout.image_card_layout_with_buttons);
    }

    public ImageButtonsCard(String title, String description, Drawable drawable) {
        super(title, description, drawable);
        this.setLayout(R.layout.image_card_layout_with_buttons);
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
