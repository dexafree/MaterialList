package com.dexafree.materialList.view;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.AttributeSet;
import android.view.View;

import com.dexafree.materialList.R;
import com.dexafree.materialList.card.Card;
import com.dexafree.materialList.listeners.OnDismissCallback;
import com.dexafree.materialList.listeners.RecyclerItemClickListener;
import com.dexafree.materialList.listeners.SwipeDismissRecyclerViewTouchListener;


public class MaterialListView extends RecyclerView {

    private static final int DEFAULT_COLUMNS_PORTRAIT = 1;
    private static final int DEFAULT_COLUMNS_LANDSCAPE = 2;

    private OnDismissCallback mDismissCallback;
    private SwipeDismissRecyclerViewTouchListener mDismissListener;
    private View mEmptyView;
    private int mColumnCount;
    private int mColumnCountLandscape = DEFAULT_COLUMNS_LANDSCAPE;
    private int mColumnCountPortrait = DEFAULT_COLUMNS_PORTRAIT;
    private final AdapterDataObserver mEmptyViewObserver = new AdapterDataObserver() {
        @Override public void onChanged() {
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

        mDismissListener = new SwipeDismissRecyclerViewTouchListener(this,
                new SwipeDismissRecyclerViewTouchListener.DismissCallbacks() {
                    @Override
                    public boolean canDismiss(final int position) {
                        final Card card = getAdapter().getCard(position);
                        return card != null && card.isDismissible();
                    }

                    @Override
                    public void onDismiss(final RecyclerView recyclerView,
                                          final int[] reverseSortedPositions) {
                        for (int reverseSortedPosition : reverseSortedPositions) {
                            final Card card = getAdapter().getCard(reverseSortedPosition);
                            if (card != null) {
                                getAdapter().remove(card, false);
                                if (mDismissCallback != null) {
                                    mDismissCallback.onDismiss(card, reverseSortedPosition);
                                }
                            }
                        }
                    }
                });
        setOnTouchListener(mDismissListener);
        setOnScrollListener(mDismissListener.makeScrollListener());

        setAdapter(new MaterialListAdapter(new OnSwipeAnimation() {
            @Override
            public void animate(final int position) {
                RecyclerView.ViewHolder holder = findViewHolderForPosition(position);
                if (holder != null) {
                    mDismissListener.dismissCard(holder.itemView, position);
                }
            }
        }, new OnAdapterItemsChanged() {
            @Override
            public void onAddItem(int position, boolean scroll) {
                if (scroll) {
                    scrollToPosition(position);
                }
                checkIfEmpty();
            }

            @Override
            public void onRemoveItem() {
                checkIfEmpty();
            }
        }));

        if (attrs != null) {
            // get the number of columns
            TypedArray typedArray = context.obtainStyledAttributes(attrs,
                    R.styleable.MaterialListView, defStyle, 0);

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

    public <T extends MaterialListAdapter> void setAdapter(@NonNull final T adapter) {
        final RecyclerView.Adapter oldAdapter = getAdapter();
        if (oldAdapter != null) {
            oldAdapter.unregisterAdapterDataObserver(mEmptyViewObserver);
        }
        super.setAdapter(adapter);
        adapter.registerAdapterDataObserver(mEmptyViewObserver);
    }

    @Override
    public MaterialListAdapter getAdapter() {
        return (MaterialListAdapter) super.getAdapter();
    }

    public int getColumnCount() {
        return mColumnCount;
    }

    public void setColumnCount(int columnCount) {
        mColumnCount = columnCount;
    }

    public int getColumnCountLandscape() {
        return mColumnCountLandscape;
    }

    public void setColumnCountLandscape(int columnCountLandscape) {
        mColumnCountLandscape = columnCountLandscape;
    }

    public int getColumnCountPortrait() {
        return mColumnCountPortrait;
    }

    public void setColumnCountPortrait(int columnCountPortrait) {
        mColumnCountPortrait = columnCountPortrait;
    }

    public void setOnDismissCallback(OnDismissCallback callback) {
        mDismissCallback = callback;
    }

    public void setEmptyView(View emptyView) {
        mEmptyView = emptyView;
        checkIfEmpty();
    }

    public void addOnItemTouchListener(RecyclerItemClickListener.OnItemClickListener listener) {
        RecyclerItemClickListener itemClickListener =
                new RecyclerItemClickListener(getContext(), listener);
        itemClickListener.setRecyclerView(this);
        addOnItemTouchListener(itemClickListener);
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
            setLayoutManager(new StaggeredGridLayoutManager(columnCount,
                    StaggeredGridLayoutManager.VERTICAL));
        } else {
            setLayoutManager(new LinearLayoutManager(getContext(),
                    LinearLayoutManager.VERTICAL, false));
        }
    }

    private boolean isLandscape() {
        return getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE;
    }

    private void checkIfEmpty() {
        if (mEmptyView != null) {
            mEmptyView.setVisibility(getAdapter().isEmpty() ? VISIBLE : GONE);
            setVisibility(getAdapter().isEmpty() ? GONE : VISIBLE);
        }
    }

    /**
     *
     */
    interface OnSwipeAnimation {
        /**
         *
         * @param position
         */
        void animate(final int position);
    }

    /**
     *
     */
    interface OnAdapterItemsChanged {
        /**
         *
         * @param position
         * @param scroll
         */
        void onAddItem(final int position, boolean scroll);

        /**
         *
         */
        void onRemoveItem();
    }
}
