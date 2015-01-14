package android.support.v7.widget;

import android.content.res.Resources;

/*
 * This extension is necessary in order to change the CardView background and keep the shadow in <21
 * For more information: http://stackoverflow.com/questions/26561122/change-the-background-color-of-cardview-programmatically
 */
public class MyRoundRectDrawableWithShadow extends RoundRectDrawableWithShadow {

    public MyRoundRectDrawableWithShadow(Resources resources, int backgroundColor, float radius,
                                         float shadowSize, float maxShadowSize) {
        super(resources, backgroundColor, radius, shadowSize, maxShadowSize);
    }

}