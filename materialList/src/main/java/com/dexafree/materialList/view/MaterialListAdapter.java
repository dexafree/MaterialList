package com.dexafree.materialList.view;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dexafree.materialList.card.Card;
import com.dexafree.materialList.card.CardLayout;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class MaterialListAdapter extends RecyclerView.Adapter<MaterialListAdapter.ViewHolder> implements IMaterialListAdapter, Observer {
    private final List<Card> mCardList = new ArrayList<>();
    private final MaterialListView.OnSwipeAnimation mAnimation;

    public MaterialListAdapter(@NonNull final MaterialListView.OnSwipeAnimation animation) {
        mAnimation = animation;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final CardLayout view;

        public ViewHolder(@NonNull final View v) {
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
        return mCardList.get(position).getConfig().getLayout();
    }

    public void addAtStart(@NonNull final Card card) {
        mCardList.add(0, card);
        card.getConfig().addObserver(this);
        notifyDataSetChanged();
    }

    public void add(@NonNull final Card card) {
        mCardList.add(card);
        card.getConfig().addObserver(this);
        notifyDataSetChanged();
    }

    public void addAll(@NonNull final Card... cards) {
        addAll(Arrays.asList(cards));
    }

    public void addAll(@NonNull final Collection<Card> cards) {
        for (Card card : cards) {
            add(card);
        }
    }

    public void remove(@NonNull final Card card, boolean animate) {
        if (card.isDismissible()) {
            card.getConfig().deleteObserver(this);
            if (animate) {
                mAnimation.animate(getPosition(card));
            } else {
                mCardList.remove(card);
                notifyDataSetChanged();
            }
        }
    }

    public void clearAll() {
        while(!mCardList.isEmpty()) {
            final Card card = mCardList.get(0);
            card.setDismissible(true);
            remove(card, false);
        }
    }

    public void clear() {
        for (int index = 0; index < mCardList.size();) {
            final Card card = mCardList.get(index);
            if(!card.isDismissible()) {
                index++;
            }
            remove(card, false);
        }
    }

    public boolean isEmpty() {
        return mCardList.isEmpty();
    }

    public Card getCard(int position) {
        if(position > 0 && position < mCardList.size()) {
            return mCardList.get(position);
        }
        return null;
    }

    public int getPosition(@NonNull Card card) {
        return mCardList.indexOf(card);
    }

    @Override
    public void update(final Observable observable, final Object data) {
        if(data == null) {
            notifyDataSetChanged();
        }
    }
}
