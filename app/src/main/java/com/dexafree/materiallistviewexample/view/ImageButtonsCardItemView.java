package com.dexafree.materiallistviewexample.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dexafree.materiallistviewexample.R;
import com.dexafree.materiallistviewexample.model.GridItemView;
import com.dexafree.materiallistviewexample.model.ImageButtonsCard;

public class ImageButtonsCardItemView extends GridItemView<ImageButtonsCard>{

    TextView mTitle;

    ImageView mImage;

    TextView mDescription;

    TextView mLeftText;

    TextView mRightText;

    public ImageButtonsCardItemView(Context context) {
        super(context);
    }

    public ImageButtonsCardItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ImageButtonsCardItemView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    /*@Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if(mImage != null) {
            LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) mImage.getLayoutParams();
            params.height = getMeasuredWidth();
            mImage.setLayoutParams(params);
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        }
    }*/


    @Override
    public void configureView(ImageButtonsCard card) {
        setTitle(card.getTitle());
        setDescription(card.getDescription());
        setImage(card.getBitmap());
        setLeftText(card);
        setRightText(card);
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

    public void setLeftText(final ImageButtonsCard card){
        mLeftText = (TextView) findViewById(R.id.left_text_button);
        mLeftText.setText(card.getLeftButtonText());
        mLeftText.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                card.getOnButtonPressedListener().onLeftTextPressed(mLeftText);
            }
        });
    }

    public void setRightText(final ImageButtonsCard card){
        mRightText = (TextView) findViewById(R.id.right_text_button);
        mRightText.setText(card.getRightButtonText());
        mRightText.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                card.getOnButtonPressedListener().onRightTextPressed(mRightText);
            }
        });
    }

}
