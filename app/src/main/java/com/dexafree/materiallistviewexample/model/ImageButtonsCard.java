package com.dexafree.materiallistviewexample.model;

import android.widget.TextView;

public class ImageButtonsCard extends Card {

    public interface OnButtonPressListener{

        void onLeftTextPressed(TextView textView);
        void onRightTextPressed(TextView textView);
    }

    private String leftButtonText;
    private String rightButtonText;
    private OnButtonPressListener mListener;

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
