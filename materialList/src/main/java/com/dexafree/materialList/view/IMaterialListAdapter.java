package com.dexafree.materialList.view;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.dexafree.materialList.cards.Card;

import java.util.Collection;

/**
 *
 */
public interface IMaterialListAdapter {
    /**
     *
     * @param card
     */
	void add(@NonNull final Card card);

    /**
     *
     * @param card
     */
	void addAtStart(@NonNull final Card card);

    /**
     *
     * @param cards
     */
	void addAll(@NonNull final Card... cards);

    /**
     *
     * @param cards
     */
	void addAll(@NonNull final Collection<Card> cards);

    /**
     *
     * @param card
     * @param withAnimation
     */
	void remove(@NonNull final Card card, boolean withAnimation);

    /**
     *
     * @return
     */
	boolean isEmpty();

    /**
     *
     */
	void clear();

    /**
     *
     * @param position
     * @return
     */
	@Nullable
	Card getCard(int position);

    /**
     *
     * @param card
     * @return
     */
	int getPosition(@NonNull final Card card);
}
