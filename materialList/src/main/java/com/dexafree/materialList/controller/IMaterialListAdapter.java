package com.dexafree.materialList.controller;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.dexafree.materialList.cards.Card;

import java.util.Collection;

public interface IMaterialListAdapter {
	void add(@NonNull final Card card);

	void addAtStart(@NonNull final Card card);

	void addAll(@NonNull final Card... cards);

	void addAll(@NonNull final Collection<Card> cards);

	void remove(@NonNull final Card card, boolean withAnimation);

	boolean isEmpty();

	@Nullable
	Card getCard(int position);

	int getPosition(@NonNull final Card card);
}
