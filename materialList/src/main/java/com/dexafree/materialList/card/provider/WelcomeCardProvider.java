package com.dexafree.materialList.card.provider;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.view.View;
import android.widget.TextView;

import com.dexafree.materialList.R;
import com.dexafree.materialList.card.Card;
import com.dexafree.materialList.card.OnButtonClickListener;

public class WelcomeCardProvider extends TextCardProvider<WelcomeCardProvider> {
    private String mSubtitle;
    private String mButtonText;
    private OnButtonClickListener mListener;
    @ColorInt
    private int mSubtitleColor;
    @ColorInt
    private int mDividerColor;
    @ColorInt
    private int mButtonTextColor;

    /**
     * Creates a WelcomeCardProvider.
     */
    public WelcomeCardProvider() {
        setSubtitleColor(Color.WHITE);
        setDividerColor(Color.parseColor("#608DFA"));
        setButtonTextColor(Color.WHITE);
    }

    /**
     * Get the subtitle.
     *
     * @return the subtitle.
     */
    public String getSubtitle() {
        return mSubtitle;
    }

    /**
     * Set the subtitle as resource.
     *
     * @param subtitle
     *         to set.
     * @return the renderer.
     */
    public WelcomeCardProvider setSubtitle(@StringRes final int subtitle) {
        return setSubtitle(getContext().getString(subtitle));
    }

    /**
     * Set the subtitle.
     *
     * @param subtitle
     *         to set.
     * @return the renderer.
     */
    public WelcomeCardProvider setSubtitle(final String subtitle) {
        mSubtitle = subtitle;
        notifyDataSetChanged();
        return this;
    }

    /**
     * Get the listener for the button click event.
     *
     * @return the listener.
     */
    public OnButtonClickListener getOnButtonPressedListener() {
        return mListener;
    }

    /**
     * Set the listener for the button click event.
     *
     * @param listener
     *         to set.
     * @return the renderer.
     */
    public WelcomeCardProvider setOnButtonPressedListener(OnButtonClickListener listener) {
        mListener = listener;
        return this;
    }

    /**
     * Get the button text.
     *
     * @return the button text.
     */
    public String getButtonText() {
        return mButtonText;
    }

    /**
     * Get the subtitle color as int.
     *
     * @return the subtitle color.
     */
    @ColorInt
    public int getSubtitleColor() {
        return mSubtitleColor;
    }

    /**
     * Get the divider color as int.
     *
     * @return the divider color.
     */
    @ColorInt
    public int getDividerColor() {
        return mDividerColor;
    }

    /**
     * Get the button text color as int.
     *
     * @return the button text color.
     */
    @ColorInt
    public int getButtonTextColor() {
        return mButtonTextColor;
    }

    /**
     * Set the button text as resource.
     *
     * @param buttonText
     *         to set.
     * @return the renderer.
     */
    public WelcomeCardProvider setButtonText(@StringRes final int buttonText) {
        return setButtonText(getContext().getString(buttonText));
    }

    /**
     * Set the button text.
     *
     * @param buttonText
     *         to set.
     * @return the renderer.
     */
    public WelcomeCardProvider setButtonText(final String buttonText) {
        mButtonText = buttonText;
        notifyDataSetChanged();
        return this;
    }

    /**
     * Set the subtitle color as resource.
     *
     * @param color
     *         to set.
     * @return the renderer.
     */
    public WelcomeCardProvider setSubtitleResourceColor(@ColorRes final int color) {
        setSubtitleColor(getContext().getResources().getColor(color));
        return this;
    }

    /**
     * Set the subtitle color as int.
     *
     * @param color
     *         to set.
     * @return the renderer.
     */
    public WelcomeCardProvider setSubtitleColor(@ColorInt final int color) {
        mSubtitleColor = color;
        notifyDataSetChanged();
        return this;
    }

    /**
     * Set the divider color as resource.
     *
     * @param color
     *         to set.
     * @return the renderer.
     */
    public WelcomeCardProvider setDividerResourceColor(@ColorRes final int color) {
        return setDividerColor(getContext().getResources().getColor(color));
    }

    /**
     * Set the divider color as int.
     *
     * @param color
     *         to set.
     * @return the renderer.
     */
    public WelcomeCardProvider setDividerColor(@ColorInt final int color) {
        mDividerColor = color;
        notifyDataSetChanged();
        return this;
    }

    /**
     * Set the button text color as resource.
     *
     * @param color
     *         to set.
     * @return the renderer.
     */
    public WelcomeCardProvider setButtonTextResourceColor(@ColorRes final int color) {
        return setButtonTextColor(getContext().getResources().getColor(color));
    }

    /**
     * Set the button text color as int.
     *
     * @param color
     *         to set.
     * @return the renderer.
     */
    public WelcomeCardProvider setButtonTextColor(@ColorInt final int color) {
        mButtonTextColor = color;
        notifyDataSetChanged();
        return this;
    }

    @Override
    public int getLayout() {
        return R.layout.material_welcome_card_layout;
    }

    @Override
    public void render(@NonNull final View view, @NonNull final Card card) {
        super.render(view, card);

        // Subtitle
        TextView subtitle = (TextView) view.findViewById(R.id.subtitleTextView);
        subtitle.setText(getSubtitle());
        subtitle.setTextColor(getSubtitleColor());

        // Divider
        View divider = view.findViewById(R.id.cardDivider);
        divider.setBackgroundColor(getDividerColor());

        // Button
        final TextView button = (TextView) view.findViewById(R.id.ok_button);
        button.setText(getButtonText());
        button.setTextColor(getButtonTextColor());
        Drawable drawable = button.getCompoundDrawables()[0];
        drawable.setColorFilter(getButtonTextColor(), PorterDuff.Mode.SRC_IN);
        button.setCompoundDrawablesWithIntrinsicBounds(resize(drawable, 50, 50), null, null, null);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (getOnButtonPressedListener() != null) {
                    getOnButtonPressedListener().onButtonClicked(button, card);
                }
            }
        });
    }

    private Drawable resize(Drawable image, int width, int height) {
        Bitmap b = ((BitmapDrawable) image).getBitmap();
        Bitmap bitmapResize = Bitmap.createScaledBitmap(b, width, height, false);
        return new BitmapDrawable(getContext().getResources(), bitmapResize);
    }
}
