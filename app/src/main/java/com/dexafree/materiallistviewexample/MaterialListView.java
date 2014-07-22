package com.dexafree.materiallistviewexample;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;


public class MaterialListView extends ListView {

    private MaterialListViewAdapter mAdapter;

    public MaterialListView (Context context) {
        super (context);
    }

    public MaterialListView (Context context, AttributeSet attrs, int defStyle) {
        super (context, attrs, defStyle);
    }

    public MaterialListView (Context context, AttributeSet attrs) {
        super (context, attrs);
    }

    public void setMaterialListViewAdapter (MaterialListViewAdapter adapter) {
        mAdapter = adapter;
        setAdapter (mAdapter);
        setDivider (null);
        setDividerHeight (8);
    }

    public void setOnDismissCallback (SwipeDismissListViewTouchListener.OnDismissCallback callback) {

        SwipeDismissListViewTouchListener touchListener = new SwipeDismissListViewTouchListener (this, callback);

        setOnTouchListener (touchListener);
        setOnScrollListener (touchListener.makeScrollListener());

    }

    public void setDefaultListeners () {
        SwipeDismissListViewTouchListener touchListener =
                new SwipeDismissListViewTouchListener(
                        this,
                        new SwipeDismissListViewTouchListener.OnDismissCallback () {

                            @Override
                            public void onDismiss (ListView listView, int[] reverseSortedPositions) {
                                for (int position : reverseSortedPositions) {
                                    mAdapter.remove(mAdapter.getItem(position));
                                }
                                mAdapter.notifyDataSetChanged();
                            }

                            @Override
                            public boolean canDismiss (ListView listView, int position){
                                return true;
                            }
                        });

        setOnTouchListener (touchListener);
        setOnScrollListener (touchListener.makeScrollListener());
    }

}
