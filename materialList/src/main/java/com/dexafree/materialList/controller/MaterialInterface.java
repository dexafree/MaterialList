package com.dexafree.materialList.controller;

import android.content.Context;
import android.view.MotionEvent;
import android.view.View;

import com.dexafree.materialList.MaterialAdapter;

public interface MaterialInterface {

    int getWidth();
    View getChildAt(int position);
    void getLocationOnScreen(int[] listViewCoords);
    int getPositionForView(View downView);
    MaterialAdapter getMaterialAdapter();
    void requestDisallowInterceptTouchEvent(boolean disallow);
    boolean onTouchEvent(MotionEvent cancelEvent);
    Context getContext();
    int getChildCount();

}
