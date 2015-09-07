package com.dexafree.materiallistviewexample;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.dexafree.materialList.card.Card;
import com.dexafree.materialList.card.OnButtonClickListener;
import com.dexafree.materialList.card.provider.BasicButtonsCardProvider;
import com.dexafree.materialList.card.provider.BasicImageButtonsCardProvider;
import com.dexafree.materialList.card.provider.BasicListCardProvider;
import com.dexafree.materialList.card.provider.BigImageButtonsCardProvider;
import com.dexafree.materialList.card.provider.BigImageCardProvider;
import com.dexafree.materialList.card.provider.SmallImageCardProvider;
import com.dexafree.materialList.card.provider.TextCardProvider;
import com.dexafree.materialList.card.provider.WelcomeCardProvider;
import com.dexafree.materialList.listeners.OnDismissCallback;
import com.dexafree.materialList.listeners.RecyclerItemClickListener;
import com.dexafree.materialList.view.MaterialListView;
import com.squareup.picasso.RequestCreator;


public class MainActivity extends AppCompatActivity {
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
            public void onDismiss(@NonNull Card card, int position) {
                // Show a toast
                Toast.makeText(mContext, "You have dismissed a " + card.getTag(), Toast.LENGTH_SHORT).show();
            }
        });

        // Add the ItemTouchListener
        mListView.addOnItemTouchListener(new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(@NonNull Card card, int position) {
                Log.d("CARD_TYPE", "" + card.getTag());
            }

            @Override
            public void onItemLongClick(@NonNull Card card, int position) {
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
                        .withProvider(SmallImageCardProvider.class)
                        .setTitle(title)
                        .setDescription(description)
                        .setDrawable(R.drawable.sample_android)
                        .endConfig()
                        .build();
            }
            case 1: {
                return new Card.Builder(this)
                        .setTag("BIG_IMAGE_CARD")
                        .withProvider(BigImageCardProvider.class)
                        .setTitle(title)
                        .setDescription(description)
                        .setDrawable("https://assets-cdn.github.com/images/modules/logos_page/GitHub-Mark.png")
                        .setOnPicassoImageLoadingListener(new TextCardProvider.OnPicassoImageLoadingListener() {
                            @Override
                            public void onImageLoading(@NonNull final RequestCreator requestCreator) {
                                requestCreator.rotate(position * 45.0f)
                                        .resize(200, 200)
                                        .centerCrop();
                            }
                        })
                        .endConfig()
                        .build();
            }
            case 2: {
                final BasicImageButtonsCardProvider provider = new Card.Builder(this)
                        .setTag("BASIC_IMAGE_BUTTON_CARD")
                        .setDismissible()
                        .withProvider(BasicImageButtonsCardProvider.class)
                        .setTitle(title)
                        .setDescription(description)
                        .setDrawable(R.drawable.dog)
                        .setLeftButtonText("left")
                        .setRightButtonText("right");

                provider.setOnLeftButtonClickListener(new OnButtonClickListener() {
                    @Override
                    public void onButtonClicked(final View view, final Card card) {
                        Toast.makeText(mContext, "You have pressed the left button", Toast.LENGTH_SHORT).show();
                        provider.setTitle("CHANGED ON RUNTIME");
                    }
                });
                provider.setOnRightButtonClickListener(new OnButtonClickListener() {
                    @Override
                    public void onButtonClicked(final View view, final Card card) {
                        Toast.makeText(mContext, "You have pressed the right button on card " + provider.getTitle(), Toast.LENGTH_SHORT).show();
                        mListView.remove(card);
                    }
                });

                if (position % 2 == 0) {
                    provider.setDividerVisible(true);
                }

                return provider.endConfig().build();
            }
            case 3: {
                final BasicButtonsCardProvider provider = new Card.Builder(this)
                        .setTag("BASIC_BUTTONS_CARD")
                        .setDismissible()
                        .withProvider(BasicButtonsCardProvider.class)
                        .setTitle(title)
                        .setDescription(description)
                        .setLeftButtonText("left")
                        .setRightButtonText("right")
                        .setRightButtonTextResourceColor(R.color.accent_material_dark);

                provider.setOnLeftButtonClickListener(new OnButtonClickListener() {
                    @Override
                    public void onButtonClicked(final View view, final Card card) {
                        Toast.makeText(mContext, "You have pressed the left button", Toast.LENGTH_SHORT).show();
                    }
                });
                provider.setOnRightButtonClickListener(new OnButtonClickListener() {
                    @Override
                    public void onButtonClicked(final View view, final Card card) {
                        Toast.makeText(mContext, "You have pressed the right button", Toast.LENGTH_SHORT).show();
                    }
                });

                if (position % 2 == 0) {
                    provider.setDividerVisible(true);
                }

                return provider.endConfig().build();
            }
            case 4: {
                final WelcomeCardProvider provider = new Card.Builder(this)
                        .setTag("WELCOME_CARD")
                        .setDismissible()
                        .withProvider(WelcomeCardProvider.class)
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
                            public void onButtonClicked(final View view, final Card card) {
                                Toast.makeText(mContext, "Welcome!", Toast.LENGTH_SHORT).show();
                            }
                        });

                if (position % 2 == 0) {
                    provider.setBackgroundResourceColor(android.R.color.background_dark);
                }

                return provider.endConfig().build();
            }
            case 5: {
                BasicListAdapter adapter = new BasicListAdapter(this);
                adapter.add("Text1");
                adapter.add("Text2");
                adapter.add("Text3");

                return new Card.Builder(this)
                        .setTag("LIST_CARD")
                        .setDismissible()
                        .withProvider(BasicListCardProvider.class)
                        .setTitle("List Card")
                        .setDescription("Take a list")
                        .setAdapter(adapter)
                        .endConfig()
                        .build();
            }
            default: {
                final BigImageButtonsCardProvider provider = new Card.Builder(this)
                        .setTag("BIG_IMAGE_BUTTONS_CARD")
                        .setDismissible()
                        .withProvider(BigImageButtonsCardProvider.class)
                        .setTitle(title)
                        .setDescription(description)
                        .setDrawable(R.drawable.photo)
                        .setLeftButtonText("add card")
                        .setRightButtonText("right button");

                provider.setOnLeftButtonClickListener(new OnButtonClickListener() {
                    @Override
                    public void onButtonClicked(final View view, final Card card) {
                        Log.d("ADDING", "CARD");

                        mListView.add(generateNewCard());
                        Toast.makeText(mContext, "Added new card", Toast.LENGTH_SHORT).show();
                    }
                });
                provider.setOnRightButtonClickListener(new OnButtonClickListener() {
                    @Override
                    public void onButtonClicked(final View view, final Card card) {
                        Toast.makeText(mContext, "You have pressed the right button", Toast.LENGTH_SHORT).show();
                    }
                });

                if (position % 2 == 0) {
                    provider.setDividerVisible(true);
                }

                return provider.endConfig().build();
            }
        }
    }

    private Card generateNewCard() {
        return new Card.Builder(this)
                .setTag("BASIC_IMAGE_BUTTONS_CARD")
                .withProvider(BasicImageButtonsCardProvider.class)
                .setTitle("I'm new")
                .setDescription("I've been generated on runtime!")
                .setDrawable(R.drawable.dog)
                .endConfig()
                .build();
    }

    private void addMockCardAtStart() {
        mListView.addAtStart(new Card.Builder(this)
                .setTag("BASIC_IMAGE_BUTTONS_CARD")
                .setDismissible()
                .withProvider(BasicImageButtonsCardProvider.class)
                .setTitle("Hi there")
                .setDescription("I've been added on top!")
                .setLeftButtonText("left")
                .setRightButtonText("right")
                .setDrawable(R.drawable.dog)
                .endConfig()
                .build());
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
                mListView.clearAll();
                break;
            case R.id.action_add_at_start:
                addMockCardAtStart();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
