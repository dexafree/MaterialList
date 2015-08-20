package com.dexafree.materialList.card.provider;

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
import com.dexafree.materialList.card.Card;

public class BasicListCardProvider extends TextCardProvider<BasicListCardProvider> {
    private AdapterView.OnItemClickListener mOnItemSelectedListener;
    private ListAdapter mAdapter;
    private boolean dividerVisible;

    /**
     * Set the adapter of the list.
     *
     * @param adapter
     *         of the list.
     * @return the renderer.
     */
    public BasicListCardProvider setAdapter(ListAdapter adapter) {
        mAdapter = adapter;
        notifyDataSetChanged();
        return this;
    }

    /**
     * Get the adapter of the list.
     *
     * @return the adapter.
     */
    public ListAdapter getAdapter() {
        return mAdapter;
    }

    /**
     * Get the visibility state of the list divider.
     *
     * @return the visibility state.
     */
    public boolean isDividerVisible() {
        return dividerVisible;
    }

    /**
     * Set the visibility state of the list divider.
     *
     * @param dividerVisible
     *         to set.
     * @return the renderer.
     */
    public BasicListCardProvider setDividerVisible(final boolean dividerVisible) {
        this.dividerVisible = dividerVisible;
        notifyDataSetChanged();
        return this;
    }

    /**
     * Get the listener for on item click events.
     *
     * @return the listener.
     */
    public AdapterView.OnItemClickListener getOnItemClickListener() {
        return mOnItemSelectedListener;
    }

    /**
     * Set the listener for on item click events.
     *
     * @param listener
     *         to set.
     * @return the renderer.
     */
    public BasicListCardProvider setOnItemClickListener(AdapterView.OnItemClickListener listener) {
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
            final ListView listView = (ListView) view.findViewById(R.id.listView);
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
