package com.dexafree.materiallistviewexample;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckedTextView;
import android.widget.TextView;
import android.widget.Toast;

import com.dexafree.materialList.controller.OnButtonPressListener;
import com.dexafree.materialList.controller.OnDismissCallback;
import com.dexafree.materialList.model.BasicButtonsCard;
import com.dexafree.materialList.model.BasicImageButtonsCard;
import com.dexafree.materialList.model.BasicListCard;
import com.dexafree.materialList.model.BigImageButtonsCard;
import com.dexafree.materialList.model.BigImageCard;
import com.dexafree.materialList.model.Card;
import com.dexafree.materialList.model.SmallImageCard;
import com.dexafree.materialList.model.WelcomeCard;
import com.dexafree.materialList.view.MaterialListView;


public class MainActivity extends ActionBarActivity {
    private Context mContext;
    private MaterialListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mContext = this;

        mListView = (MaterialListView) findViewById(R.id.material_listview);
		mListView.setCardAnimation(MaterialListView.CardAnimation.SWING_BOTTOM_IN);

        fillArray();

        mListView.setOnDismissCallback(new OnDismissCallback() {
            @Override
            public void onDismiss(Card card, int position) {
                //Toast.makeText(mContext, "CARD NUMBER "+position+" dismissed", //Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void fillArray(){
        for(int i=0;i<35;i++){
            Card card = getRandomCard(i);
            mListView.getAdapter().add(card);
        }
    }

    private Card getRandomCard(final int position){
        String title = "Card number "+(position+1);
        String description = "Lorem ipsum dolor sit amet";

        int type = position % 6;

        final Card card;
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
                ((BasicImageButtonsCard)card).setLeftButtonText("LEFT");
                ((BasicImageButtonsCard)card).setRightButtonText("RIGHT");

                if (position % 2 == 0)
                    ((BasicImageButtonsCard)card).setShowDivider(true);

                ((BasicImageButtonsCard)card).setOnLeftButtonPressedListener(new OnButtonPressListener() {
                    @Override
                    public void onButtonPressedListener(TextView textView) {
                        Toast.makeText(mContext, "You have pressed the left button", Toast.LENGTH_SHORT).show();
						mListView.getAdapter().getItem(0).setTitle("CHANGED ON RUNTIME");
						mListView.getAdapter().notifyDataSetChanged();
                    }
                });

                ((BasicImageButtonsCard)card).setOnRightButtonPressedListener(new OnButtonPressListener() {
                    @Override
                    public void onButtonPressedListener(TextView textView) {
                        Toast.makeText(mContext, "You have pressed the right button", Toast.LENGTH_SHORT).show();
                        card.dismiss();
                    }
                });

                return card;

            case 3:
                card = new BasicButtonsCard();
                card.setDescription(description);
                card.setTitle(title);
                ((BasicButtonsCard)card).setLeftButtonText("LEFT");
                ((BasicButtonsCard)card).setRightButtonText("RIGHT");

                if (position % 2 == 0)
                    ((BasicButtonsCard)card).setShowDivider(true);

                ((BasicButtonsCard)card).setOnLeftButtonPressedListener(new OnButtonPressListener() {
                    @Override
                    public void onButtonPressedListener(TextView textView) {
                        Toast.makeText(mContext, "You have pressed the left button", Toast.LENGTH_SHORT).show();
                    }
                });

                ((BasicButtonsCard)card).setOnRightButtonPressedListener(new OnButtonPressListener() {
                    @Override
                    public void onButtonPressedListener(TextView textView) {
                        Toast.makeText(mContext, "You have pressed the right button", Toast.LENGTH_SHORT).show();
                    }
                });


                return card;

            case 4:
                card = new WelcomeCard();
                card.setTitle("Welcome Card");
                card.setDescription("I am the description");
                ((WelcomeCard)card).setSubtitle("My subtitle!");
                ((WelcomeCard)card).setButtonText("Okay!");
                ((WelcomeCard)card).setOnButtonPressedListener(new OnButtonPressListener() {
                    @Override
                    public void onButtonPressedListener(TextView textView) {
                        Toast.makeText(mContext, "A welcome card has been dismissed", Toast.LENGTH_SHORT).show();
                    }
                });

                if(position%2 == 0)
                    ((WelcomeCard)card).setBackgroundColorFromId(mContext, R.color.background_material_dark);

                return card;

            case 5:
                card = new BasicListCard();
                card.setTitle("List Card");
                card.setDescription("Take a list");
                ((BasicListCard) card).addAllItems("Task 1", "Task 2", "Task 3");
                ((BasicListCard) card).setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        CheckedTextView checkedTextView = (CheckedTextView) view;
                        checkedTextView.setChecked(!checkedTextView.isChecked());
                    }
                });

                return card;

            default:
                card = new BigImageButtonsCard();
                card.setDescription(description);
                card.setTitle(title);
                icon = getResources().getDrawable(R.drawable.photo);
                card.setBitmap(icon);
                ((BigImageButtonsCard)card).setLeftButtonText("ADD CARD");
                ((BigImageButtonsCard)card).setRightButtonText("RIGHT BUTTON");

                if (position % 2 == 0) {
                    ((BigImageButtonsCard) card).setShowDivider(true);
                }

                ((BigImageButtonsCard)card).setOnLeftButtonPressedListener(new OnButtonPressListener() {
                    @Override
                    public void onButtonPressedListener(TextView textView) {
                        Log.d("ADDING", "CARD");

						mListView.getAdapter().add(generateNewCard());
                        Toast.makeText(mContext, "Added new card", Toast.LENGTH_SHORT).show();
                    }
                });

                ((BigImageButtonsCard)card).setOnRightButtonPressedListener(new OnButtonPressListener() {
                    @Override
                    public void onButtonPressedListener(TextView textView) {
                        Toast.makeText(mContext, "You have pressed the right button", Toast.LENGTH_SHORT).show();
                    }
                });


                return card;

        }

    }

    private Card generateNewCard(){
        Card card = new BasicImageButtonsCard();
        card.setBitmap(mContext, R.drawable.dog);
        card.setTitle("I'm new");
        card.setDescription("I've been generated on runtime!");

        return card;
    }
}
