package com.dexafree.materiallistviewexample;

import android.graphics.drawable.Drawable;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;


public class MainActivity extends ActionBarActivity {

    private MaterialListView mListView;
    private ArrayList<Card> cardsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mListView = (MaterialListView) findViewById(R.id.material_listview);

        cardsList = new ArrayList<Card>();

        fillArray();

        MaterialListViewAdapter adapter = new MaterialListViewAdapter(this, cardsList);

        mListView.setMaterialListViewAdapter(adapter);
        mListView.setDefaultListeners();

    }

    private void fillArray(){
        for(int i=0;i<10;i++){
            String title = "Card number "+(i+1);
            String description = "Lorem ipsum dolor sit amet";
            Drawable icon = getResources().getDrawable(R.drawable.photo);
            Card card = new Card(title, description, icon);
            cardsList.add(card);
        }
    }
}
