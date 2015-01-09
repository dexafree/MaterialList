package com.dexafree.materialList.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dexafree.materialList.R;
import com.dexafree.materialList.model.BasicImageButtonsCard;
import com.dexafree.materialList.model.ButtonsCard;
import com.dexafree.materialList.model.Card;

public abstract class ButtonsCardItemView<T extends ButtonsCard> extends CardItemView<T> {
    private final static int DIVIDER_MARGIN_DP = 16;

    public ButtonsCardItemView(Context context) {
        super(context);
    }

    public ButtonsCardItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public ButtonsCardItemView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public void build(final T card) {
        super.build(card);

        // Left Button - Text
        final TextView leftText = (TextView) findViewById(R.id.left_text_button);
        leftText.setText(card.getLeftButtonText());
        leftText.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                card.getOnLeftButtonPressedListener().onButtonPressedListener(leftText, getCard());
            }
        });

        // Right Button - Text
        final TextView rightText = (TextView) findViewById(R.id.right_text_button);
        rightText.setText(card.getRightButtonText());
        rightText.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                card.getOnRightButtonPressedListener().onButtonPressedListener(rightText, getCard());
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
                ((LinearLayout.LayoutParams) divider.getLayoutParams()).setMargins(0, 0, 0, 0);
            } else {

                // Convert DP to PX
                int dividerMarginPx = (int) TypedValue.applyDimension(
                        TypedValue.COMPLEX_UNIT_DIP,
                        DIVIDER_MARGIN_DP,
                        getContext().getResources().getDisplayMetrics()
                );

                // Set the margin
                ((LinearLayout.LayoutParams) divider.getLayoutParams()).setMargins(
                        dividerMarginPx,
                        0,
                        dividerMarginPx,
                        0
                );
            }

        }
    }
}
