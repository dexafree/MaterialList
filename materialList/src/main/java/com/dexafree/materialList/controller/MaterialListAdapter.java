package com.dexafree.materialList.controller;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dexafree.materialList.cards.Card;
import com.dexafree.materialList.cards.CardLayout;
import com.dexafree.materialList.view.MaterialListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class MaterialListAdapter extends RecyclerView.Adapter<MaterialListAdapter.ViewHolder> implements IMaterialListAdapter, Observer {
    private final List<Card> mCardList = new ArrayList<>();
    private final MaterialListView.OnSwipeAnimation mAnimation;

    public MaterialListAdapter(final MaterialListView.OnSwipeAnimation animation) {
        mAnimation = animation;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final CardLayout view;

        public ViewHolder(View v) {
            super(v);
            view = (CardLayout) v;
        }

        public void build(Card card) {
            view.build(card);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
        return new ViewHolder(LayoutInflater
                .from(parent.getContext())
                .inflate(viewType, parent, false));
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.build(mCardList.get(position));
    }

    @Override
    public int getItemCount() {
        return mCardList.size();
    }

    @Override
    public int getItemViewType(final int position) {
        return mCardList.get(position).getRenderer().getLayout();
    }

    public void addAtStart(Card card) {
        mCardList.add(0, card);
        card.getRenderer().addObserver(this);
        notifyDataSetChanged();
    }

    public void add(Card card) {
        mCardList.add(card);
        card.getRenderer().addObserver(this);
        notifyDataSetChanged();
    }

    public void addAll(Card... cards) {
        addAll(Arrays.asList(cards));
    }

    public void addAll(Collection<Card> cards) {
        for (Card card : cards) {
            add(card);
        }
    }

    public void remove(Card card, boolean animate) {
        if (card.isDismissible()) {
            card.getRenderer().deleteObserver(this);
            if (animate) {
                mAnimation.animate(getPosition(card));
            } else {
                mCardList.remove(card);
                notifyDataSetChanged();
            }
        }
    }

    public void clear() {
        for (int index = 0; index < mCardList.size(); index++) {
            remove(mCardList.get(index), false);
        }
    }

    @Override
    public void update(final Observable observable, final Object data) {
        notifyDataSetChanged();
    }

    public boolean isEmpty() {
        return mCardList.isEmpty();
    }

    public Card getCard(int position) {
        return mCardList.get(position);
    }

    public int getPosition(Card card) {
        return mCardList.indexOf(card);
    }
}
