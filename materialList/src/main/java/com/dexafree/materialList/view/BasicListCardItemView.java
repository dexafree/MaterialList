package com.dexafree.materialList.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.dexafree.materialList.R;
import com.dexafree.materialList.model.BasicListCard;
import com.nhaarman.listviewanimations.itemmanipulation.DynamicListView;

import java.util.List;

/**
 * Created by Fabio on 19.12.2014.
 */
public class BasicListCardItemView extends GridItemView<BasicListCard> {
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
    public void configureView(BasicListCard newItem) {
        setTitle(newItem.getTitle());
        setDescription(newItem.getDescription());
        setItems(newItem.getItems());
        ((DynamicListView) findViewById(R.id.listView)).setOnItemClickListener(newItem.getOnItemClickListener());
    }

    public void setTitle(String title){
        ((TextView)findViewById(R.id.titleTextView)).setText(title);
    }

    public void setDescription(String description){
        ((TextView)findViewById(R.id.descriptionTextView)).setText(description);
    }

    public void setItems(List<String> items) {
        DynamicListView listView = (DynamicListView) findViewById(R.id.listView);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_checked, items);
        listView.setAdapter(adapter);

        // Calculate the height of the ListView to display all items
        int totalHeight = 0;
        for (int i = 0; i < adapter.getCount(); i++)
        {
            View item = adapter.getView(i, null, listView);
            item.measure(View.MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED),
                    View.MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED));
            totalHeight += item.getMeasuredHeight();
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight()*adapter.getCount() - 1);

        listView.setLayoutParams(params);
        listView.requestLayout();
    }
}
