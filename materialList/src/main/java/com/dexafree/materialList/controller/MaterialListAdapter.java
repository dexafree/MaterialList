package com.dexafree.materialList.controller;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dexafree.materialList.events.BusProvider;
import com.dexafree.materialList.model.Card;
import com.dexafree.materialList.model.CardItemView;
import com.dexafree.materialList.view.MaterialListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class MaterialListAdapter extends RecyclerView.Adapter<MaterialListAdapter.ViewHolder> implements IMaterialListAdapter {
	private final List<Card> mCardList = new ArrayList<>();

	public static class ViewHolder<T extends Card> extends RecyclerView.ViewHolder {
		private final CardItemView<T> view;

		public ViewHolder(View v) {
			super(v);
			view = (CardItemView<T>) v;
		}

		public void build(T card) {
			view.build(card);
		}

    }

	@Override
	public ViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
		final View view = LayoutInflater.from(parent.getContext()).inflate(viewType, parent, false);
		return new ViewHolder(view);
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
		return mCardList.get(position).getLayout();
	}

	public void addAtStart(Card card){
		mCardList.add(0, card);
		notifyDataSetChanged();
	}

	public void add(Card card) {
		mCardList.add(card);
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

	public void remove(Card card, boolean withAnimation) {
		if (card.isDismissible()) {
			if (withAnimation) {
				BusProvider.dismiss(card);
			} else {
				mCardList.remove(card);
				notifyDataSetChanged();
			}
		}
	}

    public void clear(){
        mCardList.clear();
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
