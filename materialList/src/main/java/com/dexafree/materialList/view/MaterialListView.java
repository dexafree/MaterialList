package com.dexafree.materialList.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import com.dexafree.materialList.MaterialListViewAdapter;
import com.dexafree.materialList.controller.OnDismissCallback;
import com.dexafree.materialList.controller.SwipeDismissListener;
import com.dexafree.materialList.events.BusProvider;
import com.dexafree.materialList.events.DismissEvent;
import com.dexafree.materialList.model.Card;
import com.dexafree.materialList.model.CardList;
import com.squareup.otto.Subscribe;


public class MaterialListView extends ListView {

    @Subscribe
    public void onCardDismiss(DismissEvent event){
        Card dismissedCard = event.getDismissedCard();
        View dismissedCardView = event.getCardView();

        int cardPosition = mAdapter.getCardList().indexOf(dismissedCard);

        Log.d("POSITION", cardPosition+"");

        mListener.dismissCard(dismissedCardView, cardPosition);
        //mAdapter.notifyDataSetChanged();
    }

    private MaterialListViewAdapter mAdapter;
    private OnDismissCallback mDismissCallback;
    private SwipeDismissListener mListener;

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
        BusProvider.getInstance().register(this);
        mAdapter = adapter;
        setAdapter (mAdapter);
        setDivider (null);
        setDividerHeight (8);
        setDefaultListeners();

    }

    public void addCardsToExistingAdapter(CardList newCards){
        mAdapter.addCardsToExistingSet(newCards);
    }

    public MaterialListViewAdapter getMaterialListViewAdapter(){
        return mAdapter;
    }

    public void setOnDismissCallback (OnDismissCallback callback) {

        mDismissCallback = callback;

    }

    private void setDefaultListeners () {

        mListener =
                new SwipeDismissListener(
                        this,
                        new SwipeDismissListener.OnDismissCallback () {

                            @Override
                            public void onDismiss (MaterialListView listView, int[] reverseSortedPositions) {

                                for (int position : reverseSortedPositions) {

                                    if(position < listView.getMaterialListViewAdapter().getCount()) {

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

        setOnTouchListener (mListener);
        setOnScrollListener (mListener.makeScrollListener());
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        BusProvider.getInstance().unregister(this);
    }
}
