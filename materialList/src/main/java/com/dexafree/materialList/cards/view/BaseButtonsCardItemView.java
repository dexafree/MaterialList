package com.dexafree.materialList.cards.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dexafree.materialList.R;
import com.dexafree.materialList.cards.model.ExtendedCard;

public abstract class BaseButtonsCardItemView<T extends ExtendedCard> extends BaseTextCardItemView<T> {
    private final static int DIVIDER_MARGIN_DP = 16;

    public BaseButtonsCardItemView(Context context) {
        super(context);
    }

    public BaseButtonsCardItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public BaseButtonsCardItemView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public void build(final T card) {
        super.build(card);

        // Left Button - Text
        final TextView leftText = (TextView) findViewById(R.id.left_text_button);
        leftText.setText(card.getLeftButtonText().toUpperCase());
        leftText.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                card.getOnLeftButtonPressedListener().onButtonPressedListener(leftText, card);
            }
        });

        // Right Button - Text
        final TextView rightText = (TextView) findViewById(R.id.right_text_button);
        rightText.setText(card.getRightButtonText().toUpperCase());
        if(card.getRightButtonTextColor() > -1) {
            rightText.setTextColor(card.getRightButtonTextColor());
        }
        rightText.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                card.getOnRightButtonPressedListener().onButtonPressedListener(rightText, card);
            }
        });

        // Divider
        int visibility = card.isDividerVisible()? VISIBLE : INVISIBLE;

        View divider = findViewById(R.id.cardDivider);

        divider.setVisibility(visibility);

        // After setting the visibility, we prepare the divider params according to the preferences
        if(card.isDividerVisible()){

            // If the divider has to be from side to side, the margin will be 0
            if(card.isFullWidthDivider()) {
                ((ViewGroup.MarginLayoutParams) divider.getLayoutParams()).setMargins(0, 0, 0, 0);
            } else {
                int dividerMarginPx = (int) dpToPx(DIVIDER_MARGIN_DP);
                // Set the margin
                ((ViewGroup.MarginLayoutParams) divider.getLayoutParams()).setMargins(
                        dividerMarginPx,
                        0,
                        dividerMarginPx,
                        0
                );
            }
        }
    }
}
