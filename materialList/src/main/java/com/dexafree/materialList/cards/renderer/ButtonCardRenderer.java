package com.dexafree.materialList.cards.renderer;

import android.content.Context;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dexafree.materialList.R;
import com.dexafree.materialList.cards.Card;
import com.dexafree.materialList.cards.OnButtonClickListener;
import com.dexafree.materialList.util.PixelUtils;

/**
 * Created by Fabio on 29.07.2015.
 */
public abstract class ButtonCardRenderer<T extends ButtonCardRenderer> extends TextCardRenderer<T> {
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

    /**
     * @param context
     */
    public ButtonCardRenderer(@NonNull final Context context) {
        super(context);
        setRightButtonTextResourceColor(R.color.orange_button);
        setLeftButtonTextResourceColor(R.color.black_button);
    }

    /**
     * @return
     */
    public String getLeftButtonText() {
        return mLeftButtonText;
    }

    /**
     * @param leftButtonText
     * @return
     */
    public T setLeftButtonText(@StringRes final int leftButtonText) {
        return setLeftButtonText(mContext.getString(leftButtonText));
    }

    /**
     * @param leftButtonText
     * @return
     */
    public T setLeftButtonText(final String leftButtonText) {
        mLeftButtonText = leftButtonText;
        notifyDataSetChanged();
        return (T) this;
    }

    /**
     * @return
     */
    public String getRightButtonText() {
        return mRightButtonText;
    }

    /**
     * @param rightButtonText
     * @return
     */
    public T setRightButtonText(@StringRes final int rightButtonText) {
        return setRightButtonText(mContext.getString(rightButtonText));
    }

    /**
     * @param rightButtonText
     * @return
     */
    public T setRightButtonText(final String rightButtonText) {
        mRightButtonText = rightButtonText;
        notifyDataSetChanged();
        return (T) this;
    }

    /**
     * @return
     */
    @Nullable
    public OnButtonClickListener getOnLeftButtonClickListener() {
        return mOnLeftButtonClickListener;
    }

    /**
     * @param listener
     * @return
     */
    public T setOnLeftButtonClickListener(@Nullable final OnButtonClickListener listener) {
        mOnLeftButtonClickListener = listener;
        return (T) this;
    }

    /**
     * @return
     */
    @Nullable
    public OnButtonClickListener getOnRightButtonClickListener() {
        return mOnRightButtonClickListener;
    }

    /**
     * @param listener
     * @return
     */
    public T setOnRightButtonClickListener(@Nullable final OnButtonClickListener listener) {
        mOnRightButtonClickListener = listener;
        return (T) this;
    }

    /**
     * @return
     */
    @ColorInt
    public int getRightButtonTextColor() {
        return mRightButtonTextColor;
    }

    /**
     * @param color
     * @return
     */
    public T setRightButtonTextColor(@ColorInt final int color) {
        mRightButtonTextColor = color;
        notifyDataSetChanged();
        return (T) this;
    }

    /**
     * @param color
     * @return
     */
    public T setRightButtonTextResourceColor(@ColorRes final int color) {
        return setRightButtonTextColor(mContext.getResources().getColor(color));
    }

    /**
     * @return
     */
    @ColorInt
    public int getLeftButtonTextColor() {
        return mLeftButtonTextColor;
    }

    /**
     * @param color
     * @return
     */
    public T setLeftButtonTextColor(@ColorInt final int color) {
        mLeftButtonTextColor = color;
        notifyDataSetChanged();
        return (T) this;
    }

    /**
     * @param color
     * @return
     */
    public T setLeftButtonTextResourceColor(@ColorRes final int color) {
        return setLeftButtonTextColor(mContext.getResources().getColor(color));
    }

    /**
     * @return
     */
    public boolean isDividerVisible() {
        return mDividerVisible;
    }

    /**
     * @param visible
     * @return
     */
    public T setDividerVisible(final boolean visible) {
        mDividerVisible = visible;
        notifyDataSetChanged();
        return (T) this;
    }

    /**
     * @return
     */
    public boolean isFullWidthDivider() {
        return mFullWidthDivider;
    }

    /**
     * @param fullWidthDivider
     * @return
     */
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
                        listener.onButtonPressedListener(leftText, card);
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
                        listener.onButtonPressedListener(rightText, card);
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
                int dividerMarginPx = (int) PixelUtils.dpToPx(mContext, DIVIDER_MARGIN_DP);
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
