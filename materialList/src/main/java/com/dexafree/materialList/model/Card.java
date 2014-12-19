package com.dexafree.materialList.model;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

import java.util.Observable;
import java.util.Observer;

public abstract class Card extends Observable {
    private String title;
    private String description;
    private Bitmap bitmap;
    private boolean canDismiss = true;
    private boolean dismissed = false;

    public Card(){}

    public Card(String title, String description, Bitmap bitmap) {
        this.title = title;
        this.description = description;
        this.bitmap = bitmap;
    }

    public Card(String title, String description, Context context, int resourceId) {
        this.title = title;
        this.description = description;
        this.bitmap = resourceToBitmap(context, resourceId);
    }

    public Card(String title, String description, Drawable drawable) {
        this.title = title;
        this.description = description;
        this.bitmap = drawableToBitmap(drawable);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
        notifyAdapter();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
        notifyAdapter();
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
        notifyAdapter();
    }

    public void setBitmap(Context context, int resourceId){
        bitmap = resourceToBitmap(context, resourceId);
        notifyAdapter();
    }

    public void setBitmap(Drawable drawable){
        bitmap = drawableToBitmap(drawable);
        notifyAdapter();
    }

    private Bitmap resourceToBitmap (Context context, int resourceId){
        Resources res = context.getResources();
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

    public boolean canDismiss() {
        return canDismiss;
    }

    public void setCanDismiss(boolean canDismiss) {
        this.canDismiss = canDismiss;
    }

    public void dismiss() {
        dismissed = true;
        notifyAdapter();
    }

    public boolean isDismissed() {
        return dismissed;
    }

    public abstract int getLayout();

    private void notifyAdapter() {
        setChanged();
        notifyObservers(this);
    }
}
