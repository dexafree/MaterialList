package com.dexafree.materialList.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.TextView;

import com.dexafree.materialList.R;
import com.dexafree.materialList.model.SmallImageCard;
import com.dexafree.materialList.model.GridItemView;

public class SmallImageCardItemView extends GridItemView<SmallImageCard> {

    TextView mTitle;

    ImageView mImage;

    TextView mDescription;

    public SmallImageCardItemView(Context context) {
        super(context);
    }

    public SmallImageCardItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SmallImageCardItemView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public void configureView(SmallImageCard card) {
        setTitle(card.getTitle());
        setDescription(card.getDescription());
        setImage(card.getBitmap());
    }

    public void setTitle(String title){
        mTitle = (TextView)findViewById(R.id.titleTextView);
        mTitle.setText(title);
    }

    public void setDescription(String description){
        mDescription = (TextView)findViewById(R.id.descriptionTextView);
        mDescription.setText(description);
    }

    public void setImage(Bitmap bm){
        mImage = (ImageView)findViewById(R.id.imageView);
        mImage.setImageBitmap(bm);
    }
}
