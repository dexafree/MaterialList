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
	private String title;
    private String description;
    private Bitmap bitmap;
    private boolean canDismiss = true;
    private boolean dismissed = false;

    public Card(Context context) {
		mContext = context;
	}

	// Do we really need these Constructors? The Developer can access every field through a Setter.
	/*
    public Card(Context context, String title, String description, Bitmap bitmap) {
		mContext = context;
        this.title = title;
        this.description = description;
        this.bitmap = bitmap;
    }

    public Card(Context context, String title, String description, int resourceId) {
		mContext = context;
        this.title = title;
        this.description = description;
        this.bitmap = resourceToBitmap(context, resourceId);
    }

    public Card(Context context, String title, String description, Drawable drawable) {
		mContext = context;
        this.title = title;
        this.description = description;
        this.bitmap = drawableToBitmap(drawable);
    }
    */

    public String getTitle() {
        return title;
    }

	public void setTitle(int titleId) {
		setTitle(mContext.getString(titleId));
	}

    public void setTitle(String title) {
        this.title = title;
		BusProvider.getInstance().post(new DataSetChangedEvent());
    }

    public String getDescription() {
        return description;
    }

	public void setDescription(int descriptionId) {
		setDescription(mContext.getString(descriptionId));
	}

    public void setDescription(String description) {
        this.description = description;
		BusProvider.getInstance().post(new DataSetChangedEvent());
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

	public void setDrawable(int drawableId) {
		setBitmap(resourceToBitmap(drawableId));
	}

    public void setBitmap(int drawableId) {
        setBitmap(resourceToBitmap(drawableId));
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
		BusProvider.getInstance().post(new DataSetChangedEvent());
    }

    public void setBitmap(Drawable drawable){
        bitmap = drawableToBitmap(drawable);
		BusProvider.getInstance().post(new DataSetChangedEvent());
    }

    private Bitmap resourceToBitmap (int resourceId){
        Resources res = mContext.getResources();
        Drawable d = res.getDrawable(resourceId);
        return drawableToBitmap(d);
    }

    private Bitmap drawableToBitmap (Drawable drawable) {
        if (drawable instanceof BitmapDrawable) {
            return ((BitmapDrawable)drawable).getBitmap();
        }

        Bitmap bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);

        return bitmap;
    }

    public boolean isDismissible() {
        return canDismiss;
    }

    public void setDismissible(boolean canDismiss) {
        this.canDismiss = canDismiss;
    }

    public void dismiss() {
		if(canDismiss) {
			dismissed = true;
			BusProvider.getInstance().post(new DismissEvent(this));
		}
    }

    public boolean isDismissed() {
        return dismissed;
    }

    public abstract int getLayout();

	protected String getString(int id) {
		return mContext.getString(id);
	}

	protected Resources getResources() {
		return mContext.getResources();
	}
}
