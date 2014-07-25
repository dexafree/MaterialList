package com.dexafree.materiallistviewexample;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.dexafree.materialList.MaterialAdapter;
import com.dexafree.materialList.controller.OnButtonPressListener;
import com.dexafree.materialList.controller.OnDismissCallback;
import com.dexafree.materialList.model.BasicButtonsCard;
import com.dexafree.materialList.model.SmallImageCard;
import com.dexafree.materialList.model.BasicImageButtonsCard;
import com.dexafree.materialList.model.BigImageButtonsCard;
import com.dexafree.materialList.model.BigImageCard;
import com.dexafree.materialList.model.Card;
import com.dexafree.materialList.model.CardList;
import com.dexafree.materialList.view.MaterialGridView;
import com.dexafree.materialList.view.MaterialListView;


public class MainActivity extends ActionBarActivity {

    private MaterialGridView mListView;
    private CardList cardsList;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mContext = this;

        mListView = (MaterialGridView) findViewById(R.id.material_listview);

        cardsList = new CardList();

        fillArray();

        MaterialAdapter adapter = new MaterialAdapter(this, cardsList);

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
