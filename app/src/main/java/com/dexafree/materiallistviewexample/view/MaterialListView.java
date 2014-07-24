package com.dexafree.materiallistviewexample.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

import com.dexafree.materiallistviewexample.MaterialListViewAdapter;
import com.dexafree.materiallistviewexample.controller.SwipeDismissListener;


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

    public void setOnDismissCallback (SwipeDismissListener.OnDismissCallback callback) {

        SwipeDismissListener touchListener = new SwipeDismissListener(this, callback);

        setOnTouchListener (touchListener);
        setOnScrollListener (touchListener.makeScrollListener());

    }

    public void setDefaultListeners () {
        SwipeDismissListener touchListener =
                new SwipeDismissListener(
                        this,
                        new SwipeDismissListener.OnDismissCallback () {

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
