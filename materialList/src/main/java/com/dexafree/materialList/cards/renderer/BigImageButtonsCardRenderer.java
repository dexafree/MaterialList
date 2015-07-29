package com.dexafree.materialList.cards.renderer;

import android.content.Context;
import android.support.annotation.NonNull;

import com.dexafree.materialList.R;

/**
 * Created by Fabio on 29.07.2015.
 */
public class BigImageButtonsCardRenderer extends ButtonCardRenderer<BigImageButtonsCardRenderer> {
    /**
     * @param context
     */
    public BigImageButtonsCardRenderer(@NonNull final Context context) {
        super(context);
    }

    @Override
    public int getLayout() {
        return R.layout.material_image_with_buttons_card;
    }
}
