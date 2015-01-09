package com.dexafree.materialList.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ListAdapter;

import com.dexafree.materialList.controller.MaterialListViewAdapter;
import com.dexafree.materialList.controller.OnDismissCallback;
import com.dexafree.materialList.cards.model.Card;

import java.util.Collection;

/**
 * This interface is only necessary for the SwipeDismissListener.
 */
public interface IMaterialView {
	Context getContext();

	MaterialListViewAdapter getAdapter();

	void setAdapter(ListAdapter adapter);

	int getWidth();

	int getChildCount();

	void getLocationOnScreen(int[] location);

	View getChildAt(int position);

	void requestDisallowInterceptTouchEvent(boolean enable);

	boolean onTouchEvent(MotionEvent event);

	void setCardAnimation(CardAnimation animation);

	void setOnDismissCallback(OnDismissCallback callback);

	void add(Card card);

	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	void addAll(Card... cards);

	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	void addAll(Collection<Card> cards);

	Card getCard(int position);

	int getPosition(Card card);

	void notifyDataSetChanged();

	public enum CardAnimation {
		ALPHA_IN, SCALE_IN, SWING_BOTTOM_IN, SWING_LEFT_IN, SWING_RIGHT_IN
	}
}
