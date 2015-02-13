package com.dexafree.materialList.cards;

import android.content.Context;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import com.dexafree.materialList.R;

public class BasicListCard extends SimpleCard {
    private AdapterView.OnItemClickListener mOnItemSelectedListener;
    private ListAdapter mAdapter;
    private boolean dividerVisible;

    public BasicListCard(final Context context) {
        super(context);
    }

    public void setAdapter(ListAdapter adapter) {
        mAdapter = adapter;
    }

    public ListAdapter getAdapter() {
        return mAdapter;
    }

    public boolean isDividerVisible() {
        return dividerVisible;
    }

    public void setDividerVisible(final boolean dividerVisible) {
        this.dividerVisible = dividerVisible;
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
}
