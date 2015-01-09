package com.dexafree.materialList.controller;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.dexafree.materialList.cards.model.Card;
import com.dexafree.materialList.cards.view.BaseCardItemView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;


public class MaterialListViewAdapter extends ArrayAdapter<Card> {
	private ArrayList<Class> mClassList = new ArrayList<Class>();
    private ArrayList<Class> mDeletedList = new ArrayList<Class>();

    public MaterialListViewAdapter(Context context){
        super(context, android.R.layout.simple_list_item_1);
	}

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup){
        Card card = getItem(position);

        if(convertView == null || !convertView.getTag().getClass().isInstance(card)) {
            for(Class<?> classType : mClassList) {
                if(classType.isInstance(card)){
                    convertView = View.inflate(getContext(), card.getLayout(), null);
                    convertView.setTag(card);
                    break;
                }
            }
        }

        if (((BaseCardItemView)convertView) != null) {
            ((BaseCardItemView)convertView).build(card);
        }

        return convertView;
    }

    @Override
    public void add(Card card) {
        super.add(card);
        Class cl = card.getClass();
        if(!mClassList.contains(cl)){
            mClassList.add(cl);
        }
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    @Override
    public void addAll(Collection<? extends Card> collection) {
        super.addAll(collection);
        for (Card card : collection) {
			Class cl = card.getClass();
            if(!mClassList.contains(cl)){
                mClassList.add(cl);
            }
        }
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    @Override
    public void addAll(Card... items) {
        super.addAll(items);
        List<Card> cards = Arrays.asList(items);
        for (Card card : cards) {
            Class cl = card.getClass();
            if(!mClassList.contains(cl)){
                mClassList.add(cl);
            }
        }
    }

    @Override
    public void insert(Card card, int index) {
        super.insert(card, index);
        Class cl = card.getClass();
        if(!mClassList.contains(cl)){
            mClassList.add(cl);
        }
    }

    public void remove(Card card) {
        super.remove(card);
        if(!mDeletedList.contains(card.getClass())){
            mDeletedList.add(card.getClass());
        }
    }

    @Override
    public int getItemViewType (int position){
		if(position > -1 && position < getCount()) {
			for (int i = 0; i < mClassList.size(); i++) {
				Class cl = mClassList.get(i);
				if (cl.isInstance(getItem(position))) return i;
			}
		}
        return -1;
    }

    @Override
    public int getViewTypeCount (){
        // BugFix: Can't have a viewTypCount < 1 (Exception)
        return mClassList.isEmpty() ? 1 : mClassList.size();
    }
}
