package com.dexafree.materiallistviewexample.model;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;

import com.dexafree.materiallistviewexample.R;

public class BasicCard extends Card {

    public BasicCard(){
        super();
        this.setLayout(R.layout.basic_card_layout);
    }

    public BasicCard(String title, String description, Bitmap bitmap) {
        super(title, description, bitmap);
        this.setLayout(R.layout.basic_card_layout);
    }

    public BasicCard(String title, String description, int resourceId) {
        super(title, description, resourceId);
        this.setLayout(R.layout.basic_card_layout);
    }

    public BasicCard(String title, String description, Drawable drawable) {
        super(title, description, drawable);
        this.setLayout(R.layout.basic_card_layout);
    }


}
