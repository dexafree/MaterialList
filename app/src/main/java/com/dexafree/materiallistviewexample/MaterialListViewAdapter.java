package com.dexafree.materiallistviewexample;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;


public class MaterialListViewAdapter extends ArrayAdapter<Card> {

    private Context mContext;
    private LayoutInflater inflater;
    private ArrayList<Card> mCardList;

    public MaterialListViewAdapter(Context context, ArrayList<Card> cardList){
        super(context, R.layout.simple_card_layout, cardList);
        mContext = context;
        inflater = LayoutInflater.from(mContext);
        mCardList = cardList;
    }

    static class ViewHolder{
        TextView title;
        TextView description;
        ImageView image;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup){

        ViewHolder holder;

    	if(convertView == null){
    		holder = new ViewHolder();
    		convertView = inflater.inflate(R.layout.image_card_layout, null);
    		bindViews(holder, convertView);
    		convertView.setTag(holder);
    	} else {
    		holder = (ViewHolder) convertView.getTag();
    	}

    	setViewsContent(holder, position);

    	return convertView;
    }

    private void bindViews(ViewHolder holder, View convertView){
        holder.title = (TextView) convertView.findViewById(R.id.titleTextView);
        holder.description = (TextView) convertView.findViewById(R.id.descriptionTextView);
        holder.image = (ImageView) convertView.findViewById(R.id.imageView);
    }

    public void setViewsContent(ViewHolder holder, int position){
        Card card = getItem(position);
        holder.title.setText(card.getTitle());
        holder.description.setText(card.getDescription());
        holder.image.setImageBitmap(card.getBitmap());
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

}
