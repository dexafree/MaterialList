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

    public String getSubtitle() {
        return subtitle;
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

    public void setButtonText(String buttonText) {
        this.buttonText = buttonText;
    }

    public void setBackgroundColor(int color){
        this.backgroundColor = color;
    }

    public void setBackgroundColor(String color){
        backgroundColor = Color.parseColor(color);
    }

    public void setBackgroundColorFromId(Context context, int colorId){
        backgroundColor = context.getResources().getColor(colorId);
    }

    public void setTitleColor(int color){
        this.titleColor = color;
    }

    public void setTitleColor(String color){
        titleColor = Color.parseColor(color);
    }

    public void setTitleColorFromId(Context context, int colorId){
        titleColor = context.getResources().getColor(colorId);
    }

    public void setSubtitleColor(int color){
        this.subtitleColor = color;
    }

    public void setSubtitleColor(String color){
        subtitleColor = Color.parseColor(color);
    }

    public void setSubtitleColorFromId(Context context, int colorId){
        subtitleColor = context.getResources().getColor(colorId);
    }

    public void setDescriptionColor(int color){
        this.descriptionColor = color;
    }

    public void setDescriptionColor(String color){
        descriptionColor = Color.parseColor(color);
    }

    public void setDescriptionColorFromId(Context context, int colorId){
        descriptionColor = context.getResources().getColor(colorId);
    }

    public void setDividerColor(int color){
        this.dividerColor = color;
    }

    public void setDividerColor(String color){
        dividerColor = Color.parseColor(color);
    }

    public void setDividerColorFromId(Context context, int colorId){
        dividerColor = context.getResources().getColor(colorId);
    }

    public void setButtonTextColor(int color){
        this.buttonTextColor = color;
    }

    public void setButtonTextColor(String color){
        buttonTextColor = Color.parseColor(color);
    }

    public void setButtonTextColorFromId(Context context, int colorId){
        buttonTextColor = context.getResources().getColor(colorId);
    }

    @Override
    public int getLayout() {
        return R.layout.material_welcome_card_layout;
    }

}
