package com.dexafree.materialList.view;

import android.content.Context;
import android.graphics.PorterDuff;
import android.os.Build;
import android.support.v7.widget.CardView;
import android.support.v7.widget.MyRoundRectDrawableWithShadow;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dexafree.materialList.R;
import com.dexafree.materialList.model.WelcomeCard;

public class WelcomeCardItemView extends CardItemView<WelcomeCard> {
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
    public void build(final WelcomeCard card) {
        super.build(card);

        int textSize = pxToDp(36);

        // Title
        TextView title = (TextView)findViewById(R.id.titleTextView);
        title.setTextColor(card.getTitleColor());

        // Subtitle
        TextView subtitle = (TextView)findViewById(R.id.subtitleTextView);
        subtitle.setText(card.getSubtitle());
        subtitle.setTextColor(card.getSubtitleColor());

        // Description
        ((TextView)findViewById(R.id.descriptionTextView)).setTextColor(card.getDescriptionColor());

        // Divider
        View divider = findViewById(R.id.cardDivider);
        divider.setBackgroundColor(card.getDividerColor());

        // Check mark
        ImageView checkMark = (ImageView) findViewById(R.id.check_mark);
        checkMark.setColorFilter(card.getButtonTextColor(), PorterDuff.Mode.SRC_IN);

        // Button
        final TextView button = (TextView) findViewById(R.id.ok_button);
        button.setText(card.getButtonText());
        button.setTextColor(card.getButtonTextColor());
        button.setTextSize((float) textSize);

        final CardItemView<WelcomeCard> refView = this;

        button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                //Log.d("CLICK", "Text clicked");

                if (card.getOnButtonPressedListener() != null) {
                    card.getOnButtonPressedListener().onButtonPressedListener(button, getCard());
                }

                // The developer should decide for his own to dismiss or not
                //BusProvider.getInstance().post(new DismissEvent(card));
            }
        });

        // Background Color
        CardView cardView = (CardView)findViewById(R.id.cardView);

        if(Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            MyRoundRectDrawableWithShadow backgroundDrawable = new MyRoundRectDrawableWithShadow(
                    getContext().getResources(),
                    card.getBackgroundColor(),
                    cardView.getRadius(),
                    6f,
                    6f
            );
            cardView.setBackgroundDrawable(backgroundDrawable);
        } else {
            cardView.setBackgroundColor(card.getBackgroundColor());
            cardView.setCardElevation(dpToPx(6));
        }
    }
}
