package com.dexafree.materialList.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.dexafree.materialList.MaterialListViewAdapter;
import com.dexafree.materialList.controller.OnDismissCallback;
import com.dexafree.materialList.controller.SwipeDismissListener;
import com.dexafree.materialList.events.BusProvider;
import com.dexafree.materialList.events.DismissEvent;
import com.dexafree.materialList.model.Card;
import com.squareup.otto.Subscribe;


public class MaterialListView extends ListView {
    private OnDismissCallback mDismissCallback;

    public MaterialListView (Context context) {
        super(context);
        init();
    }

    public MaterialListView (Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public MaterialListView (Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        setDivider (null);
        setDividerHeight (8);

        SwipeDismissListener mListener =
                new SwipeDismissListener(
                        this,
                        new SwipeDismissListener.OnDismissCallback () {
                            @Override
                            public void onDismiss (MaterialListView listView, int[] reverseSortedPositions) {
                                for (int position : reverseSortedPositions) {
                                    if(position < listView.getAdapter().getCount()) {
                                        Card currentCard = getAdapter().getItem(position);

                                        if (mDismissCallback != null) {
                                            mDismissCallback.onDismiss(currentCard, position);
                                        }

                                        getAdapter().remove(currentCard);
                                    }
                                }

                                getAdapter().notifyDataSetChanged();
                            }
                        });

        setOnTouchListener (mListener);
        setOnScrollListener (mListener.makeScrollListener());
    }

    public ArrayAdapter<Card> getAdapter() {
        return (ArrayAdapter<Card>) super.getAdapter();
    }

    public void setOnDismissCallback (OnDismissCallback callback) {
        mDismissCallback = callback;
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        BusProvider.getInstance().register(this);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        BusProvider.getInstance().unregister(this);
    }
}
