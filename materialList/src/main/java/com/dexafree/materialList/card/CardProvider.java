package com.dexafree.materialList.card;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v7.widget.CardView;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.dexafree.materialList.R;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.RequestCreator;

import java.util.HashMap;
import java.util.Map;
import java.util.Observable;

/**
 * The basic CardProvider.
 */
public class CardProvider<T extends CardProvider> extends Observable {
    private final static int DIVIDER_MARGIN_DP = 16;

    private Context mContext;
    private Card.Builder mBuilder;

    private String mTitle;
    private String mSubtitle;
    private String mDescription;
    private boolean mDividerVisible;
    private boolean mFullWidthDivider;
    private int mTitleGravity;
    private int mSubtitleGravity;
    private int mDescriptionGravity;

    @ColorInt
    private int mBackgroundColor = Color.WHITE;
    @ColorInt
    private int mTitleColor;
    @ColorInt
    private int mSubtitleColor;
    @ColorInt
    private int mDescriptionColor;
    @ColorInt
    private int mDividerColor;
    @Nullable
    private Drawable mDrawable;
    @Nullable
    private String mUrlImage;

    private final Map<Integer, Action> mActionMapping = new HashMap<>();

    private OnImageConfigListener mOnImageConfigListenerListener;
    private int mLayoutId;

    /////////////////////////////////////////////////////////////////
    //
    //      Functions related to the builder pattern.
    //
    /////////////////////////////////////////////////////////////////

    /**
     * Do NOT use this method! Only for the {@code Card.Builder}!
     *
     * @param context to access the resources.
     */
    void setContext(Context context) {
        mContext = context;
        onCreated();
    }

    /**
     * Do NOT use this method! Only for the {@code Card.Builder}!
     *
     * @param builder to return the {@code Card.Builder} by {@code endConfig}.
     */
    void setBuilder(Card.Builder builder) {
        mBuilder = builder;
    }

    protected void onCreated() {
        setTitleResourceColor(R.color.grey_title);
        setDescriptionResourceColor(R.color.description_color);
    }

    /**
     * Get the context.
     *
     * @return the context.
     */
    protected Context getContext() {
        return mContext;
    }

    /**
     * End withProvider the configuration.
     *
     * @return the {@code Card.Builder}.
     */
    public Card.Builder endConfig() {
        return mBuilder;
    }

    /**
     * Notifies the Card that the content changed.
     */
    protected void notifyDataSetChanged() {
        notifyDataSetChanged(null);
    }

    /**
     * Notifies the Card that the content changed.
     */
    protected void notifyDataSetChanged(@Nullable final Object object) {
        setChanged();
        notifyObservers(object);
    }

    /////////////////////////////////////////////////////////////////
    //
    //      Functions related to setting and getting the properties.
    //
    /////////////////////////////////////////////////////////////////

    @NonNull
    @SuppressWarnings("unchecked")
    public T setLayout(@LayoutRes final int layoutId) {
        mLayoutId = layoutId;
        return (T) this;
    }

    /**
     * Get the card layout as resource.
     *
     * @return the card layout.
     */
    @LayoutRes
    public int getLayout() {
        return mLayoutId;
    }

    /**
     * Get the background color.
     *
     * @return the background color.
     */
    @ColorInt
    public int getBackgroundColor() {
        return mBackgroundColor;
    }

    /**
     * Set the background color withProvider an real color (e.g. {@code Color.WHITE}).
     *
     * @param color as real.
     * @return the renderer.
     */
    @NonNull
    @SuppressWarnings("unchecked")
    public T setBackgroundColor(@ColorInt final int color) {
        mBackgroundColor = color;
        notifyDataSetChanged();
        return (T) this;
    }

    /**
     * Set the background color withProvider an resource color (e.g. {@code
     * android.R.color.white}).
     *
     * @param color as resource.
     * @return the renderer.
     */
    @NonNull
    public T setBackgroundResourceColor(@ColorRes final int color) {
        return setBackgroundColor(getContext().getResources().getColor(color));
    }

    /**
     * Get the title.
     *
     * @return the title.
     */
    public String getTitle() {
        return mTitle;
    }

    /**
     * Set the title withProvider a string resource.
     *
     * @param title to set.
     * @return the renderer.
     */
    @NonNull
    public T setTitle(@StringRes final int title) {
        return setTitle(getContext().getString(title));
    }

    /**
     * Set the title.
     *
     * @param title to set.
     * @return the renderer.
     */
    @SuppressWarnings("unchecked")
    public T setTitle(@NonNull final String title) {
        mTitle = title;
        notifyDataSetChanged();
        return (T) this;
    }

    /**
     * Set Gravity of title.
     *
     * @param titleGravity
     * @return the renderer.
     */

    @NonNull
    @SuppressWarnings("unchecked")
    public T setTitleGravity(final int titleGravity) {
        mTitleGravity = titleGravity;
        notifyDataSetChanged();
        return (T) this;
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
     * @param subtitle to set.
     * @return the renderer.
     */
    @NonNull
    public T setSubtitle(@StringRes final int subtitle) {
        return setSubtitle(getContext().getString(subtitle));
    }

    /**
     * Set the subtitle.
     *
     * @param subtitle to set.
     * @return the renderer.
     */
    @NonNull
    @SuppressWarnings("unchecked")
    public T setSubtitle(final String subtitle) {
        mSubtitle = subtitle;
        notifyDataSetChanged();
        return (T) this;
    }

    /**
     * Set Gravity of subtitle
     *
     * @param subtitleGravity
     * @return the renderer.
     */
    @NonNull
    @SuppressWarnings("unchecked")
    public T setSubtitleGravity(final int subtitleGravity) {
        mSubtitleGravity = subtitleGravity;
        notifyDataSetChanged();
        return (T) this;
    }

    /**
     * Get the description.
     *
     * @return the description.
     */
    public String getDescription() {
        return mDescription;
    }

    /**
     * Set the description withProvider a string resource.
     *
     * @param description to set.
     * @return the renderer.
     */
    @NonNull
    public T setDescription(@StringRes final int description) {
        return setDescription(getContext().getString(description));
    }

    /**
     * Set the description.
     *
     * @param description to set.
     * @return the renderer.
     */
    @NonNull
    @SuppressWarnings("unchecked")
    public T setDescription(@NonNull final String description) {
        mDescription = description;
        notifyDataSetChanged();
        return (T) this;
    }

    /**
     *Set Gravity of description
     *
     * @param descriptionGravity to set.
     * @return the renderer.
     */
    @NonNull
    @SuppressWarnings("unchecked")
    public T setDescriptionGravity(final int descriptionGravity) {
        mDescriptionGravity = descriptionGravity;
        notifyDataSetChanged();
        return (T) this;
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
     * Set the divider color as resource.
     *
     * @param color to set.
     * @return the renderer.
     */
    @NonNull
    public T setDividerResourceColor(@ColorRes final int color) {
        return setDividerColor(getContext().getResources().getColor(color));
    }

    /**
     * Set the divider color as int.
     *
     * @param color to set.
     * @return the renderer.
     */
    @NonNull
    @SuppressWarnings("unchecked")
    public T setDividerColor(@ColorInt final int color) {
        mDividerColor = color;
        notifyDataSetChanged();
        return (T) this;
    }

    /**
     * Get the drawable.
     *
     * @return the drawable.
     */
    public Drawable getDrawable() {
        return mDrawable;
    }

    /**
     * Set the drawable withProvider a drawable resource.
     *
     * @param drawable to set.
     * @return the renderer.
     */
    @NonNull
    public T setDrawable(@DrawableRes final int drawable) {
        return setDrawable(Uri.parse("android.resource://" + getContext().getPackageName()
                + "/" + drawable).toString());
    }

    /**
     * Set the drawable. This drawable can not be configured inside of the ImageView. It will
     * directly be drawn. If the configuration of the image is necessary, use {@link
     * #setDrawable(int)} or {@link #setDrawable(String)} and {@link
     * #setDrawableConfiguration(OnImageConfigListener)} to configure the render process.
     *
     * @param drawable to set.
     * @return the renderer.
     */
    @NonNull
    @SuppressWarnings("unchecked")
    public T setDrawable(@Nullable final Drawable drawable) {
        mDrawable = drawable;
        notifyDataSetChanged();
        return (T) this;
    }

    /**
     * Set the drawable withProvider a web url.
     *
     * @param urlImage to set.
     * @return the renderer.
     */
    @NonNull
    @SuppressWarnings("unchecked")
    public T setDrawable(@Nullable final String urlImage) {
        mUrlImage = urlImage;
        notifyDataSetChanged();
        return (T) this;
    }

    /**
     * Get the web url.
     *
     * @return the url.
     */
    public String getImageUrl() {
        return mUrlImage;
    }

    /**
     * Get the title color as int.
     *
     * @return the color.
     */
    @ColorInt
    public int getTitleColor() {
        return mTitleColor;
    }

    /**
     * Get the title gravity as int.
     *
     * @return the gravity.
     */

    public int getTitleGravity(){
        return mTitleGravity;
    }

    /**
     * Set the title color as int.
     *
     * @param color to set as int.
     * @return the renderer.
     */
    @NonNull
    @SuppressWarnings("unchecked")
    public T setTitleColor(@ColorInt final int color) {
        mTitleColor = color;
        notifyDataSetChanged();
        return (T) this;
    }

    /**
     * Set the title color as resource.
     *
     * @param color to set as resource.
     * @return the renderer.
     */
    @NonNull
    public T setTitleResourceColor(@ColorRes final int color) {
        return setTitleColor(getContext().getResources().getColor(color));
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
     * Get the subtitle gravity as int.
     *
     * @return the subtitle gravity.
     */
    public int getSubtitleGravity(){
        return mSubtitleGravity;
    }

    /**
     * Set the subtitle color as resource.
     *
     * @param color to set.
     * @return the renderer.
     */
    @NonNull
    public T setSubtitleResourceColor(@ColorRes final int color) {
        return setSubtitleColor(getContext().getResources().getColor(color));
    }

    /**
     * Set the subtitle color as int.
     *
     * @param color to set.
     * @return the renderer.
     */
    @NonNull
    @SuppressWarnings("unchecked")
    public T setSubtitleColor(@ColorInt final int color) {
        mSubtitleColor = color;
        notifyDataSetChanged();
        return (T) this;
    }

    /**
     * Get the description color as int.
     *
     * @return the color.
     */
    @ColorInt
    public int getDescriptionColor() {
        return mDescriptionColor;
    }

    /**
     * Get the description gravity as int.
     *
     * @return the gravity.
     */
    public int getDescriptionGravity() {
        return mDescriptionGravity;
    }

    /**
     * Set the description color as int.
     *
     * @param color to set.
     * @return the renderer.
     */
    @NonNull
    @SuppressWarnings("unchecked")
    public T setDescriptionColor(@ColorInt final int color) {
        mDescriptionColor = color;
        notifyDataSetChanged();
        return (T) this;
    }

    /**
     * Set the description color as resource.
     *
     * @param color to set.
     * @return the renderer.
     */
    @NonNull
    public T setDescriptionResourceColor(@ColorRes final int color) {
        return setDescriptionColor(getContext().getResources().getColor(color));
    }

    /**
     * Set the listener for image customizations.
     *
     * @param listener to set.
     * @return the renderer.
     */
    @NonNull
    @SuppressWarnings("unchecked")
    public T setDrawableConfiguration(@NonNull final OnImageConfigListener listener) {
        mOnImageConfigListenerListener = listener;
        return (T) this;
    }

    /**
     * Get the listener.
     *
     * @return the listener.
     */
    public OnImageConfigListener getOnImageConfigListenerListener() {
        return mOnImageConfigListenerListener;
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
     * @param visible to set.
     * @return the renderer.
     */
    @NonNull
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
     * @param fullWidthDivider to set.
     * @return the renderer.
     */
    @NonNull
    @SuppressWarnings("unchecked")
    public T setFullWidthDivider(final boolean fullWidthDivider) {
        mFullWidthDivider = fullWidthDivider;
        notifyDataSetChanged();
        return (T) this;
    }

    /**
     *
     * @param actionViewId
     * @param action
     * @return
     */
    @NonNull
    @SuppressWarnings("unchecked")
    public T addAction(@IdRes final int actionViewId, @NonNull final Action action) {
        mActionMapping.put(actionViewId, action);
        return (T) this;
    }

    /**
     *
     * @param actionViewId
     * @return
     */
    @Nullable
    public Action getAction(@IdRes final int actionViewId) {
        return mActionMapping.get(actionViewId);
    }

    /////////////////////////////////////////////////////////////////
    //
    //      Functions for rendering.
    //
    /////////////////////////////////////////////////////////////////

    /**
     * Renders the content and style of the card to the view.
     *
     * @param view to display the content and style on.
     * @param card to render.
     */
    @SuppressWarnings("unchecked")
    public void render(@NonNull final View view, @NonNull final Card card) {
        // The card background
        final CardView cardView = findViewById(view, R.id.cardView, CardView.class);
        if (cardView != null) {
            cardView.setCardBackgroundColor(getBackgroundColor());
        }

        // Title
        final TextView title = findViewById(view, R.id.title, TextView.class);
        if (title != null) {
            title.setText(getTitle());
            title.setTextColor(getTitleColor());
            title.setGravity(getTitleGravity());
        }

        // Subtitle
        final TextView subtitle = findViewById(view, R.id.subtitle, TextView.class);
        if (subtitle != null) {
            subtitle.setText(getSubtitle());
            subtitle.setTextColor(getSubtitleColor());
            subtitle.setGravity(getSubtitleGravity());
            if (getSubtitle() == null || getSubtitle().isEmpty()) {
                subtitle.setVisibility(View.GONE);
            } else {
                subtitle.setVisibility(View.VISIBLE);
            }
        }

        // Description
        final TextView supportingText = findViewById(view, R.id.supportingText, TextView.class);
        if (supportingText != null) {
            supportingText.setText(getDescription());
            supportingText.setTextColor(getDescriptionColor());
            supportingText.setGravity(getDescriptionGravity());
        }

        // Image
        final ImageView imageView = findViewById(view, R.id.image, ImageView.class);
        if (imageView != null) {
            if (getDrawable() != null) {
                imageView.setImageDrawable(getDrawable());
            } else {
                final RequestCreator requestCreator = Picasso.with(getContext())
                        .load(getImageUrl());
                if (getOnImageConfigListenerListener() != null) {
                    getOnImageConfigListenerListener().onImageConfigure(requestCreator);
                }
                requestCreator.into(imageView);
            }
        }

        // Divider
        final View divider = findViewById(view, R.id.divider, View.class);
        if (divider != null) {
            divider.setVisibility(isDividerVisible() ? View.VISIBLE : View.INVISIBLE);

            // After setting the visibility, we prepare the divider params
            // according to the preferences
            if (isDividerVisible()) {
                // If the divider has to be from side to side, the margin will be 0
                final ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams)
                        divider.getLayoutParams();
                if (isFullWidthDivider()) {
                    params.setMargins(0, 0, 0, 0);
                } else {
                    int dividerMarginPx = dpToPx(DIVIDER_MARGIN_DP);
                    // Set the margin
                    params.setMargins(
                            dividerMarginPx,
                            0,
                            dividerMarginPx,
                            0
                    );
                }
            }
        }

        // Actions
        for (final Map.Entry<Integer, Action> entry : mActionMapping.entrySet()) {
            final View actionViewRaw = findViewById(view, entry.getKey(), View.class);
            if (actionViewRaw != null) {
                final Action action = entry.getValue();
                action.setProvider(this);
                action.onRender(actionViewRaw, card);
            }
        }
    }

    @Nullable
    protected <V extends View> V findViewById(@NonNull final View view, @IdRes final int id,
                                              @NonNull final Class<V> type) {
        final View viewById = view.findViewById(id);
        if (viewById != null) {
            return type.cast(viewById);
        } else {
            return null;
        }
    }

    /////////////////////////////////////////////////////////////////
    //
    //      Interfaces.
    //
    /////////////////////////////////////////////////////////////////

    /**
     * The OnImageConfigListener will be called, if an image is loaded from an url to an ImageView.
     */
    public interface OnImageConfigListener {
        /**
         * An image is loaded from an url and can be customized now.
         *
         * @param requestCreator to customize the image.
         */
        void onImageConfigure(@NonNull final RequestCreator requestCreator);
    }

    /////////////////////////////////////////////////////////////////
    //
    //      Helper methods.
    //
    /////////////////////////////////////////////////////////////////

    /**
     *
     * @param dp
     * @return
     */
    protected int dpToPx(final int dp) {
        DisplayMetrics displayMetrics = getContext().getResources().getDisplayMetrics();
        return (int) Math.round(dp * (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));
    }
}
