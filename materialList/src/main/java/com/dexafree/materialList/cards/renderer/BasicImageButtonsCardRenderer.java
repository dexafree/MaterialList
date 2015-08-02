package com.dexafree.materialList.cards.renderer;

import android.content.Context;
import android.support.annotation.NonNull;

import com.dexafree.materialList.R;

public class BasicImageButtonsCardRenderer extends ButtonCardRenderer<BasicImageButtonsCardRenderer> {
    public BasicImageButtonsCardRenderer(@NonNull final Context context) {
        super(context);
    }

    @Override
    public int getLayout() {
        return R.layout.material_basic_image_buttons_card_layout;
    }
}
