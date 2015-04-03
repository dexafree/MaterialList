package com.dexafree.materialList.controller;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;

import com.dexafree.materialList.model.CardItemView;


// From http://stackoverflow.com/a/26196831/1610001
public class RecyclerItemClickListener implements RecyclerView.OnItemTouchListener{

    private RecyclerView mRecyclerView;

    public static interface OnItemClickListener {
        public void onItemClick(CardItemView view, int position);
        public void onItemLongClick(CardItemView view, int position);
    }

    private OnItemClickListener mListener;
    private GestureDetector mGestureDetector;

    public RecyclerItemClickListener(Context context, OnItemClickListener listener){
        mListener = listener;

        mGestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener(){
            @Override
            public boolean onSingleTapUp(MotionEvent e){
                return true;
            }

            @Override
            public void onLongPress(MotionEvent e){
                CardItemView childView = (CardItemView)mRecyclerView.findChildViewUnder(e.getX(), e.getY());

                if(childView != null && mListener != null){
                    mListener.onItemLongClick(childView, mRecyclerView.getChildPosition(childView));
                }
            }
        });
    }

    @Override
    public boolean onInterceptTouchEvent(RecyclerView view, MotionEvent e){
        CardItemView childView = (CardItemView)view.findChildViewUnder(e.getX(), e.getY());

        if(childView != null && mListener != null && mGestureDetector.onTouchEvent(e)){
            mListener.onItemClick(childView, view.getChildPosition(childView));
        }

        return false;
    }

    @Override
    public void onTouchEvent(RecyclerView view, MotionEvent motionEvent){}

    public void setRecyclerView(RecyclerView recyclerView){
        mRecyclerView = recyclerView;
    }
}