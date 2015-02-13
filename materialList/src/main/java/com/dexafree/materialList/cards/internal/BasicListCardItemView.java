package com.dexafree.materialList.cards.internal;

import android.content.Context;
import android.database.DataSetObserver;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;
import com.dexafree.materialList.cards.BasicListCard;
import com.dexafree.materialList.R;
import com.nhaarman.listviewanimations.itemmanipulation.DynamicListView;

public class BasicListCardItemView extends BaseTextCardItemView<BasicListCard> {
    public BasicListCardItemView(Context context) {
        super(context);
    }

    public BasicListCardItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public BasicListCardItemView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public void build(final BasicListCard newItem) {
        super.build(newItem);
        if (newItem.getAdapter() != null) {
            final DynamicListView listView = (DynamicListView) findViewById(R.id.listView);
            if (!newItem.isDividerVisible()) {
                listView.setDivider(new ColorDrawable(Color.TRANSPARENT));
            }
            listView.setAdapter(newItem.getAdapter());
            listView.getAdapter().registerDataSetObserver(new DataSetObserver() {
                @Override
                public void onChanged() {
                    super.onChanged();
                    calculateListHeight(listView);
                }
            });
            listView.setOnItemClickListener(newItem.getOnItemClickListener());
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
                    View.MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED),
                    View.MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED)
            );
            totalHeight += item.getMeasuredHeight();
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + listView.getDividerHeight() * (adapter.getCount() - 1);

        listView.setLayoutParams(params);
        listView.requestLayout();
    }
}
