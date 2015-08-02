package com.dexafree.materialList.cards.renderer;

import android.content.Context;
import android.support.annotation.NonNull;

import com.dexafree.materialList.R;

public class SmallImageCardRenderer extends TextCardRenderer<SmallImageCardRenderer> {
    public SmallImageCardRenderer(@NonNull final Context context) {
        super(context);
    }

    @Override
    public int getLayout() {
        return R.layout.material_small_image_card;
    }
}
