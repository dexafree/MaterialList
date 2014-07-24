package com.dexafree.materiallistviewexample.model;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;

import com.dexafree.materiallistviewexample.R;

public class BigImageCard extends Card {
    
    public BigImageCard(){
        super();
        this.setLayout(R.layout.material_big_image_card_layout);
    }

    public BigImageCard(String title, String description, Bitmap bitmap) {
        super(title, description, bitmap);
        this.setLayout(R.layout.material_big_image_card_layout);
    }

    public BigImageCard(String title, String description, int resourceId) {
        super(title, description, resourceId);
        this.setLayout(R.layout.material_big_image_card_layout);
    }

    public BigImageCard(String title, String description, Drawable drawable) {
        super(title, description, drawable);
        this.setLayout(R.layout.material_big_image_card_layout);
    }
}
