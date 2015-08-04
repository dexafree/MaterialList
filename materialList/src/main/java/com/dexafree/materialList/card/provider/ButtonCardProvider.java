package com.dexafree.materialList.card.provider;

import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dexafree.materialList.R;
import com.dexafree.materialList.card.Card;
import com.dexafree.materialList.card.OnButtonClickListener;
import com.dexafree.materialList.util.PixelUtils;

/**
 * A ButtonCardProvider will render a left and right Button, a Divider and everything like
 * TextCardProvider does.
 */
public abstract class ButtonCardProvider<T extends ButtonCardProvider> extends TextCardProvider<T> {
    private final static int DIVIDER_MARGIN_DP = 16;

    @ColorInt
    private int mRightButtonTextColor;
    @ColorInt
    private int mLeftButtonTextColor;
    private boolean mDividerVisible;
    private boolean mFullWidthDivider;
    private String mLeftButtonText;
    private String mRightButtonText;
    @Nullable
    private OnButtonClickListener mOnLeftButtonClickListener;
    @Nullable
    private OnButtonClickListener mOnRightButtonClickListener;

    @Override
    protected void onCreated() {
        super.onCreated();
        setRightButtonTextResourceColor(R.color.orange_button);
        setLeftButtonTextResourceColor(R.color.black_button);
    }

    /**
     * Get the left button text.
     *
     * @return the left button text.
     */
    public String getLeftButtonText() {
        return mLeftButtonText;
    }

    /**
     * Set the left button text as string resource.
     *
     * @param leftButtonText
     *         to set.
     * @return the renderer.
     */
    public T setLeftButtonText(@StringRes final int leftButtonText) {
        return setLeftButtonText(getContext().getString(leftButtonText));
    }

    /**
     * Set the left button text.
     *
     * @param leftButtonText
     *         to set.
     * @return the renderer.
     */
    @SuppressWarnings("unchecked")
    public T setLeftButtonText(final String leftButtonText) {
        mLeftButtonText = leftButtonText;
        notifyDataSetChanged();
        return (T) this;
    }

    /**
     * Get the right button text.
     *
     * @return the right button text.
     */
    public String getRightButtonText() {
        return mRightButtonText;
    }

    /**
     * Set the right button text as string resource.
     *
     * @param rightButtonText
     *         to set.
     * @return the renderer.
     */
    public T setRightButtonText(@StringRes final int rightButtonText) {
        return setRightButtonText(getContext().getString(rightButtonText));
    }

    /**
     * Set the right button text.
     *
     * @param rightButtonText
     *         to set.
     * @return the renderer.
     */
    @SuppressWarnings("unchecked")
    public T setRightButtonText(final String rightButtonText) {
        mRightButtonText = rightButtonText;
        notifyDataSetChanged();
        return (T) this;
    }

    /**
     * Get the listener for on click events of the left button.
     *
     * @return the listener.
     */
    @Nullable
    public OnButtonClickListener getOnLeftButtonClickListener() {
        return mOnLeftButtonClickListener;
    }

    /**
     * Set the listener for on click events of the left button.
     *
     * @param listener
     *         to set.
     * @return the renderer.
     */
    @SuppressWarnings("unchecked")
    public T setOnLeftButtonClickListener(@Nullable final OnButtonClickListener listener) {
        mOnLeftButtonClickListener = listener;
        return (T) this;
    }

    /**
     * Get the listener for on click events of the right button.
     *
     * @return the listener.
     */
    @Nullable
    public OnButtonClickListener getOnRightButtonClickListener() {
        return mOnRightButtonClickListener;
    }

    /**
     * Set the listener for on click events of the right button.
     *
     * @param listener
     *         to set.
     * @return the renderer.
     */
    @SuppressWarnings("unchecked")
    public T setOnRightButtonClickListener(@Nullable final OnButtonClickListener listener) {
        mOnRightButtonClickListener = listener;
        return (T) this;
    }

    /**
     * Get the right button text color as int.
     *
     * @return the color.
     */
    @ColorInt
    public int getRightButtonTextColor() {
        return mRightButtonTextColor;
    }

    /**
     * Set the right button text color as int.
     *
     * @param color
     *         to set.
     * @return the renderer.
     */
    @SuppressWarnings("unchecked")
    public T setRightButtonTextColor(@ColorInt final int color) {
        mRightButtonTextColor = color;
        notifyDataSetChanged();
        return (T) this;
    }

    /**
     * Set the right button text color as resource.
     *
     * @param color
     *         to set.
     * @return the renderer.
     */
    public T setRightButtonTextResourceColor(@ColorRes final int color) {
        return setRightButtonTextColor(getContext().getResources().getColor(color));
    }

    /**
     * Get the left button text color as int.
     *
     * @return the color.
     */
    @ColorInt
    public int getLeftButtonTextColor() {
        return mLeftButtonTextColor;
    }

    /**
     * Set the left button text color as int.
     *
     * @param color
     *         to set.
     * @return the renderer.
     */
    @SuppressWarnings("unchecked")
    public T setLeftButtonTextColor(@ColorInt final int color) {
        mLeftButtonTextColor = color;
        notifyDataSetChanged();
        return (T) this;
    }

    /**
     * Set the left button text color as resource.
     *
     * @param color
     *         to set.
     * @return the renderer.
     */
    public T setLeftButtonTextResourceColor(@ColorRes final int color) {
        return setLeftButtonTextColor(getContext().getResources().getColor(color));
    }

    /**
     * Get the visibility state of the divider.
     *
     * @return the visibility state of the divider.
     */
    public boolean isDividerVisible() {
        return mDividerVisible;
    }

    /**
     * Set the divider visible or invisible.
     *
     * @param visible
     *         to set.
     * @return the renderer.
     */
    @SuppressWarnings("unchecked")
    public T setDividerVisible(final boolean visible) {
        mDividerVisible = visible;
        notifyDataSetChanged();
        return (T) this;
    }

    /**
     * Get the state of wideness of the divider.
     *
     * @return the wideness of the divider.
     */
    public boolean isFullWidthDivider() {
        return mFullWidthDivider;
    }

    /**
     * Set the wideness of the divider to full.
     *
     * @param fullWidthDivider
     *         to set.
     * @return the renderer.
     */
    @SuppressWarnings("unchecked")
    public T setFullWidthDivider(final boolean fullWidthDivider) {
        mFullWidthDivider = fullWidthDivider;
        notifyDataSetChanged();
        return (T) this;
    }

    @Override
    public void render(@NonNull final View view, @NonNull final Card card) {
        super.render(view, card);

        // Left Button - Text
        final TextView leftText = (TextView) view.findViewById(R.id.left_text_button);
        if (getLeftButtonTextColor() != -1) {
            leftText.setTextColor(getLeftButtonTextColor());
        }
        if (getLeftButtonText() != null) {
            leftText.setText(getLeftButtonText().toUpperCase());
            leftText.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    OnButtonClickListener listener = getOnLeftButtonClickListener();
                    if (listener != null) {
                        listener.onButtonClicked(leftText, card);
                    }
                }
            });
        }

        // Right Button - Text
        final TextView rightText = (TextView) view.findViewById(R.id.right_text_button);
        if (getRightButtonTextColor() != -1) {
            rightText.setTextColor(getRightButtonTextColor());
        }
        if (getRightButtonText() != null) {
            rightText.setText(getRightButtonText().toUpperCase());
            rightText.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    OnButtonClickListener listener = getOnRightButtonClickListener();
                    if (listener != null) {
                        listener.onButtonClicked(rightText, card);
                    }
                }
            });
        }

        // Divider
        View divider = view.findViewById(R.id.cardDivider);
        divider.setVisibility(isDividerVisible() ? View.VISIBLE : View.INVISIBLE);

        // After setting the visibility, we prepare the divider params according to the preferences
        if (isDividerVisible()) {
            // If the divider has to be from side to side, the margin will be 0
            if (isFullWidthDivider()) {
                ((ViewGroup.MarginLayoutParams) divider.getLayoutParams()).setMargins(0, 0, 0, 0);
            } else {
                int dividerMarginPx = (int) PixelUtils.dpToPx(getContext(), DIVIDER_MARGIN_DP);
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
