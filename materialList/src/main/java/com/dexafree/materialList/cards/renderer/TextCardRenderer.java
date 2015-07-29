package com.dexafree.materialList.cards.renderer;

import android.content.Context;
import android.graphics.Color;
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
import com.dexafree.materialList.cards.Card;
import com.squareup.picasso.Picasso;

/**
 * Created by Fabio on 29.07.2015.
 */
public abstract class TextCardRenderer<T extends TextCardRenderer> extends CardRenderer<T> {
    @ColorInt
    private int mTitleColor = Color.BLACK;
    @ColorInt
    private int mDescriptionColor = Color.GRAY;
    private String mTitle;
    private String mDescription;
    @Nullable
    private Drawable mDrawable;
    @Nullable
    private String mUrlImage;

    /**
     * @param context
     */
    public TextCardRenderer(@NonNull final Context context) {
        super(context);
    }

    /**
     * @return
     */
    public String getTitle() {
        return mTitle;
    }

    /**
     * @param title
     * @return
     */
    public T setTitle(@StringRes final int title) {
        setTitle(mContext.getString(title));
        return (T) this;
    }

    /**
     * @param title
     * @return
     */
    public T setTitle(@NonNull final String title) {
        mTitle = title;
        notifyDataSetChanged();
        return (T) this;
    }

    /**
     * @return
     */
    public String getDescription() {
        return mDescription;
    }

    /**
     * @param description
     * @return
     */
    public T setDescription(@StringRes final int description) {
        setDescription(mContext.getString(description));
        return (T) this;
    }

    /**
     * @param description
     * @return
     */
    public T setDescription(@NonNull final String description) {
        mDescription = description;
        notifyDataSetChanged();
        return (T) this;
    }

    /**
     * @return
     */
    public Drawable getDrawable() {
        return mDrawable;
    }

    /**
     * @param drawable
     * @return
     */
    public T setDrawable(@DrawableRes final int drawable) {
        setDrawable(mContext.getResources().getDrawable(drawable));
        return (T) this;
    }

    /**
     * @param drawable
     * @return
     */
    public T setDrawable(@Nullable final Drawable drawable) {
        mDrawable = drawable;
        notifyDataSetChanged();
        return (T) this;
    }

    /**
     * @param urlImage
     * @return
     */
    public T setDrawable(@Nullable final String urlImage) {
        mUrlImage = urlImage;
        notifyDataSetChanged();
        return (T) this;
    }

    /**
     * @return
     */
    public String getImageUrl() {
        return mUrlImage;
    }

    /**
     * @return
     */
    @ColorInt
    public int getTitleColor() {
        return mTitleColor;
    }

    /**
     * @param color
     * @return
     */
    public T setTitleColor(@ColorInt final int color) {
        mTitleColor = color;
        notifyDataSetChanged();
        return (T) this;
    }

    /**
     * @param color
     * @return
     */
    public T setTitleResourceColor(@ColorRes final int color) {
        setTitleColor(mContext.getResources().getColor(color));
        return (T) this;
    }

    /**
     * @return
     */
    @ColorInt
    public int getDescriptionColor() {
        return mDescriptionColor;
    }

    /**
     * @param color
     * @return
     */
    public T setDescriptionColor(@ColorInt final int color) {
        mDescriptionColor = color;
        notifyDataSetChanged();
        return (T) this;
    }

    /**
     * @param color
     * @return
     */
    public T setDescriptionResourceColor(@ColorRes final int color) {
        setDescriptionColor(mContext.getResources().getColor(color));
        return (T) this;
    }

    @Override
    public void render(@NonNull final View view, @NonNull final Card card) {
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
                Picasso.with(mContext).load(getImageUrl()).into(imageView);
            }
        }
    }
}
