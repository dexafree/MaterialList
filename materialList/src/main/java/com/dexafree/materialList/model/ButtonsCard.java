package com.dexafree.materialList.model;

import android.content.Context;

import com.dexafree.materialList.controller.OnButtonPressListener;

public abstract class ButtonsCard extends Card {
    private String leftButtonText;
    private String rightButtonText;
    private int mRightButtonTextColor = -1;
    private OnButtonPressListener onLeftButtonPressedListener;
    private OnButtonPressListener onRightButtonPressedListener;
    private boolean dividerVisible = false;
    private boolean fullWidthDivider = false;

    public ButtonsCard(final Context context) {
        super(context);
    }

    public String getLeftButtonText() {
        return leftButtonText;
    }

    public void setLeftButtonText(int leftButtonTextId) {
        setLeftButtonText(getString(leftButtonTextId));
    }

    public void setLeftButtonText(String leftButtonText) {
        this.leftButtonText = leftButtonText;
    }

    public String getRightButtonText() {
        return rightButtonText;
    }

    public void setRightButtonText(int rightButtonTextId) {
        setRightButtonText(getString(rightButtonTextId));
    }

    public void setRightButtonText(String rightButtonText) {
        this.rightButtonText = rightButtonText;
    }

    public int getRightButtonTextColor() {
        return mRightButtonTextColor;
    }

    public void setRightButtonTextColor(int color) {
        this.mRightButtonTextColor = color;
    }

    public void setRightButtonTextColorRes(int colorId) {
        setRightButtonTextColor(getResources().getColor(colorId));
    }

    public boolean isDividerVisible() {
        return dividerVisible;
    }

    public boolean isFullWidthDivider() {
        return fullWidthDivider;
    }

    public void setFullWidthDivider(boolean fullWidthDivider) {
        this.fullWidthDivider = fullWidthDivider;
    }

    public void setDividerVisible(boolean visible) {
        this.dividerVisible = visible;
    }

    public OnButtonPressListener getOnLeftButtonPressedListener() {
        return onLeftButtonPressedListener;
    }

    public void setOnLeftButtonPressedListener(OnButtonPressListener onLeftButtonPressedListener) {
        this.onLeftButtonPressedListener = onLeftButtonPressedListener;
    }

    public OnButtonPressListener getOnRightButtonPressedListener() {
        return onRightButtonPressedListener;
    }

    public void setOnRightButtonPressedListener(OnButtonPressListener onRightButtonPressedListener) {
        this.onRightButtonPressedListener = onRightButtonPressedListener;
    }
}
