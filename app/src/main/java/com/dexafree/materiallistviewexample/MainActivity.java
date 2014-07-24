package com.dexafree.materiallistviewexample;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.dexafree.materiallistviewexample.controller.OnButtonPressListener;
import com.dexafree.materiallistviewexample.controller.OnDismissCallback;
import com.dexafree.materiallistviewexample.model.BasicButtonsCard;
import com.dexafree.materiallistviewexample.model.SmallImageCard;
import com.dexafree.materiallistviewexample.model.BasicImageButtonsCard;
import com.dexafree.materiallistviewexample.model.BigImageButtonsCard;
import com.dexafree.materiallistviewexample.model.BigImageCard;
import com.dexafree.materiallistviewexample.model.Card;
import com.dexafree.materiallistviewexample.model.CardList;
import com.dexafree.materiallistviewexample.view.MaterialListView;


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

        mListView.setOnDismissCallback(new OnDismissCallback() {
            @Override
            public void onDismiss(Card card, int position) {
                //Toast.makeText(mContext, "CARD NUMBER "+position+" dismissed", //Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void fillArray(){
        for(int i=0;i<25;i++){
            Card card = getRandomCard(i);
            cardsList.add(card);
        }
    }

    private Card getRandomCard(final int position){
        String title = "Card number "+(position+1);
        String description = "Lorem ipsum dolor sit amet";

        int type = position % 5;

        Card card;
        Drawable icon;

        switch (type){

            case 0:
                card = new SmallImageCard();
                card.setDescription(description);
                card.setTitle(title);
                icon = getResources().getDrawable(R.drawable.ic_launcher);
                card.setBitmap(icon);
                return card;

            case 1:
                card = new BigImageCard();
                card.setDescription(description);
                card.setTitle(title);
                icon = getResources().getDrawable(R.drawable.photo);
                card.setBitmap(icon);
                card.setCanDismiss(false);
                return card;

            case 2:
                card = new BasicImageButtonsCard();
                card.setDescription(description);
                card.setTitle(title);
                icon = getResources().getDrawable(R.drawable.dog);
                card.setBitmap(icon);
                ((BasicImageButtonsCard)card).setLeftButtonText("IZQUIERDA");
                ((BasicImageButtonsCard)card).setRightButtonText("DERECHA");
                ((BasicImageButtonsCard)card).setOnButtonPressListener(new OnButtonPressListener() {
                    @Override
                    public void onLeftTextPressed(TextView textView) {
                        //Toast.makeText(mContext, "PULSADA IZQUIERDA EN NUMERO "+(position+1), //Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onRightTextPressed(TextView textView) {
                        //Toast.makeText(mContext, "PULSADA DERECHA EN NUMERO "+(position+1), //Toast.LENGTH_SHORT).show();
                    }
                });
                return card;

            case 3:
                card = new BasicButtonsCard();
                card.setDescription(description);
                card.setTitle(title);
                ((BasicButtonsCard)card).setLeftButtonText("IZQUIERDA");
                ((BasicButtonsCard)card).setRightButtonText("DERECHA");
                ((BasicButtonsCard)card).setOnButtonPressListener(new OnButtonPressListener() {
                    @Override
                    public void onLeftTextPressed(TextView textView) {
                        //Toast.makeText(mContext, "PULSADA IZQUIERDA EN NUMERO "+(position+1), //Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onRightTextPressed(TextView textView) {
                        //Toast.makeText(mContext, "PULSADA DERECHA EN NUMERO "+(position+1), //Toast.LENGTH_SHORT).show();
                    }
                });
                return card;

            default:
                card = new BigImageButtonsCard();
                card.setDescription(description);
                card.setTitle(title);
                icon = getResources().getDrawable(R.drawable.photo);
                card.setBitmap(icon);
                ((BigImageButtonsCard)card).setLeftButtonText("IZQUIERDA");
                ((BigImageButtonsCard)card).setRightButtonText("DERECHA");
                ((BigImageButtonsCard)card).setOnButtonPressListener(new OnButtonPressListener() {
                    @Override
                    public void onLeftTextPressed(TextView textView) {
                        //Toast.makeText(mContext, "PULSADA IZQUIERDA EN NUMERO "+(position+1), //Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onRightTextPressed(TextView textView) {
                        //Toast.makeText(mContext, "PULSADA DERECHA EN NUMERO "+(position+1), //Toast.LENGTH_SHORT).show();
                    }
                });
                return card;

        }

    }
}
