# MaterialList [![Build Status](https://travis-ci.org/dexafree/MaterialList.svg?branch=master)](https://travis-ci.org/dexafree/MaterialList) [![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.github.dexafree/materiallist/badge.svg?style=flat)](https://maven-badges.herokuapp.com/maven-central/com.github.dexafree/materiallist) [![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-MaterialList-brightgreen.svg?style=flat)](https://android-arsenal.com/details/1/1161)

## Discontinued

This library will not receive any updates, as I do not have the time or knowledge to improve it.

If anyone forks it and wants to mantain it, please let me know and I will add a link to the mantained version.

-----

> ### Warning!
> MaterialList v3 changes the way Cards are built, and its API is not backwards compatible.
> Read the changes and learn how to build your cards in the Wiki.
> 
> Also the v3 version should be considered experimental

MaterialList is an Android library created to help all Android developers get the beautiful CardViews that Google shows at its [official design specifications](http://www.google.com/design/spec/components/cards.html#cards-usage).

Provided as a RecyclerView extension, it can receive a list of Cards (stored in a CardList, provided by the library) and show them accordingly to the android style and design patterns.

It also has been developed while keeping extensibility in mind, which means that you are able to create your own card layouts and add them to the CardList without any pain (see examples below).

## Cards provided
These are the cards that the library offers by default:

[<img src="http://i.imgur.com/f5LLorA.png" alt="SmallImageCard" width="300px">](https://github.com/dexafree/MaterialList/wiki/SmallImageCard)
[<img src="http://i.imgur.com/ENxUGAw.png" alt="BasicImageButtonsCard" width="300px">](https://github.com/dexafree/MaterialList/wiki/BasicImageButtonsCard)
[<img src="http://i.imgur.com/yW7uBNy.png" alt="BigImageCard" width="300px">](https://github.com/dexafree/MaterialList/wiki/BigImageCard)
[<img src="http://i.imgur.com/vr4vP6o.png" alt="BigImageButtonsCard" width="300px">](https://github.com/dexafree/MaterialList/wiki/BigImageButtonsCard)
[<img src="http://i.imgur.com/19xt1FX.png" alt="BasicButtonsCard" width="300px">](https://github.com/dexafree/MaterialList/wiki/BasicButtonsCard)
[<img src="http://i.imgur.com/ZGPmfaf.jpg" alt="WelcomeCard" width="300px">](https://github.com/dexafree/MaterialList/wiki/WelcomeCard)
[<img src="http://i.imgur.com/iR7xmbG.jpg" alt="BasicListCard" width="300px">](https://github.com/dexafree/MaterialList/wiki/BasicListCard)

For further documentation, you can [check the Wiki page](https://github.com/dexafree/MaterialList/wiki/).

## How to use

The MaterialListView is based on a RecyclerView. It acts just as a normal ListView, but offering options for interacting with your cards. It can display the cards in a single or multiple columns.

### 1. Step: Declare a MaterialListView in your layout

```xml
<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:paddingLeft="@dimen/activity_horizontal_margin"
    android:paddingRight="@dimen/activity_horizontal_margin"
    android:paddingTop="@dimen/activity_vertical_margin"
    android:paddingBottom="@dimen/activity_vertical_margin">

    <com.dexafree.materialList.view.MaterialListView
        android:layout_width="fill_parent"
        android:layout_height="fill_parent"
        android:id="@+id/material_listview"/>

</RelativeLayout>
```

There are also some additional attributes to define the column count of the MaterialListView. Therefore you need

```xml
xmlns:app="http://schemas.android.com/apk/res-auto"
```

You can use the **column_count** attribute for using a fixed column count in portrait and landscape mode.

```xml
    app:column_count="1"
```

Or you can use the **column_count_portrait** and **column_count_landscape** attributes.

```xml
    app:column_count_portrait="1"
    app:column_count_landscape="2"
```

### 2. Step: Find your MaterialListView in code

```java
MaterialListView mListView = (MaterialListView) findViewById(R.id.material_listview);
```

### 3. Step: Add Cards to the MaterialListView

```java
Card card = new Card.Builder(this)
                            .setTag("BASIC_IMAGE_BUTTONS_CARD")
                            .withProvider(BasicImageButtonsCardProvider.class)
                            .setTitle("I'm new")
                            .setDescription("I've been generated on runtime!")
                            .setDrawable(R.drawable.dog)
                            .endConfig()
                            .build()

mListView.getAdapter.add(card);
```

There are also some Cards that may show a Divider between the content and the buttons. For further reference, [read the Wiki page](https://github.com/dexafree/MaterialList/wiki/Dividers)

## Clicking the cards
As the project was migrated from `ListView` to `RecyclerView`, it did not offer the native `OnItemClickListener` / `OnItemLongClickListener` methods that can be accessed from ListView.

Since version 2.4.0, you can add your listeners

```java
mListView.addOnItemTouchListener(new RecyclerItemClickListener.OnItemClickListener() {

    @Override
    public void onItemClick(Card card, int position) {
        Log.d("CARD_TYPE", card.getTag().toString());
    }

    @Override
    public void onItemLongClick(Card card, int position) {
        Log.d("LONG_CLICK", card.getTag().toString());
    }
});
```

Check also the **Recovering data from the cards** section in order to be able to recover the Card's content

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
You will also be able to decide if a card should be dismissible or not, just by calling `card.setDismissible(true)`.

Check also the **Recovering data from the cards** section in order to be able to recover the Card's content


## Recovering data from the cards
You can check [the Wiki page in order to get more information about the tag system](https://github.com/dexafree/MaterialList/wiki/Recovering-data-from-the-Cards).


## Animations
Since version 2.0, MaterialList provides animations, in order to enhance cards apparitions.

You can implement them just by calling the [setItemAnimator(RecyclerView.ItemAnimator animator)](https://developer.android.com/reference/android/support/v7/widget/RecyclerView.html#setItemAnimator(android.support.v7.widget.RecyclerView.ItemAnimator) "Android Developer Documentation") method.

There also exists an extensive [animation library for the RecyclerView from wasabeef](https://github.com/wasabeef/recyclerview-animators).

## Extensibility
MaterialList was created with extensibility in mind, so it makes things easy for you if you want to create your own Cards.

For learning how to do it, [check the Wiki page](https://github.com/dexafree/MaterialList/wiki/Create-your-own-Cards)

## Compatibility
MaterialList is compatible with Android 2.3+

## How to use
In order to use MaterialList, you can either clone the project and import it as a module, or you can add this line to your build.gradle script:

```groovy
dependencies {
    ...
    compile 'com.github.dexafree:materiallist:3.2.2'
}
```

## Sample
You can clone the project and compile it yourself (it includes a sample), or you can check it out already compiled at Google Play

[![Google Play](http://developer.android.com/images/brand/en_generic_rgb_wo_45.png)](https://play.google.com/store/apps/details?id=com.dexafree.materiallistviewexample)

> Notice that it might not be the last version

## Collaborations
* [Fabio Hellmann](https://github.com/FHellmann): Great pull request that added MaterialStaggeredGridView, animations, and refactored a lot of code. Thank you very much, really! Also ported MaterialListView from ListView to RecyclerView.
* [Ricardo Romero](https://github.com/RicardoRB): Integrating Picasso to the library

## Credits
* Jake Wharton: [SwipeToDismissNOA](https://github.com/JakeWharton/SwipeToDismissNOA)
* Romain Guy: The sand picture provided as example was taken from one of his projects

## License

MaterialList is licensed under MIT License, which means that is Open Source and free to use and modify, you just have to.

```
The MIT License (MIT)

Copyright (c) 2014 Dexafree

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
```
