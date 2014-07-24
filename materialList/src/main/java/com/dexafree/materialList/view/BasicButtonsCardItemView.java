package com.dexafree.materialList.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import com.dexafree.materialList.R;
import com.dexafree.materialList.model.BasicButtonsCard;
import com.dexafree.materialList.model.GridItemView;

public class BasicButtonsCardItemView extends GridItemView<BasicButtonsCard> {

    TextView mTitle;

    TextView mDescription;

    TextView mLeftText;

    TextView mRightText;

    public BasicButtonsCardItemView(Context context) {
        super(context);
    }

    public BasicButtonsCardItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public BasicButtonsCardItemView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }


    @Override
    public void configureView(BasicButtonsCard card) {
        setTitle(card.getTitle());
        setDescription(card.getDescription());
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

    public void setLeftText(final BasicButtonsCard card){
        mLeftText = (TextView) findViewById(R.id.left_text_button);
        mLeftText.setText(card.getLeftButtonText());
        mLeftText.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                card.getOnButtonPressedListener().onLeftTextPressed(mLeftText);
            }
        });
    }

    public void setRightText(final BasicButtonsCard card){
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
