package com.dexafree.materialList.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.dexafree.materialList.R;
import com.dexafree.materialList.controller.IMaterialListAdapter;
import com.dexafree.materialList.controller.MaterialListAdapter;
import com.dexafree.materialList.controller.OnDismissCallback;
import com.dexafree.materialList.controller.SwipeDismissListener;
import com.dexafree.materialList.controller.SwipeDismissRecyclerViewTouchListener;
import com.dexafree.materialList.events.BusProvider;
import com.dexafree.materialList.events.DataSetChangedEvent;
import com.dexafree.materialList.events.DismissEvent;
import com.dexafree.materialList.model.Card;
import com.squareup.otto.Subscribe;

import java.util.Collection;


public class MaterialListView extends RecyclerView {
    private OnDismissCallback mDismissCallback;
    private SwipeDismissListener mDismissListener;

    public MaterialListView(Context context) {
        this(context, null);
    }

	public MaterialListView(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

    public MaterialListView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

		/*
		mDismissListener =
				new SwipeDismissListener(
						this,
						new SwipeDismissListener.OnDismissCallback() {
							@Override
							public void onDismiss(MaterialListView listView, final Card[] reverseSortedCards) {
								for (Card dismissedCard : reverseSortedCards) {
									int position = ((IMaterialListAdapter) getAdapter()).getPosition(dismissedCard);
									//Log.d(getClass().getSimpleName(), dismissedCard.getmTitle() +
									// " [Position="+position+"]");

									if (mDismissCallback != null) {
										mDismissCallback.onDismiss(dismissedCard, position);
									}

									((IMaterialListAdapter) getAdapter()).remove(dismissedCard, false);
								}
								getAdapter().notifyDataSetChanged();
							}
						});

		setOnTouchListener(mDismissListener);
		setOnScrollListener(mDismissListener.makeScrollListener());
		*/

		SwipeDismissRecyclerViewTouchListener touchListener = new SwipeDismissRecyclerViewTouchListener(this, new SwipeDismissRecyclerViewTouchListener.DismissCallbacks() {
			@Override
			public boolean canDismiss(final int position) {
				return ((IMaterialListAdapter) getAdapter()).getCard(position).isDismissible();
			}

			@Override
			public void onDismiss(final RecyclerView recyclerView, final int[] reverseSortedPositions) {
				for (int reverseSortedPosition : reverseSortedPositions) {
					final Card card = ((IMaterialListAdapter) getAdapter()).getCard(reverseSortedPosition);
					((IMaterialListAdapter) getAdapter()).remove(card, false);
					Log.d("DissmissListener", "delete: " + card.getClass());
				}
			}
		});
		setOnTouchListener(touchListener);
		setOnScrollListener(touchListener.makeScrollListener());

		setAdapter(new MaterialListAdapter());

		Log.d(getClass().getSimpleName(), "Setup...");

		if(attrs != null) {
			// get the number of columns
			TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.MaterialListView, defStyle, 0);

			if(typedArray.hasValue(R.styleable.MaterialListView_column_count)) {
				Log.d(getClass().getSimpleName(), "Has ColumnCount set");
			}

			int columnCount = typedArray.getInteger(R.styleable.MaterialListView_column_count, 1);
			if(columnCount > 1) {
				setLayoutManager(new StaggeredGridLayoutManager(columnCount, StaggeredGridLayoutManager.VERTICAL));
			} else {
				setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
			}

			Log.d(getClass().getSimpleName(), "ColumnCount="+columnCount);

			typedArray.recycle();
		}
    }

    public void remove(Card card) {
        if (card.isDismissible()) {
            BusProvider.dismiss(card);
        }
    }

    public void add(Card card) {
		((IMaterialListAdapter) getAdapter()).add(card);
    }

    public void addAll(Card... cards) {
		((IMaterialListAdapter) getAdapter()).addAll(cards);
    }

    public void addAll(Collection<Card> cards) {
		((IMaterialListAdapter) getAdapter()).addAll(cards);
    }

	/*
    public void setCardAnimation(CardAnimation type) {
        BaseAdapter baseAdapter = mAdapter;

        switch (type) {
            case ALPHA_IN: {
                AnimationAdapter animationAdapter = new AlphaInAnimationAdapter(baseAdapter);
                animationAdapter.setAbsListView(this);
                baseAdapter = animationAdapter;
            }
            break;
            case SCALE_IN: {
                AnimationAdapter animationAdapter = new ScaleInAnimationAdapter(baseAdapter);
                animationAdapter.setAbsListView(this);
                baseAdapter = animationAdapter;
            }
            break;
            case SWING_BOTTOM_IN: {
                AnimationAdapter animationAdapter = new SwingBottomInAnimationAdapter(baseAdapter);
                animationAdapter.setAbsListView(this);
                baseAdapter = animationAdapter;
            }
            break;
            case SWING_LEFT_IN: {
                AnimationAdapter animationAdapter = new SwingLeftInAnimationAdapter(baseAdapter);
                animationAdapter.setAbsListView(this);
                baseAdapter = animationAdapter;
            }
            break;
            case SWING_RIGHT_IN: {
                AnimationAdapter animationAdapter = new SwingRightInAnimationAdapter(baseAdapter);
                animationAdapter.setAbsListView(this);
                baseAdapter = animationAdapter;
            }
            break;
        }

        setAdapter(baseAdapter);
    }
    */

	@Override
	public void setAdapter(final Adapter adapter) {
		if(adapter instanceof IMaterialListAdapter) {
			super.setAdapter(adapter);
		} else {
			throw new IllegalArgumentException("The Adapter must implement IMaterialListAdapter");
		}
	}

    public void setOnDismissCallback(OnDismissCallback callback) {
        mDismissCallback = callback;
    }

    @Subscribe
    public void onNotifyDataSetChanged(DataSetChangedEvent event) {
        getAdapter().notifyDataSetChanged();
    }

    @Subscribe
    public void onCardDismiss(DismissEvent event) {
        Card dismissedCard = event.getDismissedCard();
        View dismissedCardView = null;
        for (int index = 0; index < getAdapter().getItemCount(); index++) {
            View view = getChildAt(index);
            if (view.getTag() != null && view.getTag().equals(dismissedCard)) {
                dismissedCardView = view;
                break;
            }
        }
        if (dismissedCardView != null) {
            mDismissListener.dismissCard(dismissedCardView, dismissedCard);
        }
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        BusProvider.register(this);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        BusProvider.unregister(this);
    }
}
