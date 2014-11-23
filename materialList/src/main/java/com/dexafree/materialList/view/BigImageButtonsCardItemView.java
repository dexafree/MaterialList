package com.dexafree.materialList.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dexafree.materialList.R;
import com.dexafree.materialList.model.BigImageButtonsCard;
import com.dexafree.materialList.model.GridItemView;

public class BigImageButtonsCardItemView extends GridItemView<BigImageButtonsCard>{

    private final static int DIVIDER_MARGIN_DP = 16;

    private TextView mTitle;

    private ImageView mImage;

    private TextView mDescription;

    private TextView mLeftText;

    private TextView mRightText;

    public BigImageButtonsCardItemView(Context context) {
        super(context);
    }

    public BigImageButtonsCardItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public BigImageButtonsCardItemView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }


    @Override
    public void configureView(BigImageButtonsCard card) {
        setTitle(card.getTitle());
        setDescription(card.getDescription());
        setImage(card.getBitmap());
        setLeftText(card);
        setRightText(card);
        setDivider(card);
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

    public void setLeftText(final BigImageButtonsCard card){
        mLeftText = (TextView) findViewById(R.id.left_text_button);
        mLeftText.setText(card.getLeftButtonText());
        mLeftText.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                card.getOnLeftButtonPressedListener().onButtonPressedListener(mLeftText);
            }
        });
    }

    public void setRightText(final BigImageButtonsCard card){
        mRightText = (TextView) findViewById(R.id.right_text_button);
        mRightText.setText(card.getRightButtonText());
        mRightText.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                card.getOnRightButtonPressedListener().onButtonPressedListener(mRightText);
            }
        });
    }

    public void setDivider(final BigImageButtonsCard card){
        int visibility = card.getShowDivider()? VISIBLE : INVISIBLE;

        View divider = findViewById(R.id.cardDivider);

        divider.setVisibility(visibility);

        // After setting the visibility, we prepare the divider params according to the preferences
        if(card.getShowDivider()){

            // If the divider has to be from side to side, the margin will be 0
            if(card.getFullDividerLength()) {
                ((RelativeLayout.LayoutParams) divider.getLayoutParams()).setMargins(0, 0, 0, 0);
            } else {

                // Convert DP to PX
                int dividerMarginPx = (int) TypedValue.applyDimension(
                        TypedValue.COMPLEX_UNIT_DIP,
                        DIVIDER_MARGIN_DP,
                        getContext().getResources().getDisplayMetrics()
                );

                // Set the margin
                ((RelativeLayout.LayoutParams) divider.getLayoutParams()).setMargins(
                        dividerMarginPx,
                        0,
                        dividerMarginPx,
                        0
                );
            }

        }
    }

}
