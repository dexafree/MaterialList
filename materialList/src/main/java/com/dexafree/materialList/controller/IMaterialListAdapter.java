package com.dexafree.materialList.controller;

import com.dexafree.materialList.model.Card;

import java.util.Collection;

public interface IMaterialListAdapter {
	void add(Card card);

	void addAtStart(Card card);

	void addAll(Card... cards);

	void addAll(Collection<Card> cards);

	void remove(Card card, boolean withAnimation);

	boolean isEmpty();

	Card getCard(int position);

	int getPosition(Card card);
}
