package com.dexafree.materialList.cards;

import android.content.Context;
import com.dexafree.materialList.R;

public class BasicImageButtonsCard extends ExtendedCard {
    public BasicImageButtonsCard(final Context context) {
        super(context);
    }

    @Override
    public int getLayout() {
        return R.layout.material_basic_image_buttons_card_layout;
    }

}
