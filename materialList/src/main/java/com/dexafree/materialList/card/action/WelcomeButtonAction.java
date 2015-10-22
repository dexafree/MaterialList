package com.dexafree.materialList.card.action;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.PorterDuff;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.TextView;

import com.dexafree.materialList.card.Card;

public class WelcomeButtonAction extends TextViewAction {
    public WelcomeButtonAction(@NonNull Context context) {
        super(context);
    }

    @Override
    protected void onRender(@NonNull View view, @NonNull Card card) {
        super.onRender(view, card);

        final TextView button = (TextView) view;
        Drawable drawable = button.getCompoundDrawables()[0];
        drawable.setColorFilter(getTextColor(), PorterDuff.Mode.SRC_IN);
        button.setCompoundDrawablesWithIntrinsicBounds(resize(drawable, 50, 50), null, null, null);
    }

    private Drawable resize(Drawable image, int width, int height) {
        Bitmap b = ((BitmapDrawable) image).getBitmap();
        Bitmap bitmapResize = Bitmap.createScaledBitmap(b, width, height, false);
        return new BitmapDrawable(getContext().getResources(), bitmapResize);
    }
}
