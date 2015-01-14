package com.dexafree.materiallistviewexample;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.dexafree.materialList.cards.OnButtonPressListener;
import com.dexafree.materialList.controller.OnDismissCallback;
import com.dexafree.materialList.model.Card;
import com.dexafree.materialList.cards.SimpleCard;
import com.dexafree.materialList.cards.BasicButtonsCard;
import com.dexafree.materialList.cards.BasicImageButtonsCard;
import com.dexafree.materialList.cards.BasicListCard;
import com.dexafree.materialList.cards.BigImageButtonsCard;
import com.dexafree.materialList.cards.BigImageCard;
import com.dexafree.materialList.cards.SmallImageCard;
import com.dexafree.materialList.cards.WelcomeCard;
import com.dexafree.materialList.view.IMaterialView;
import com.dexafree.materialList.view.MaterialListView;


public class MainActivity extends ActionBarActivity {
    private Context mContext;
    private IMaterialView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mContext = this;

        mListView = (IMaterialView) findViewById(R.id.material_listview);
        mListView.setCardAnimation(MaterialListView.CardAnimation.SWING_BOTTOM_IN);

        fillArray();

        mListView.setOnDismissCallback(new OnDismissCallback() {
            @Override
            public void onDismiss(Card card, int position) {
                //Toast.makeText(mContext, "CARD NUMBER "+position+" dismissed", //Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void fillArray() {
        for (int i = 0; i < 35; i++) {
            Card card = getRandomCard(i);
            mListView.add(card);
        }
    }

    private Card getRandomCard(final int position) {
        String title = "Card number " + (position + 1);
        String description = "Lorem ipsum dolor sit amet";

        int type = position % 6;

        SimpleCard card;
        Drawable icon;

        switch (type) {

            case 0:
                card = new SmallImageCard(this);
                card.setDescription(description);
                card.setTitle(title);
                card.setDrawable(R.drawable.ic_launcher);
                card.setDismissible(true);
                return card;

            case 1:
                card = new BigImageCard(this);
                card.setDescription(description);
                card.setTitle(title);
                card.setDrawable(R.drawable.photo);
                return card;

            case 2:
                card = new BasicImageButtonsCard(this);
                card.setDescription(description);
                card.setTitle(title);
                card.setDrawable(R.drawable.dog);
                ((BasicImageButtonsCard) card).setLeftButtonText("LEFT");
                ((BasicImageButtonsCard) card).setRightButtonText("RIGHT");

                if (position % 2 == 0)
                    ((BasicImageButtonsCard) card).setDividerVisible(true);

                ((BasicImageButtonsCard) card).setOnLeftButtonPressedListener(new OnButtonPressListener() {
                    @Override
                    public void onButtonPressedListener(View view, Card card) {
                        Toast.makeText(mContext, "You have pressed the left button", Toast.LENGTH_SHORT).show();
                        ((SimpleCard) card).setTitle("CHANGED ON RUNTIME");
                    }
                });

                ((BasicImageButtonsCard) card).setOnRightButtonPressedListener(new OnButtonPressListener() {
                    @Override
                    public void onButtonPressedListener(View view, Card card) {
                        Toast.makeText(mContext, "You have pressed the right button on card " + ((SimpleCard) card).getTitle(), Toast.LENGTH_SHORT).show();
                        mListView.remove(card);
                    }
                });
                card.setDismissible(true);

                return card;

            case 3:
                card = new BasicButtonsCard(this);
                card.setDescription(description);
                card.setTitle(title);
                ((BasicButtonsCard) card).setLeftButtonText("LEFT");
                ((BasicButtonsCard) card).setRightButtonText("RIGHT");

                if (position % 2 == 0)
                    ((BasicButtonsCard) card).setDividerVisible(true);

                ((BasicButtonsCard) card).setOnLeftButtonPressedListener(new OnButtonPressListener() {
                    @Override
                    public void onButtonPressedListener(View view, Card card) {
                        Toast.makeText(mContext, "You have pressed the left button", Toast.LENGTH_SHORT).show();
                    }
                });

                ((BasicButtonsCard) card).setOnRightButtonPressedListener(new OnButtonPressListener() {
                    @Override
                    public void onButtonPressedListener(View view, Card card) {
                        Toast.makeText(mContext, "You have pressed the right button", Toast.LENGTH_SHORT).show();
                    }
                });
                card.setDismissible(true);


                return card;

            case 4:
                card = new WelcomeCard(this);
                card.setTitle("Welcome Card");
                card.setDescription("I am the description");
                ((WelcomeCard) card).setSubtitle("My subtitle!");
                ((WelcomeCard) card).setButtonText("Okay!");
                ((WelcomeCard) card).setOnButtonPressedListener(new OnButtonPressListener() {
                    @Override
                    public void onButtonPressedListener(View view, Card card) {
                        Toast.makeText(mContext, "Welcome!", Toast.LENGTH_SHORT).show();
                    }
                });

                if (position % 2 == 0)
                    ((WelcomeCard) card).setBackgroundColorRes(R.color.background_material_dark);
                card.setDismissible(true);

                return card;

            case 5:
                card = new BasicListCard(this);
                card.setTitle("List Card");
                card.setDescription("Take a list");
                BasicListAdapter adapter = new BasicListAdapter(this);
                adapter.add("Text1");
                adapter.add("Text2");
                adapter.add("Text3");
                ((BasicListCard) card).setAdapter(adapter);
                /*
                ((BasicListCard) card).setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    	// Do what ever you want...
                    }
                });
                */
                card.setDismissible(true);

                return card;

            default:
                card = new BigImageButtonsCard(this);
                card.setDescription(description);
                card.setTitle(title);
                card.setDrawable(R.drawable.photo);
                ((BigImageButtonsCard) card).setLeftButtonText("ADD CARD");
                ((BigImageButtonsCard) card).setRightButtonText("RIGHT BUTTON");

                if (position % 2 == 0) {
                    ((BigImageButtonsCard) card).setDividerVisible(true);
                }

                ((BigImageButtonsCard) card).setOnLeftButtonPressedListener(new OnButtonPressListener() {
                    @Override
                    public void onButtonPressedListener(View view, Card card) {
                        Log.d("ADDING", "CARD");

                        mListView.add(generateNewCard());
                        Toast.makeText(mContext, "Added new card", Toast.LENGTH_SHORT).show();
                    }
                });

                ((BigImageButtonsCard) card).setOnRightButtonPressedListener(new OnButtonPressListener() {
                    @Override
                    public void onButtonPressedListener(View view, Card card) {
                        Toast.makeText(mContext, "You have pressed the right button", Toast.LENGTH_SHORT).show();
                    }
                });
                card.setDismissible(true);


                return card;

        }

    }

    private Card generateNewCard() {
        SimpleCard card = new BasicImageButtonsCard(this);
        card.setDrawable(R.drawable.dog);
        card.setTitle("I'm new");
        card.setDescription("I've been generated on runtime!");

        return card;
    }
}
