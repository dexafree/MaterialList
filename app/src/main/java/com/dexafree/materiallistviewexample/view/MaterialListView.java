package com.dexafree.materiallistviewexample.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

import com.dexafree.materiallistviewexample.MaterialListViewAdapter;
import com.dexafree.materiallistviewexample.controller.OnDismissCallback;
import com.dexafree.materiallistviewexample.controller.SwipeDismissListener;
import com.dexafree.materiallistviewexample.model.Card;


public class MaterialListView extends ListView {

    private MaterialListViewAdapter mAdapter;
    private OnDismissCallback mDismissCallback;

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
        setDefaultListeners();
    }

    public MaterialListViewAdapter getMaterialListViewAdapter(){
        return mAdapter;
    }

    public void setOnDismissCallback (OnDismissCallback callback) {

        mDismissCallback = callback;

    }

    private void setDefaultListeners () {

        SwipeDismissListener touchListener =
                new SwipeDismissListener(
                        this,
                        new SwipeDismissListener.OnDismissCallback () {

                            @Override
                            public void onDismiss (MaterialListView listView, int[] reverseSortedPositions) {
                                for (int position : reverseSortedPositions) {
                                    Card currentCard = mAdapter.getItem(position);
                                    if(mDismissCallback != null){
                                        mDismissCallback.onDismiss(currentCard, position);
                                    }
                                    mAdapter.remove(currentCard);
                                }
                                mAdapter.notifyDataSetChanged();
                            }

                            @Override
                            public boolean canDismiss (MaterialListView listView, int position){
                                return true;
                            }
                        });

        setOnTouchListener (touchListener);
        setOnScrollListener (touchListener.makeScrollListener());
    }

}
