package com.dexafree.materialList.model;

import android.widget.AdapterView;

import com.dexafree.materialList.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Fabio on 19.12.2014.
 */
public class BasicListCard extends Card implements Iterable<String> {
    private final List<String> mItemList = new ArrayList<String>();
    private AdapterView.OnItemClickListener mOnItemSelectedListener;

    public void addItem(String item) {
        mItemList.add(item);
    }

    public void addAllItems(String... items) {
        mItemList.addAll(Arrays.asList(items));
    }

    public void addAllItems(Collection<String> items) {
        mItemList.addAll(items);
    }

    public int getPosition(String item) {
        return mItemList.indexOf(item);
    }

    public void remove(String item) {
        mItemList.remove(item);
    }

    public int getCount() {
        return mItemList.size();
    }

    public List<String> getItems() {
        return new ArrayList<String>(mItemList);
    }

    public AdapterView.OnItemClickListener getOnItemClickListener() {
        return mOnItemSelectedListener;
    }

    public void setOnItemClickListener(AdapterView.OnItemClickListener listener) {
        this.mOnItemSelectedListener = listener;
    }

    @Override
    public int getLayout() {
        return R.layout.material_basic_list_card_layout;
    }

    @Override
    public Iterator<String> iterator() {
        return mItemList.iterator();
    }
}
