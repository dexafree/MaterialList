package com.dexafree.materialList.util;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.DisplayMetrics;

/**
 * Created by Fabio on 29.07.2015.
 */
public final class PixelUtils {
    private PixelUtils() {
    }

    /**
     *
     * @param context
     * @param dp
     * @return
     */
    public static float dpToPx(@NonNull final Context context, final int dp) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        return Math.round(dp * (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));
    }

    //public float pxToDp(int px) {
    //    DisplayMetrics displayMetrics = getContext().getResources().getDisplayMetrics();
    //    return Math.round(px / (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));
    //}

    /**
     *
     * @param context
     * @param sp
     * @return
     */
    public static float spToPx(@NonNull final Context context, final int sp) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        return Math.round(sp * (displayMetrics.scaledDensity / DisplayMetrics.DENSITY_DEFAULT));
    }
}
