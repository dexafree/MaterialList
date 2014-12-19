package com.dexafree.materialList.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.BaseAdapter;
import android.widget.ListView;

import com.dexafree.materialList.MaterialListViewAdapter;
import com.dexafree.materialList.controller.OnDismissCallback;
import com.dexafree.materialList.controller.SwipeDismissListener;
import com.dexafree.materialList.events.BusProvider;
import com.dexafree.materialList.model.Card;
import com.nhaarman.listviewanimations.appearance.AnimationAdapter;
import com.nhaarman.listviewanimations.appearance.simple.AlphaInAnimationAdapter;
import com.nhaarman.listviewanimations.appearance.simple.ScaleInAnimationAdapter;
import com.nhaarman.listviewanimations.appearance.simple.SwingBottomInAnimationAdapter;
import com.nhaarman.listviewanimations.appearance.simple.SwingLeftInAnimationAdapter;
import com.nhaarman.listviewanimations.appearance.simple.SwingRightInAnimationAdapter;


public class MaterialListView extends ListView {
	private MaterialListViewAdapter mAdapter;
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

		mAdapter = new MaterialListViewAdapter(getContext());
		setAdapter(mAdapter);
    }

	public void setCardAnimation(CardAnimation type) {
		BaseAdapter baseAdapter = mAdapter;

		switch (type) {
			case ALPHA_IN: {
				AnimationAdapter animationAdapter = new AlphaInAnimationAdapter(baseAdapter);
				animationAdapter.setAbsListView(this);
				baseAdapter = animationAdapter;
			} break;
			case SCALE_IN: {
				AnimationAdapter animationAdapter = new ScaleInAnimationAdapter(baseAdapter);
				animationAdapter.setAbsListView(this);
				baseAdapter = animationAdapter;
			} break;
			case SWING_BOTTOM_IN: {
				AnimationAdapter animationAdapter = new SwingBottomInAnimationAdapter(baseAdapter);
				animationAdapter.setAbsListView(this);
				baseAdapter = animationAdapter;
			} break;
			case SWING_LEFT_IN: {
				AnimationAdapter animationAdapter = new SwingLeftInAnimationAdapter(baseAdapter);
				animationAdapter.setAbsListView(this);
				baseAdapter = animationAdapter;
			} break;
			case SWING_RIGHT_IN: {
				AnimationAdapter animationAdapter = new SwingRightInAnimationAdapter(baseAdapter);
				animationAdapter.setAbsListView(this);
				baseAdapter = animationAdapter;
			} break;
		}

		super.setAdapter(baseAdapter);
	}

	public MaterialListViewAdapter getAdapter() {
        return mAdapter;
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

	public enum CardAnimation {
		ALPHA_IN, SCALE_IN, SWING_BOTTOM_IN, SWING_LEFT_IN, SWING_RIGHT_IN
	}
}
