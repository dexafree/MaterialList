package com.dexafree.materialList.cards.renderer;

import android.content.Context;
import android.support.annotation.NonNull;

import com.dexafree.materialList.R;

public class BigImageButtonsCardRenderer extends ButtonCardRenderer<BigImageButtonsCardRenderer> {
    public BigImageButtonsCardRenderer(@NonNull final Context context) {
        super(context);
    }

    @Override
    public int getLayout() {
        return R.layout.material_image_with_buttons_card;
    }
}
