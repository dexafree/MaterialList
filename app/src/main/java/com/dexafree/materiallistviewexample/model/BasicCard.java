package com.dexafree.materiallistviewexample.model;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

public class BasicCard extends Card {

    private String title;
    private String description;
    private Bitmap bitmap;

    public BasicCard(){}

    public BasicCard(String title, String description, Bitmap bitmap) {
        this.title = title;
        this.description = description;
        this.bitmap = bitmap;
    }

    public BasicCard(String title, String description, int resourceId) {
        this.title = title;
        this.description = description;
        this.bitmap = resourceToBitmap(resourceId);
    }

    public BasicCard(String title, String description, Drawable drawable) {
        this.title = title;
        this.description = description;
        this.bitmap = drawableToBitmap(drawable);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public void setBitmap(int resourceId){
        bitmap = resourceToBitmap(resourceId);
    }

    public void setBitmap(Drawable drawable){
        bitmap = drawableToBitmap(drawable);
    }

    private Bitmap resourceToBitmap (int resourceId){
        return BitmapFactory.decodeResource(Resources.getSystem(), resourceId);
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

}
