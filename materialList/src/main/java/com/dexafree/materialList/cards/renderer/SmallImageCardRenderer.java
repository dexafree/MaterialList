package com.dexafree.materialList.cards.renderer;

import android.content.Context;
import android.support.annotation.NonNull;

import com.dexafree.materialList.R;

/**
 * Created by Fabio on 29.07.2015.
 */
public class SmallImageCardRenderer extends TextCardRenderer<SmallImageCardRenderer> {
    /**
     * @param context
     */
    public SmallImageCardRenderer(@NonNull final Context context) {
        super(context);
    }

    @Override
    public int getLayout() {
        return R.layout.material_small_image_card;
    }
}
