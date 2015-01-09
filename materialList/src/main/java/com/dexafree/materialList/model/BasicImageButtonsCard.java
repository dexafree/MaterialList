package com.dexafree.materialList.model;

import android.content.Context;

import com.dexafree.materialList.R;
import com.dexafree.materialList.controller.OnButtonPressListener;

public class BasicImageButtonsCard extends ButtonsCard {
	public BasicImageButtonsCard(final Context context) {
		super(context);
	}

    @Override
    public int getLayout() {
        return R.layout.material_basic_image_buttons_card_layout;
    }

}
