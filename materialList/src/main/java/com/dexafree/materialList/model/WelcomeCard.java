package com.dexafree.materialList.model;

import android.content.Context;
import android.graphics.Color;

import com.dexafree.materialList.R;
import com.dexafree.materialList.controller.OnButtonPressListener;

public class WelcomeCard extends Card {
    private String subtitle;
    private String buttonText;
    private OnButtonPressListener mListener;
    private int backgroundColor = Color.parseColor("#2F64F6");
    private int titleColor = Color.WHITE;
    private int subtitleColor = Color.WHITE;
    private int descriptionColor = Color.WHITE;
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

    public int getBackgroundColor(){
        return backgroundColor;
    }

    public int getTitleColor() {
        return titleColor;
    }

    public int getSubtitleColor() {
        return subtitleColor;
    }

    public int getDescriptionColor() {
        return descriptionColor;
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

	public void setBackgroundColorRes(int colorId) {
		setBackgroundColor(getResources().getColor(colorId));
	}

	public void setBackgroundColor(String color){
		setBackgroundColor(Color.parseColor(color));
	}

    public void setBackgroundColor(int color){
        this.backgroundColor = color;
    }

	public void setTitleColorRes(int colorId) {
		setTitleColor(getResources().getColor(colorId));
	}

	public void setTitleColor(String color){
		setTitleColor(Color.parseColor(color));
	}

    public void setTitleColor(int color){
        this.titleColor = color;
    }

	public void setSubtitleColorRes(int colorId) {
		setSubtitleColor(getResources().getColor(colorId));
	}

    public void setSubtitleColor(int color){
        this.subtitleColor = color;
    }

    public void setSubtitleColor(String color){
        setSubtitleColor(Color.parseColor(color));
    }

	public void setDescriptionColorRes(int colorId) {
		setDescriptionColor(getResources().getColor(colorId));
	}

    public void setDescriptionColor(int color){
        this.descriptionColor = color;
    }

    public void setDescriptionColor(String color){
        setDescriptionColor(Color.parseColor(color));
    }

	public void setDividerColorRes(int colorId) {
		setDividerColor(getResources().getColor(colorId));
	}

    public void setDividerColor(int color){
        this.dividerColor = color;
    }

    public void setDividerColor(String color){
        setDividerColor(Color.parseColor(color));
    }

	public void setButtonTextColorRes(int colorId) {
		setButtonTextColor(getResources().getColor(colorId));
	}

    public void setButtonTextColor(int color){
        this.buttonTextColor = color;
    }

    public void setButtonTextColor(String color){
        setButtonTextColor(Color.parseColor(color));
    }

    @Override
    public int getLayout() {
        return R.layout.material_welcome_card_layout;
    }

}
