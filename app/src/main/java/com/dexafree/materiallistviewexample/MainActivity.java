package com.dexafree.materiallistviewexample;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.dexafree.materialList.cards.Card;
import com.dexafree.materialList.cards.OnButtonClickListener;
import com.dexafree.materialList.cards.renderer.BasicButtonsCardRenderer;
import com.dexafree.materialList.cards.renderer.BasicImageButtonsCardRenderer;
import com.dexafree.materialList.cards.renderer.BasicListCardRenderer;
import com.dexafree.materialList.cards.renderer.BigImageButtonsCardRenderer;
import com.dexafree.materialList.cards.renderer.BigImageCardRenderer;
import com.dexafree.materialList.cards.renderer.SmallImageCardRenderer;
import com.dexafree.materialList.cards.renderer.WelcomeCardRenderer;
import com.dexafree.materialList.controller.OnDismissCallback;
import com.dexafree.materialList.controller.RecyclerItemClickListener;
import com.dexafree.materialList.view.MaterialListView;


public class MainActivity extends ActionBarActivity {
    private Context mContext;
    private MaterialListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Save a reference to the context
        mContext = this;

        // Bind the MaterialListView to a variable
        mListView = (MaterialListView) findViewById(R.id.material_listview);

        // Fill the array with mock content
        fillArray();

        // Set the dismiss listener
        mListView.setOnDismissCallback(new OnDismissCallback() {
            @Override
            public void onDismiss(Card card, int position) {
                // Show a toast
                Toast.makeText(mContext, "You have dismissed a " + card.getTag(), Toast.LENGTH_SHORT).show();
            }
        });

        // Add the ItemTouchListener
        mListView.addOnItemTouchListener(new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(Card card, int position) {
                Log.d("CARD_TYPE", "" + card.getTag());
            }

            @Override
            public void onItemLongClick(Card card, int position) {
                Log.d("LONG_CLICK", "" + card.getTag());
            }
        });

    }

    private void fillArray() {
        for (int i = 0; i < 35; i++) {
            mListView.add(getRandomCard(i));
        }
    }

    private Card getRandomCard(final int position) {
        String title = "Card number " + (position + 1);
        String description = "Lorem ipsum dolor sit amet";

        switch (position % 6) {
            case 0: {
                return new Card.Builder(this)
                        .setTag("SMALL_IMAGE_CARD")
                        .setDismissible()
                        .build(new SmallImageCardRenderer(this)
                                .setTitle(title)
                                .setDescription(description)
                                .setDrawable(R.drawable.sample_android));
            }
            case 1: {
                return new Card.Builder(this)
                        .setTag("BIG_IMAGE_CARD")
                        .build(new BigImageCardRenderer(this)
                                .setTitle(title)
                                .setDescription(description)
                                .setDrawable("https://assets-cdn.github.com/images/modules/logos_page/GitHub-Mark.png"));
            }
            case 2: {
                final BasicImageButtonsCardRenderer renderer = new BasicImageButtonsCardRenderer(this)
                        .setTitle(title)
                        .setDescription(description)
                        .setDrawable(R.drawable.dog)
                        .setLeftButtonText("left")
                        .setRightButtonText("right");

                renderer.setOnLeftButtonClickListener(new OnButtonClickListener() {
                    @Override
                    public void onButtonPressedListener(final View view, final Card card) {
                        Toast.makeText(mContext, "You have pressed the left button", Toast.LENGTH_SHORT).show();
                        renderer.setTitle("CHANGED ON RUNTIME");
                    }
                });
                renderer.setOnRightButtonClickListener(new OnButtonClickListener() {
                    @Override
                    public void onButtonPressedListener(final View view, final Card card) {
                        Toast.makeText(mContext, "You have pressed the right button on card " + renderer.getTitle(), Toast.LENGTH_SHORT).show();
                        mListView.remove(card);
                    }
                });

                if (position % 2 == 0) {
                    renderer.setDividerVisible(true);
                }

                return new Card.Builder(this)
                        .setTag("BASIC_IMAGE_BUTTON_CARD")
                        .setDismissible()
                        .build(renderer);
            }
            case 3: {
                final BasicButtonsCardRenderer renderer = new BasicButtonsCardRenderer(this)
                        .setTitle(title)
                        .setDescription(description)
                        .setLeftButtonText("left")
                        .setRightButtonText("right")
                        .setRightButtonTextResourceColor(R.color.accent_material_dark);

                renderer.setOnLeftButtonClickListener(new OnButtonClickListener() {
                    @Override
                    public void onButtonPressedListener(final View view, final Card card) {
                        Toast.makeText(mContext, "You have pressed the left button", Toast.LENGTH_SHORT).show();
                    }
                });
                renderer.setOnRightButtonClickListener(new OnButtonClickListener() {
                    @Override
                    public void onButtonPressedListener(final View view, final Card card) {
                        Toast.makeText(mContext, "You have pressed the right button", Toast.LENGTH_SHORT).show();
                    }
                });

                if (position % 2 == 0) {
                    renderer.setDividerVisible(true);
                }

                return new Card.Builder(this)
                        .setTag("BASIC_BUTTONS_CARD")
                        .setDismissible()
                        .build(renderer);
            }
            case 4: {
                final WelcomeCardRenderer renderer = new WelcomeCardRenderer(this)
                        .setTitle("Welcome Card")
                        .setTitleColor(Color.WHITE)
                        .setDescription("I am the description")
                        .setDescriptionColor(Color.WHITE)
                        .setSubtitle("My subtitle!")
                        .setSubtitleColor(Color.WHITE)
                        .setBackgroundColor(Color.BLUE)
                        .setButtonText("Okay!")
                        .setOnButtonPressedListener(new OnButtonClickListener() {
                            @Override
                            public void onButtonPressedListener(final View view, final Card card) {
                                Toast.makeText(mContext, "Welcome!", Toast.LENGTH_SHORT).show();
                            }
                        });

                if (position % 2 == 0) {
                    renderer.setBackgroundResourceColor(android.R.color.background_dark);
                }

                return new Card.Builder(this)
                        .setTag("WELCOME_CARD")
                        .setDismissible()
                        .build(renderer);
            }
            case 5: {
                BasicListAdapter adapter = new BasicListAdapter(this);
                adapter.add("Text1");
                adapter.add("Text2");
                adapter.add("Text3");

                return new Card.Builder(this)
                        .setTag("LIST_CARD")
                        .setDismissible()
                        .build(new BasicListCardRenderer(this)
                                .setTitle("List Card")
                                .setDescription("Take a list")
                                .setAdapter(adapter));
            }
            default: {
                final BigImageButtonsCardRenderer renderer = new BigImageButtonsCardRenderer(this)
                        .setTitle(title)
                        .setDescription(description)
                        .setDrawable(R.drawable.photo)
                        .setLeftButtonText("add card")
                        .setRightButtonText("right button");

                renderer.setOnLeftButtonClickListener(new OnButtonClickListener() {
                    @Override
                    public void onButtonPressedListener(final View view, final Card card) {
                        Log.d("ADDING", "CARD");

                        mListView.add(generateNewCard());
                        Toast.makeText(mContext, "Added new card", Toast.LENGTH_SHORT).show();
                    }
                });
                renderer.setOnRightButtonClickListener(new OnButtonClickListener() {
                    @Override
                    public void onButtonPressedListener(final View view, final Card card) {
                        Toast.makeText(mContext, "You have pressed the right button", Toast.LENGTH_SHORT).show();
                    }
                });

                if (position % 2 == 0) {
                    renderer.setDividerVisible(true);
                }

                return new Card.Builder(this)
                        .setTag("BIG_IMAGE_BUTTONS_CARD")
                        .setDismissible()
                        .build(renderer);
            }
        }
    }

    private Card generateNewCard() {
        return new Card.Builder(this)
                .setTag("BASIC_IMAGE_BUTTONS_CARD")
                .build(new BasicImageButtonsCardRenderer(this)
                        .setTitle("I'm new")
                        .setDescription("I've been generated on runtime!")
                        .setDrawable(R.drawable.dog));
    }

    private void addMockCardAtStart() {
        mListView.addAtStart(new Card.Builder(this)
                .setTag("BASIC_IMAGE_BUTTONS_CARD")
                .setDismissible()
                .build(new BasicImageButtonsCardRenderer(this)
                        .setTitle("Hi there")
                        .setDescription("I've been added on top!")
                        .setLeftButtonText("left")
                        .setRightButtonText("right")
                        .setDrawable(R.drawable.dog)));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_clear:
                mListView.clear();
                break;
            case R.id.action_add_at_start:
                addMockCardAtStart();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
