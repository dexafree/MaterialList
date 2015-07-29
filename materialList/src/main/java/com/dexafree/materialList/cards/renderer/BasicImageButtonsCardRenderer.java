package com.dexafree.materialList.cards.renderer;

import android.content.Context;
import android.support.annotation.NonNull;

import com.dexafree.materialList.R;

/**
 * Created by Fabio on 29.07.2015.
 */
public class BasicImageButtonsCardRenderer extends ButtonCardRenderer<BasicImageButtonsCardRenderer> {
    /**
     * @param context
     */
    public BasicImageButtonsCardRenderer(@NonNull final Context context) {
        super(context);
    }

    @Override
    public int getLayout() {
        return R.layout.material_basic_image_buttons_card_layout;
    }
}
