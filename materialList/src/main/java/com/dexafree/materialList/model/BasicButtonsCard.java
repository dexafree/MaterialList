package com.dexafree.materialList.model;


import android.content.Context;

import com.dexafree.materialList.R;
import com.dexafree.materialList.controller.OnButtonPressListener;

public class BasicButtonsCard extends Card {
    private String leftButtonText;
    private String rightButtonText;
    private OnButtonPressListener onLeftButtonPressedListener;
    private OnButtonPressListener onRightButtonPressedListener;
    private boolean dividerVisible = false;
    private boolean fullDividerLength = false;

	public BasicButtonsCard(final Context context) {
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

    public boolean isDividerVisible() {
        return dividerVisible;
    }

    public boolean getFullDividerLength() {
        return fullDividerLength;
    }

    public void setFullDividerLength(boolean fullDividerLength) {
        this.fullDividerLength = fullDividerLength;
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

    @Override
    public int getLayout() {
        return R.layout.material_basic_buttons_card;
    }

}
