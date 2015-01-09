package com.dexafree.materialList.model;

import android.content.Context;

import com.dexafree.materialList.R;
import com.dexafree.materialList.controller.OnButtonPressListener;

public class BigImageButtonsCard extends ButtonsCard {
	public BigImageButtonsCard(final Context context) {
		super(context);
	}

    @Override
    public int getLayout() {
        return R.layout.material_image_with_buttons_card;
    }
}
