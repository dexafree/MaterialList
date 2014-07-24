package com.dexafree.materialList.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.TextView;

import com.dexafree.materialList.R;
import com.dexafree.materialList.model.BigImageCard;
import com.dexafree.materialList.model.GridItemView;

public class BigImageCardItemView extends GridItemView<BigImageCard> {

    TextView mTitle;

    ImageView mImage;

    TextView mDescription;

    public BigImageCardItemView(Context context) {
        super(context);
    }

    public BigImageCardItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public BigImageCardItemView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    /*@Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if(mImage != null) {
            RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) mImage.getLayoutParams();
            params.height = getMeasuredWidth();
            mImage.setLayoutParams(params);
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        }
    }*/


    @Override
    public void configureView(BigImageCard card) {
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
