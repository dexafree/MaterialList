package com.dexafree.materialList.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

import com.dexafree.materialList.MaterialAdapter;
import com.dexafree.materialList.controller.MaterialInterface;
import com.dexafree.materialList.controller.OnDismissCallback;
import com.dexafree.materialList.controller.SwipeDismissListener;
import com.dexafree.materialList.model.Card;

public class MaterialGridView extends GridView implements MaterialInterface {

    private MaterialAdapter mAdapter;
    private OnDismissCallback mDismissCallback;

    public MaterialGridView (Context context) {
        super (context);
    }

    public MaterialGridView (Context context, AttributeSet attrs, int defStyle) {
        super (context, attrs, defStyle);
    }

    public MaterialGridView (Context context, AttributeSet attrs) {
        super (context, attrs);
    }

    public void setMaterialListViewAdapter (MaterialAdapter adapter) {

        mAdapter = adapter;
        setAdapter (mAdapter);
        setDefaultListeners();

    }

    public MaterialAdapter getMaterialAdapter(){
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
                            public void onDismiss (MaterialInterface listView, int[] reverseSortedPositions) {

                                for (int position : reverseSortedPositions) {

                                    if(position < listView.getMaterialAdapter().getCount()) {

                                        Card currentCard = mAdapter.getItem(position);

                                        if (mDismissCallback != null) {

                                            mDismissCallback.onDismiss(currentCard, position);

                                        }

                                        mAdapter.remove(currentCard);
                                    }

                                }

                                mAdapter.notifyDataSetChanged();

                            }

                        });

        setOnTouchListener (touchListener);
        setOnScrollListener (touchListener.makeScrollListener());
    }
}
