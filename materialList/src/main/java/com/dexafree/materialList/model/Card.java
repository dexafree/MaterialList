package com.dexafree.materialList.model;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

import com.dexafree.materialList.events.BusProvider;
import com.dexafree.materialList.events.DataSetChangedEvent;
import com.dexafree.materialList.events.DismissEvent;

public abstract class Card {
	private final Context mContext;
	private String mTitle;
    private String mDescription;
    private Drawable mDrawable;
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
		BusProvider.getInstance().post(new DataSetChangedEvent());
    }

    public String getDescription() {
        return mDescription;
    }

	public void setDescription(int descriptionId) {
		setDescription(mContext.getString(descriptionId));
	}

    public void setDescription(String description) {
        this.mDescription = description;
		BusProvider.getInstance().post(new DataSetChangedEvent());
    }

    public Drawable getDrawable() {
        return mDrawable;
    }

	public void setDrawable(int drawableId) {
        setDrawable(getResources().getDrawable(drawableId));
	}

    public void setDrawable(Drawable drawable) {
        this.mDrawable = drawable;
		BusProvider.getInstance().post(new DataSetChangedEvent());
    }

    public boolean isDismissible() {
        return mDismissible;
    }

    public void setDismissible(boolean canDismiss) {
        this.mDismissible = canDismiss;
    }

    public void dismiss() {
		if(mDismissible) {
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
