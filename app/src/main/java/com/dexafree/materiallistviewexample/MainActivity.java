package com.dexafree.materiallistviewexample;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.dexafree.materiallistviewexample.model.BasicCard;
import com.dexafree.materiallistviewexample.model.BigImageCard;
import com.dexafree.materiallistviewexample.model.Card;
import com.dexafree.materiallistviewexample.model.CardList;
import com.dexafree.materiallistviewexample.model.ImageButtonsCard;


public class MainActivity extends ActionBarActivity {

    private MaterialListView mListView;
    private CardList cardsList;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mContext = this;

        mListView = (MaterialListView) findViewById(R.id.material_listview);

        cardsList = new CardList();

        fillArray();

        MaterialListViewAdapter adapter = new MaterialListViewAdapter(this, cardsList);

        mListView.setMaterialListViewAdapter(adapter);
        mListView.setDefaultListeners();

    }

    private void fillArray(){
        for(int i=0;i<15;i++){
            Card card = getRandomCard(i);
            cardsList.add(card);
        }
    }

    private Card getRandomCard(final int position){
        String title = "Card number "+(position+1);
        String description = "Lorem ipsum dolor sit amet";
        Drawable icon = getResources().getDrawable(R.drawable.photo);

        int type = position % 3;

        Card card;

        switch (type){

            case 0:
                card = new BasicCard();
                card.setDescription(description);
                card.setTitle(title);
                card.setBitmap(icon);
                return card;

            case 1:
                card = new BigImageCard();
                card.setDescription(description);
                card.setTitle(title);
                card.setBitmap(icon);
                return card;

            default:
                card = new ImageButtonsCard();
                card.setDescription(description);
                card.setTitle(title);
                card.setBitmap(icon);
                ((ImageButtonsCard)card).setLeftButtonText("IZQUIERDA");
                ((ImageButtonsCard)card).setRightButtonText("DERECHA");
                ((ImageButtonsCard)card).setOnButtonPressListener(new ImageButtonsCard.OnButtonPressListener() {
                    @Override
                    public void onLeftTextPressed(TextView textView) {
                        Toast.makeText(mContext, "PULSADA IZQUIERDA EN NUMERO "+(position+1), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onRightTextPressed(TextView textView) {
                        Toast.makeText(mContext, "PULSADA DERECHA EN NUMERO "+(position+1), Toast.LENGTH_SHORT).show();
                    }
                });
                return card;

        }

    }
}
