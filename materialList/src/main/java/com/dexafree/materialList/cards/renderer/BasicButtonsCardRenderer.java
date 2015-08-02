package com.dexafree.materialList.cards.renderer;

import android.content.Context;
import android.support.annotation.NonNull;

import com.dexafree.materialList.R;

public class BasicButtonsCardRenderer extends ButtonCardRenderer<BasicButtonsCardRenderer> {
    public BasicButtonsCardRenderer(@NonNull final Context context) {
        super(context);
    }

    @Override
    public int getLayout() {
        return R.layout.material_basic_buttons_card;
    }
}
