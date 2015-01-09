package com.dexafree.materialList.model;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

import com.dexafree.materialList.R;
import com.dexafree.materialList.events.BusProvider;
import com.dexafree.materialList.events.DismissEvent;

public abstract class Card {
	private final Context mContext;
	private String mTitle;
    private int mTitleColor = -1;
    private String mDescription;
    private int mDescriptionColor = -1;
    private Drawable mDrawable;
    private int mBackgroundColor = Color.WHITE;
    private boolean mDismissible = true;
    private boolean mDismissed = false;

    public Card(Context context) {
		mContext = context;
	}

    public String getTitle() {
        return mTitle;
    }

	public void setTitle(int titleId) {
		setTitle(mContext.getString(titleId));
	}

    public void setTitle(String title) {
        this.mTitle = title;
    }

    public String getDescription() {
        return mDescription;
    }

	public void setDescription(int descriptionId) {
		setDescription(mContext.getString(descriptionId));
	}

    public void setDescription(String description) {
        this.mDescription = description;
    }

    public Drawable getDrawable() {
        return mDrawable;
    }

	public void setDrawable(int drawableId) {
        setDrawable(getResources().getDrawable(drawableId));
	}

    public void setDrawable(Drawable drawable) {
        this.mDrawable = drawable;
    }

    public void setBackgroundColor(int color) {
        mBackgroundColor = color;
    }

    public void setBackgroundColorRes(int colorId) {
        setBackgroundColor(getResources().getColor(colorId));
    }

    public int getBackgroundColor() {
        return mBackgroundColor;
    }

    public int getTitleColor() {
        return mTitleColor;
    }

    public void setTitleColor(int color) {
        this.mTitleColor = color;
    }

    public void setTitleColorRes(int colorId) {
        setTitleColor(getResources().getColor(colorId));
    }

    public int getDescriptionColor() {
        return mDescriptionColor;
    }

    public void setDescriptionColor(int color) {
        this.mDescriptionColor = color;
    }

    public void setDescriptionColorRes(int colorId) {
        setDescriptionColor(getResources().getColor(colorId));
    }

    public boolean isDismissible() {
        return mDismissible;
    }

    public void setDismissible(boolean canDismiss) {
        this.mDismissible = canDismiss;
    }

    public void dismiss() {
		if(isDismissible() && !isDismissed()) {
			mDismissed = true;
			BusProvider.getInstance().post(new DismissEvent(this));
		}
    }

    public boolean isDismissed() {
        return mDismissed;
    }

    public abstract int getLayout();

	protected String getString(int id) {
		return mContext.getString(id);
	}

	protected Resources getResources() {
		return mContext.getResources();
	}
}
