package com.dexafree.materialList.view;

import android.content.Context;
import android.graphics.PorterDuff;
import android.support.v7.widget.CardView;
import android.support.v7.widget.MyRoundRectDrawableWithShadow;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dexafree.materialList.R;
import com.dexafree.materialList.events.BusProvider;
import com.dexafree.materialList.events.DismissEvent;
import com.dexafree.materialList.model.GridItemView;
import com.dexafree.materialList.model.WelcomeCard;

public class WelcomeCardItemView extends GridItemView<WelcomeCard> {

    private TextView mTitle;
    private TextView mSubtitle;
    private TextView mDescription;
    private TextView mButton;
    private View mDivider;
    private RelativeLayout backgroundView;
    private ImageView checkMark;

    public WelcomeCardItemView(Context context) {
        super(context);
    }

    public WelcomeCardItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public WelcomeCardItemView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public void configureView(WelcomeCard card) {
        setTitle(card.getTitle());
        setSubtitle(card.getSubtitle());
        setDescription(card.getDescription());
        setButton(card);
        setColors(card);
    }

    public void setTitle(String title){
        mTitle = (TextView)findViewById(R.id.titleTextView);
        mTitle.setText(title);
    }

    public void setSubtitle(String subtitle){
        mSubtitle = (TextView)findViewById(R.id.subtitleTextView);
        mSubtitle.setText(subtitle);
    }

    public void setDescription(String description){
        mDescription = (TextView)findViewById(R.id.descriptionTextView);
        mDescription.setText(description);
    }


    public void setButton(final WelcomeCard card){
        mButton = (TextView) findViewById(R.id.ok_button);
        mButton.setText(card.getButtonText());
        mButton.setTextColor(card.getBackgroundColor());

        final GridItemView<WelcomeCard> refView = this;

        mButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("CLICK", "Text clicked");

                if(card.getOnButtonPressedListener() != null) {
                    card.getOnButtonPressedListener().onButtonPressedListener(mButton);
                }

                BusProvider.getInstance().post(new DismissEvent(card, refView));
            }
        });


    }

    private void setColors(WelcomeCard card){

        CardView cardView = (CardView)findViewById(R.id.cardView);
        MyRoundRectDrawableWithShadow backgroundDrawable = new MyRoundRectDrawableWithShadow(
                getContext().getResources(),
                card.getBackgroundColor(),
                cardView.getRadius(),
                6f,
                6f
        );

        int textSize = pxToDp(36);

        cardView.setBackgroundDrawable(backgroundDrawable);
        checkMark = (ImageView)findViewById(R.id.check_mark);
        mDivider = findViewById(R.id.cardDivider);
        mDivider.setBackgroundColor(card.getDividerColor());
        mTitle.setTextColor(card.getTitleColor());
        mSubtitle.setTextColor(card.getSubtitleColor());
        mDescription.setTextColor(card.getDescriptionColor());
        mButton.setTextColor(card.getButtonTextColor());
        mButton.setTextSize((float) textSize);
        checkMark.setColorFilter(card.getButtonTextColor(), PorterDuff.Mode.SRC_IN);

    }


}
