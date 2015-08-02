package com.dexafree.materialList.cards.renderer;

import android.content.Context;
import android.support.annotation.NonNull;

import com.dexafree.materialList.R;

public class BigImageCardRenderer extends TextCardRenderer<BigImageCardRenderer> {
    public BigImageCardRenderer(@NonNull final Context context) {
        super(context);
    }

    @Override
    public int getLayout() {
        return R.layout.material_big_image_card_layout;
    }
}
