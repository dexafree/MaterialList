package com.dexafree.materialList.card.provider;

import android.database.DataSetObserver;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.dexafree.materialList.R;
import com.dexafree.materialList.card.Card;
import com.dexafree.materialList.card.CardProvider;

public class ListCardProvider extends CardProvider<ListCardProvider> {
    private AdapterView.OnItemClickListener mOnItemSelectedListener;
    private ListAdapter mAdapter;

    @Override
    protected void onCreated() {
        super.onCreated();
        setLayout(R.layout.material_list_card_layout);
    }

    /**
     * Set the adapter of the list.
     *
     * @param adapter
     *         of the list.
     * @return the renderer.
     */
    public ListCardProvider setAdapter(ListAdapter adapter) {
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
    public ListCardProvider setOnItemClickListener(AdapterView.OnItemClickListener listener) {
        this.mOnItemSelectedListener = listener;
        return this;
    }

    @Override
    public void render(@NonNull final View view, @NonNull final Card card) {
        super.render(view, card);
        if (getAdapter() != null) {
            final ListView listView = (ListView) view.findViewById(R.id.listView);
            listView.setScrollbarFadingEnabled(true);
            listView.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    return false; // That the gesture detection is correct
                }
            });
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
        params.height = totalHeight;

        listView.setLayoutParams(params);
        listView.requestLayout();
    }
}
