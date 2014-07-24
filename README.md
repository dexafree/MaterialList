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
MaterialList brings you this feature, and in order to 
