# MaterialList
MaterialList is an Android library created to help all Android developers get the beautiful CardViews that Google shows at its [official design specifications](http://www.google.com/design/spec/components/cards.html#cards-usage).

Provided as a ListView extension, it can receive a list of Cards (stored in a CardList, provided by the library) and show them accordingly to the android style and design patterns.

It also has been developed while keeping extensibility in mind, which meens that you are able to create your own card layouts and add them to the CardList without any pain (see examples below).

## Cards provided
These are the cards that the library offers by default:
* **SmallImageCard**
![SmallImageCard](http://i.imgur.com/f5LLorA.png)

* **BigImageCard**
![BigImageCard](http://i.imgur.com/yW7uBNy.png)

* **BasicImageButtonsCard**
![BasicImageButtonsCard](http://i.imgur.com/ENxUGAw.png)

* **BasicButtonsCard**
![BasicButtonsCard](http://i.imgur.com/19xt1FX.png)

* **BigImageButtonsCard**
![BigImageButtonsCard](http://i.imgur.com/vr4vP6o.png)

## How to use
First of all, you'll need to declare a MaterialListView in your layout:
```xml
<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:paddingLeft="@dimen/activity_horizontal_margin"
    android:paddingRight="@dimen/activity_horizontal_margin"
    android:paddingTop="@dimen/activity_vertical_margin"
    android:paddingBottom="@dimen/activity_vertical_margin">

    <com.dexafree.materiallistviewexample.view.MaterialListView
        android:layout_width="fill_parent"
        android:layout_height="fill_parent"
        android:id="@+id/material_listview"/>

</RelativeLayout>
```

After that, get it on your code by simply making a findViewById query.

```java
mListView = (MaterialListView) findViewById(R.id.material_listview);
```

Then, create your CardList, fill it with Cards, create the adapter and you're ready to go:

```java
CardList cardsList = new CardList();

// Fill your CardsList

MaterialListViewAdapter adapter = new MaterialListViewAdapter(mContext, cardsList);

mListView.setMaterialListViewAdapter(adapter);
```

## Dismissing the cards
One of the features I've always loved is the SwipeToDismiss gesture.
MaterialList brings you this feature, and in order to set a callback to the dismissing action, you only need to create your own OnDismissCallback:

```java
mListView.setOnDismissCallback(new OnDismissCallback() {
    @Override
    public void onDismiss(Card card, int position) {
        // Do whatever you want here
    }
});
```
You will also be able to decide if a card should be dismissable or not, just by calling Card.setCanDismiss().

## Creating your own cards

### The Card class
By default, Card includes:
* String title
* String description
* Bitmap bitmap
* int layout

It also will convert Drawables and resourceIds to Bitmaps if assigned.

Feel free to add any extra elements you'll need.

## How to create new cards
In order to provide an extensible library, in order to create your own Cards you'll need to do 3 things:

1. Declare your Card class
2. Declare your ItemView Class
3. Declare your Layout

### Card class
The first thing you'll need to do is declaring your Card class (a class which will extend Card), and override its getLayout() method:

```java
public class CustomCard extends Card {
    @Override
    public int getLayout(){
        return R.layout.my_custom_layout;
    }
}
```

If you need more things like a callback, a String for extra elements... you should declare it here.

Also, if you wanted this kind of card always to be dismissable (or not), you could override the canDismiss() method and make it return whatever you want.

### ItemView class
After having your CustomCard class, you'll need to define a class which will render its content.
In order to be consistent with the system (and being able to attach it to the MaterialAdapter), you'll need to extend GridItemView<CustomCard> and provide a configureView(CustomCard card).
Here you have an example of how to implement it:

```java
public class CustomCardItemView extends GridItemView<CustomCard> {

    TextView mTitle;
    TextView mDescription;

    // Default constructors
    public CustomCardItemView(Context context) {
        super(context);
    }

    public CustomCardItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomCardItemView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public void configureView(CustomCard card) {
        setTitle(card.getTitle());
        setDescription(card.getDescription());
    }

    public void setTitle(String title){
        mTitle = (TextView)findViewById(R.id.titleTextView);
        mTitle.setText(title);
    }

    public void setDescription(String description){
        mDescription = (TextView)findViewById(R.id.descriptionTextView);
        mDescription.setText(description);
    }
}
```

## Layout
Finally you'll have to declare your own layout (feel free to take a look at the layouts provided in order to look for inspiration or taking a starting point).
The most important thing is that you must provide a CustomCardItemView element as the root of the view:

```xml
<?xml version="1.0" encoding="utf-8"?>

<CustomCardItemView
    xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent" />

    <!--YOUR UI ELEMENTS.-->

</CustomCardItemView>
```

After that, you'll be able to use your new custom card just by creating it, setting its content and inserting it in a CardList.

## Credits
* Jake Wharton: [SwipeToDismissNOA](https://github.com/JakeWharton/SwipeToDismissNOA)
* Romain Guy: The sand picture provided as example was taken from one of his projects
