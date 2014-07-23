package com.dexafree.materiallistviewexample;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.dexafree.materiallistviewexample.model.*;
import com.dexafree.materiallistviewexample.model.Card;

import java.util.ArrayList;


public class MaterialListViewAdapter extends BaseAdapter {

    private Context mContext;
    private ArrayList<Card> mCardList;
    private ArrayList<Class> mClassList = new ArrayList<Class>();

    public MaterialListViewAdapter(Context context, ArrayList<Card> cardList){
        mContext = context;
        mCardList = cardList;

        fillClassList();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup){

        if(convertView == null){
            boolean found = false;
            for(int i = 0;i<mClassList.size() && !found;i++){
                if(mClassList.get(i).isInstance(getItem(position))){
                    convertView = View.inflate(mContext, getItem(position).getLayout(), null);
                    found = true;
                }
            }
        }

        ((GridItemView)convertView).configureView(getItem(position));


    	return convertView;
    }

    private void fillClassList(){
        for(Card card : mCardList){
            Class cl = card.getClass();
            if(!mClassList.contains(cl)){
                mClassList.add(cl);
            }
        }
        Log.d("SIZE", mClassList.size()+"");
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

        for(int i=0;i<mClassList.size();i++){
            Class cl = mClassList.get(i);
            if(cl.isInstance(getItem(position))) return i;
        }

        return -1;
    }


    @Override
    public int getViewTypeCount (){
        return mClassList.size();
    }

    public void remove(Card card) {

        if (mCardList != null)
            mCardList.remove(card);

        notifyDataSetChanged();

    }

}
