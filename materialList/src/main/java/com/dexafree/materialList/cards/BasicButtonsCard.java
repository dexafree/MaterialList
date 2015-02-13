package com.dexafree.materialList.cards;


import android.content.Context;
import com.dexafree.materialList.R;

public class BasicButtonsCard extends ExtendedCard {
    public BasicButtonsCard(final Context context) {
        super(context);
    }

    @Override
    public int getLayout() {
        return R.layout.material_basic_buttons_card;
    }
}
