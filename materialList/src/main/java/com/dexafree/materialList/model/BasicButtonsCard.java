package com.dexafree.materialList.model;


import android.content.Context;

import com.dexafree.materialList.R;
import com.dexafree.materialList.controller.OnButtonPressListener;

public class BasicButtonsCard extends ButtonsCard {
	public BasicButtonsCard(final Context context) {
		super(context);
	}

    @Override
    public int getLayout() {
        return R.layout.material_basic_buttons_card;
    }
}
