package com.dexafree.materialList.cards;

import android.content.Context;
import com.dexafree.materialList.R;

public class BigImageCard extends SimpleCard {
    public BigImageCard(final Context context) {
        super(context);
    }

    @Override
    public int getLayout() {
        return R.layout.material_big_image_card_layout;
    }
}
