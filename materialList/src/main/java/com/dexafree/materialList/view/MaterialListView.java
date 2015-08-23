package com.dexafree.materialList.view;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.dexafree.materialList.R;
import com.dexafree.materialList.card.Card;
import com.dexafree.materialList.listeners.OnDismissCallback;
import com.dexafree.materialList.listeners.RecyclerItemClickListener;
import com.dexafree.materialList.listeners.SwipeDismissRecyclerViewTouchListener;

import java.util.Collection;


public class MaterialListView extends RecyclerView {

    private static final int DEFAULT_COLUMNS_PORTRAIT = 1;
    private static final int DEFAULT_COLUMNS_LANDSCAPE = 2;

    private OnDismissCallback mDismissCallback;
    private SwipeDismissRecyclerViewTouchListener mDismissListener;
    private View emptyView;

    private int mColumnCount;
    private int mColumnCountLandscape = DEFAULT_COLUMNS_LANDSCAPE;
    private int mColumnCountPortrait = DEFAULT_COLUMNS_PORTRAIT;

    final AdapterDataObserver observer = new AdapterDataObserver() {
        @Override
        public void onChanged() {
            super.onChanged();
            checkIfEmpty();
        }
    };

    public MaterialListView(Context context) {
        this(context, null);
    }

    public MaterialListView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MaterialListView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        mDismissListener = new SwipeDismissRecyclerViewTouchListener(this, new SwipeDismissRecyclerViewTouchListener.DismissCallbacks() {
            @Override
            public boolean canDismiss(final int position) {
                final Card card = ((IMaterialListAdapter) getAdapter()).getCard(position);
                return card != null && card.isDismissible();
            }

            @Override
            public void onDismiss(final RecyclerView recyclerView, final int[] reverseSortedPositions) {
                for (int reverseSortedPosition : reverseSortedPositions) {
                    final Card card = ((IMaterialListAdapter) getAdapter()).getCard(reverseSortedPosition);
                    if(card != null) {
                        ((IMaterialListAdapter) getAdapter()).remove(card, false);
                        if (mDismissCallback != null) {
                            mDismissCallback.onDismiss(card, reverseSortedPosition);
                        }
                        Log.d("DissmissListener", "delete: " + card.getClass());
                    }
                }
            }
        });
        setOnTouchListener(mDismissListener);
        setOnScrollListener(mDismissListener.makeScrollListener());

        setAdapter(new MaterialListAdapter(new OnSwipeAnimation() {
            @Override
            public void animate(final int position) {
                ViewHolder holder = findViewHolderForPosition(position);
                mDismissListener.dismissCard(holder.itemView, position);
            }
        }));

        Log.d(getClass().getSimpleName(), "Setup...");

        if (attrs != null) {
            // get the number of columns
            TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.MaterialListView, defStyle, 0);

            if (typedArray.hasValue(R.styleable.MaterialListView_column_count_landscape) ||
                    typedArray.hasValue(R.styleable.MaterialListView_column_count_portrait) ||
                    typedArray.hasValue(R.styleable.MaterialListView_column_count)) {
                Log.d(getClass().getSimpleName(), "Has ColumnCount set");
            }

            mColumnCount = typedArray.getInteger(R.styleable.MaterialListView_column_count, 0);
            if (mColumnCount > 0) {
                mColumnCountPortrait = mColumnCount;
                mColumnCountLandscape = mColumnCount;
            } else {
                mColumnCountPortrait = typedArray.getInteger(
                        R.styleable.MaterialListView_column_count_portrait,
                        DEFAULT_COLUMNS_PORTRAIT);
                mColumnCountLandscape = typedArray.getInteger(
                        R.styleable.MaterialListView_column_count_landscape,
                        DEFAULT_COLUMNS_LANDSCAPE);
            }

            boolean isLandscape = isLandscape();
            mColumnCount = isLandscape ? mColumnCountLandscape : mColumnCountPortrait;
            setColumnLayout(mColumnCount);

            typedArray.recycle();
        }
    }

    public void remove(Card card) {
        if (card.isDismissible()) {
            ((IMaterialListAdapter) getAdapter()).remove(card, true);
        }
    }

    public void add(Card card) {
        ((IMaterialListAdapter) getAdapter()).add(card);
    }

    public void addAtStart(Card card) {
        ((IMaterialListAdapter) getAdapter()).addAtStart(card);
    }

    public void addAll(Card... cards) {
        ((IMaterialListAdapter) getAdapter()).addAll(cards);
    }

    public void addAll(Collection<Card> cards) {
        ((IMaterialListAdapter) getAdapter()).addAll(cards);
    }

    public void clear() {
        ((MaterialListAdapter) getAdapter()).clear();
    }

    public void clearAll() {
        ((MaterialListAdapter) getAdapter()).clearAll();
    }

    @Override
    public void setAdapter(final Adapter adapter) {
        final Adapter oldAdapter = getAdapter();
        if (oldAdapter != null) {
            oldAdapter.unregisterAdapterDataObserver(observer);
        }
        if (adapter != null) {
            if (adapter instanceof IMaterialListAdapter) {
                super.setAdapter(adapter);
                adapter.registerAdapterDataObserver(observer);
            } else {
                throw new IllegalArgumentException("The Adapter must implement IMaterialListAdapter");
            }
        }
    }

    public void setOnDismissCallback(OnDismissCallback callback) {
        mDismissCallback = callback;
    }

    @Override
    protected void onSizeChanged(final int w, final int h, final int oldw, final int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        boolean isLandscape = isLandscape();
        int newColumnCount = isLandscape ? mColumnCountLandscape : mColumnCountPortrait;
        if (mColumnCount != newColumnCount) {
            mColumnCount = newColumnCount;
            setColumnLayout(mColumnCount);
        }
    }

    private void setColumnLayout(int columnCount) {
        if (columnCount > 1) {
            setLayoutManager(new StaggeredGridLayoutManager(columnCount, StaggeredGridLayoutManager.VERTICAL));
        } else {
            setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        }

        Log.d(getClass().getSimpleName(), "ColumnCount=" + columnCount);
    }

    private boolean isLandscape() {
        return getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE;
    }

    private void checkIfEmpty() {
        if (emptyView != null) {
            emptyView.setVisibility(getAdapter().getItemCount() > 0 ? GONE : VISIBLE);
        }
    }

    public void setEmptyView(View emptyView) {
        this.emptyView = emptyView;
        checkIfEmpty();
    }

    public void addOnItemTouchListener(RecyclerItemClickListener.OnItemClickListener listener) {

        RecyclerItemClickListener itemClickListener = new RecyclerItemClickListener(getContext(), listener);

        itemClickListener.setRecyclerView(this);
        super.addOnItemTouchListener(itemClickListener);
    }

    public interface OnSwipeAnimation {
        /**
         *
         * @param position
         */
        void animate(int position);
    }
}
