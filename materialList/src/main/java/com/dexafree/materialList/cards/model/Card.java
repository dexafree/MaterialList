package com.dexafree.materialList.cards.model;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;

import com.dexafree.materialList.events.BusProvider;

/**
 * The Card is the base class of all Card Models.
 */
public abstract class Card {
	private final Context mContext;
    private int mBackgroundColor = Color.WHITE;
    private boolean mDismissible = true;
    private boolean mDismissed = false;

    public Card(Context context) {
		mContext = context;
	}

    public void setBackgroundColor(int color) {
        mBackgroundColor = color;
		BusProvider.dataSetChanged();
    }

    public void setBackgroundColorRes(int colorId) {
        setBackgroundColor(getResources().getColor(colorId));
    }

    public int getBackgroundColor() {
        return mBackgroundColor;
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
			BusProvider.dismiss(this);
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
