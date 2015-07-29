package com.dexafree.materialList.cards.renderer;

import android.content.Context;
import android.database.DataSetObserver;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.dexafree.materialList.R;
import com.dexafree.materialList.cards.Card;
import com.nhaarman.listviewanimations.itemmanipulation.DynamicListView;

/**
 * Created by Fabio on 29.07.2015.
 */
public class BasicListCardRenderer extends TextCardRenderer<BasicListCardRenderer> {
    private AdapterView.OnItemClickListener mOnItemSelectedListener;
    private ListAdapter mAdapter;
    private boolean dividerVisible;

    /**
     * @param context
     */
    public BasicListCardRenderer(@NonNull final Context context) {
        super(context);
    }

    /**
     *
     * @param adapter
     * @return
     */
    public BasicListCardRenderer setAdapter(ListAdapter adapter) {
        mAdapter = adapter;
        notifyDataSetChanged();
        return this;
    }

    /**
     *
     * @return
     */
    public ListAdapter getAdapter() {
        return mAdapter;
    }

    /**
     *
     * @return
     */
    public boolean isDividerVisible() {
        return dividerVisible;
    }

    /**
     *
     * @param dividerVisible
     * @return
     */
    public BasicListCardRenderer setDividerVisible(final boolean dividerVisible) {
        this.dividerVisible = dividerVisible;
        notifyDataSetChanged();
        return this;
    }

    /**
     *
     * @return
     */
    public AdapterView.OnItemClickListener getOnItemClickListener() {
        return mOnItemSelectedListener;
    }

    /**
     *
     * @param listener
     * @return
     */
    public BasicListCardRenderer setOnItemClickListener(AdapterView.OnItemClickListener listener) {
        this.mOnItemSelectedListener = listener;
        return this;
    }

    @Override
    public int getLayout() {
        return R.layout.material_basic_list_card_layout;
    }

    @Override
    public void render(@NonNull final View view, @NonNull final Card card) {
        super.render(view, card);
        if (getAdapter() != null) {
            final DynamicListView listView = (DynamicListView) view.findViewById(R.id.listView);
            if (!isDividerVisible()) {
                listView.setDivider(new ColorDrawable(Color.TRANSPARENT));
            }
            listView.setAdapter(getAdapter());
            listView.getAdapter().registerDataSetObserver(new DataSetObserver() {
                @Override
                public void onChanged() {
                    super.onChanged();
                    calculateListHeight(listView);
                }
            });
            listView.setOnItemClickListener(getOnItemClickListener());
            calculateListHeight(listView);
        }
    }

    private void calculateListHeight(ListView listView) {
        final ListAdapter adapter = listView.getAdapter();

        // Calculate the height of the ListView to display all items
        int totalHeight = 0;
        for (int i = 0; i < adapter.getCount(); i++) {
            View item = adapter.getView(i, null, listView);
            item.measure(
                    View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED),
                    View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED)
            );
            totalHeight += item.getMeasuredHeight();
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + listView.getDividerHeight() * (adapter.getCount() - 1);

        listView.setLayoutParams(params);
        listView.requestLayout();
    }
}
