package com.dexafree.materialList.cards.internal;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.PorterDuff;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;
import com.dexafree.materialList.cards.WelcomeCard;
import com.dexafree.materialList.R;

public class WelcomeCardItemView extends BaseTextCardItemView<WelcomeCard> {
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

        // Subtitle
        TextView subtitle = (TextView) findViewById(R.id.subtitleTextView);
        subtitle.setText(card.getSubtitle());
        subtitle.setTextColor(card.getSubtitleColor());

        // Divider
        View divider = findViewById(R.id.cardDivider);
        divider.setBackgroundColor(card.getDividerColor());

        // Button
        final TextView button = (TextView) findViewById(R.id.ok_button);
        button.setText(card.getButtonText());
        button.setTextColor(card.getButtonTextColor());
        Drawable drawable = button.getCompoundDrawables()[0];
        drawable.setColorFilter(card.getButtonTextColor(), PorterDuff.Mode.SRC_IN);
        button.setCompoundDrawablesWithIntrinsicBounds(resize(drawable, 50, 50), null, null, null);

        button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (card.getOnButtonPressedListener() != null) {
                    card.getOnButtonPressedListener().onButtonPressedListener(button, card);
                }
            }
        });
    }

    private Drawable resize(Drawable image, int width, int height) {
        Bitmap b = ((BitmapDrawable) image).getBitmap();
        Bitmap bitmapResize = Bitmap.createScaledBitmap(b, width, height, false);
        return new BitmapDrawable(getResources(), bitmapResize);
    }
}
