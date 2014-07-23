package com.dexafree.materiallistviewexample;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.dexafree.materiallistviewexample.model.*;
import com.dexafree.materiallistviewexample.model.Card;

import java.util.ArrayList;


public class MaterialListViewAdapter extends BaseAdapter {

    private final static int BASIC_CARD = 0;
    private final static int BIG_IMAGE_CARD = 1;
    private final static int IMAGE_BUTTON_CARD = 2;


    private Context mContext;
    private ArrayList<Card> mCardList;

    public MaterialListViewAdapter(Context context, ArrayList<Card> cardList){
        mContext = context;
        mCardList = cardList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup){

        if(convertView == null){
            switch (getItemViewType(position)){
                case BASIC_CARD:
                    convertView = View.inflate(mContext, R.layout.simple_card_layout, null);
                    break;
                case BIG_IMAGE_CARD:
                    convertView = View.inflate(mContext, R.layout.image_card_layout, null);
                    break;
                default:
                    convertView = View.inflate(mContext, R.layout.image_card_layout_with_buttons, null);
                    break;
            }
        }

        ((GridItemView)convertView).configureView(getItem(position));


    	return convertView;
    }


    @Override
    public int getCount() {
    	return mCardList.size();
    }

    @Override
    public Card getItem(int i){
    	return mCardList.get(i);
    }

    @Override
    public long getItemId(int id){
    	return 0;
    }

    @Override
    public int getItemViewType (int position){
        if(getItem(position) instanceof BasicCard) return BASIC_CARD;
        if(getItem(position) instanceof BigImageCard) return BIG_IMAGE_CARD;
        if(getItem(position) instanceof ImageButtonsCard) return IMAGE_BUTTON_CARD;

        return -1;
    }


    @Override
    public int getViewTypeCount (){
        return 3;
    }

    public void remove(Card card) {

        if (mCardList != null)
            mCardList.remove(card);

        notifyDataSetChanged();

    }

}
