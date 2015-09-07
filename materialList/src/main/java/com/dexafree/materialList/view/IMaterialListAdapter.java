package com.dexafree.materialList.view;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.dexafree.materialList.card.Card;

import java.util.Collection;

/**
 *
 */
public interface IMaterialListAdapter {
    /**
     * Add a Card.
     *
     * @param card
     *         to add.
     */
    void add(@NonNull final Card card);

    /**
     * Add a Card at the start.
     *
     * @param card
     *         to add at the start.
     */
    void addAtStart(@NonNull final Card card);

    /**
     * Add all Cards.
     *
     * @param cards
     *         to add.
     */
    void addAll(@NonNull final Card... cards);

    /**
     * Add all Cards.
     *
     * @param cards
     *         to add.
     */
    void addAll(@NonNull final Collection<Card> cards);

    /**
     * Remove a Card with or without an animation.
     *
     * @param card
     *         to remove.
     * @param animate
     *         {@code true} to animate the remove process or {@code false} otherwise.
     */
    void remove(@NonNull final Card card, boolean animate);

    /**
     * Is the list empty?
     *
     * @return {@code true} if the list is empty or {@code false} otherwise.
     */
    boolean isEmpty();

    /**
     * Clears the list from all Cards (only if dismissable).
     */
    void clear();

    /**
     * Clears the list from all Cards (even if they are not dismissable).
     */
    void clearAll();

    /**
     * Get a Card at the specified position.
     *
     * @param position
     *         of the Card.
     * @return the Card or {@code null} if the position is outside of the list range.
     */
    @Nullable
    Card getCard(int position);

    /**
     * Get the position of a specified Card.
     *
     * @param card
     *         to get the position of.
     * @return the position.
     */
    int getPosition(@NonNull final Card card);
}
