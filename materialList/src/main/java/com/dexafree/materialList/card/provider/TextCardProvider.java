package com.dexafree.materialList.card.provider;

import android.graphics.drawable.Drawable;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.dexafree.materialList.R;
import com.dexafree.materialList.card.Card;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.RequestCreator;

/**
 * A TextCardProvider will render a Title, Description and Drawable to a Card.
 */
public abstract class TextCardProvider<T extends TextCardProvider> extends CardProvider<T> {
    private String mTitle;
    private String mDescription;
    @ColorInt
    private int mTitleColor;
    @ColorInt
    private int mDescriptionColor;
    @Nullable
    private Drawable mDrawable;
    @Nullable
    private String mUrlImage;
    private OnPicassoImageLoadingListener mOnPicassoImageLoadingListenerListener;

    @Override
    protected void onCreated() {
        super.onCreated();
        setTitleResourceColor(R.color.grey_title);
        setDescriptionResourceColor(R.color.description_color);
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
     * Set the title with a string resource.
     *
     * @param title
     *         to set.
     * @return the renderer.
     */
    public T setTitle(@StringRes final int title) {
        return setTitle(getContext().getString(title));
    }

    /**
     * Set the title.
     *
     * @param title
     *         to set.
     * @return the renderer.
     */
    @SuppressWarnings("unchecked")
    public T setTitle(@NonNull final String title) {
        mTitle = title;
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
     * Set the description with a string resource.
     *
     * @param description
     *         to set.
     * @return the renderer.
     */
    public T setDescription(@StringRes final int description) {
        return setDescription(getContext().getString(description));
    }

    /**
     * Set the description.
     *
     * @param description
     *         to set.
     * @return the renderer.
     */
    @SuppressWarnings("unchecked")
    public T setDescription(@NonNull final String description) {
        mDescription = description;
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
     * Set the drawable with a drawable resource.
     *
     * @param drawable
     *         to set.
     * @return the renderer.
     */
    public T setDrawable(@DrawableRes final int drawable) {
        return setDrawable(getContext().getResources().getDrawable(drawable));
    }

    /**
     * Set the drawable.
     *
     * @param drawable
     *         to set.
     * @return the renderer.
     */
    @SuppressWarnings("unchecked")
    public T setDrawable(@Nullable final Drawable drawable) {
        mDrawable = drawable;
        notifyDataSetChanged();
        return (T) this;
    }

    /**
     * Set the drawable with a web url.
     *
     * @param urlImage
     *         to set.
     * @return the renderer.
     */
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
     * Set the title color as int.
     *
     * @param color
     *         to set as int.
     * @return the renderer.
     */
    @SuppressWarnings("unchecked")
    public T setTitleColor(@ColorInt final int color) {
        mTitleColor = color;
        notifyDataSetChanged();
        return (T) this;
    }

    /**
     * Set the title color as resource.
     *
     * @param color
     *         to set as resource.
     * @return the renderer.
     */
    public T setTitleResourceColor(@ColorRes final int color) {
        return setTitleColor(getContext().getResources().getColor(color));
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
     * Set the description color as int.
     *
     * @param color to set.
     * @return the renderer.
     */
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
    public T setDescriptionResourceColor(@ColorRes final int color) {
        return setDescriptionColor(getContext().getResources().getColor(color));
    }

    /**
     * Set the listener for image customizations.
     *
     * @param listener to set.
     * @return the renderer.
     */
    @SuppressWarnings("unchecked")
    public T setOnPicassoImageLoadingListener(@NonNull final OnPicassoImageLoadingListener listener) {
        mOnPicassoImageLoadingListenerListener = listener;
        return (T) this;
    }

    /**
     * Get the listener.
     *
     * @return the listener.
     */
    public OnPicassoImageLoadingListener getOnPicassoImageLoadingListener() {
        return mOnPicassoImageLoadingListenerListener;
    }

    @Override
    public void render(@NonNull final View view, @NonNull final Card card) {
        super.render(view, card);

        // Title
        TextView title = (TextView) view.findViewById(R.id.titleTextView);
        title.setText(getTitle());
        title.setTextColor(getTitleColor());

        // Description
        TextView description = (TextView) view.findViewById(R.id.descriptionTextView);
        description.setText(getDescription());
        description.setTextColor(getDescriptionColor());

        // Image
        ImageView imageView = (ImageView) view.findViewById(R.id.imageView);
        if (imageView != null) {
            if (getImageUrl() == null || getImageUrl().isEmpty()) {
                imageView.setImageDrawable(getDrawable());
            } else {
                final RequestCreator requestCreator = Picasso.with(getContext()).load(getImageUrl());
                if (getOnPicassoImageLoadingListener() != null) {
                    getOnPicassoImageLoadingListener().onImageLoading(requestCreator);
                }
                requestCreator.into(imageView);
            }
        }
    }

    /**
     * The OnPicassoImageLoadingListener will be called, if an image is loaded from an url to an
     * ImageView.
     */
    public interface OnPicassoImageLoadingListener {
        /**
         * An image is loaded from an url and can be customized now.
         *
         * @param requestCreator
         *         to customize the image.
         */
        void onImageLoading(@NonNull final RequestCreator requestCreator);
    }
}
