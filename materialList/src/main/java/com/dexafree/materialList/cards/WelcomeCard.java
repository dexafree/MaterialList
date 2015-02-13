package com.dexafree.materialList.cards;

import android.content.Context;
import android.graphics.Color;
import com.dexafree.materialList.R;

public class WelcomeCard extends SimpleCard {
    private String subtitle;
    private String buttonText;
    private OnButtonPressListener mListener;
    private int subtitleColor = Color.WHITE;
    private int dividerColor = Color.parseColor("#608DFA");
    private int buttonTextColor = Color.WHITE;

    public WelcomeCard(final Context context) {
        super(context);
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(int subtitleId) {
        setSubtitle(getString(subtitleId));
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public OnButtonPressListener getOnButtonPressedListener() {
        return mListener;
    }

    public void setOnButtonPressedListener(OnButtonPressListener mListener) {
        this.mListener = mListener;
    }

    public String getButtonText() {
        return buttonText;
    }

    public int getSubtitleColor() {
        return subtitleColor;
    }

    public int getDividerColor() {
        return dividerColor;
    }

    public int getButtonTextColor() {
        return buttonTextColor;
    }

    public void setButtonText(int buttonTextId) {
        setButtonText(getString(buttonTextId));
    }

    public void setButtonText(String buttonText) {
        this.buttonText = buttonText;
    }

    public void setSubtitleColorRes(int colorId) {
        setSubtitleColor(getResources().getColor(colorId));
    }

    public void setSubtitleColor(int color) {
        this.subtitleColor = color;
    }

    public void setSubtitleColor(String color) {
        setSubtitleColor(Color.parseColor(color));
    }

    public void setDividerColorRes(int colorId) {
        setDividerColor(getResources().getColor(colorId));
    }

    public void setDividerColor(int color) {
        this.dividerColor = color;
    }

    public void setDividerColor(String color) {
        setDividerColor(Color.parseColor(color));
    }

    public void setButtonTextColorRes(int colorId) {
        setButtonTextColor(getResources().getColor(colorId));
    }

    public void setButtonTextColor(int color) {
        this.buttonTextColor = color;
    }

    public void setButtonTextColor(String color) {
        setButtonTextColor(Color.parseColor(color));
    }

    @Override
    public int getLayout() {
        return R.layout.material_welcome_card_layout;
    }
}
