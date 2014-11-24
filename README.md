# MaterialList [![Build Status](https://travis-ci.org/dexafree/MaterialList.svg?branch=master)](https://travis-ci.org/dexafree/MaterialList) [![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.github.dexafree/materiallist/badge.svg?style=flat)](https://maven-badges.herokuapp.com/maven-central/com.github.dexafree/materiallist) [![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-MaterialList-brightgreen.svg?style=flat)](https://android-arsenal.com/details/1/1161)

MaterialList is an Android library created to help all Android developers get the beautiful CardViews that Google shows at its [official design specifications](http://www.google.com/design/spec/components/cards.html#cards-usage).

Provided as a ListView extension, it can receive a list of Cards (stored in a CardList, provided by the library) and show them accordingly to the android style and design patterns.

It also has been developed while keeping extensibility in mind, which means that you are able to create your own card layouts and add them to the CardList without any pain (see examples below).

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

There are also some Cards that may show a Divider between the content and the buttons. For further reference, [read the Wiki page](https://github.com/dexafree/MaterialList/wiki/Dividers)

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
You will also be able to decide if a card should be dismissible or not, just by calling Card.setCanDismiss().

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
    compile 'com.github.dexafree:materiallist:1.1.0'
}
```

## Sample
You can clone the project and compile it yourself (it includes a sample), or you can check it out already compiled at Google Play

[![Google Play](http://developer.android.com/images/brand/en_generic_rgb_wo_45.png)](https://play.google.com/store/apps/details?id=com.dexafree.materiallistviewexample)

> Notice that it might not be the last version

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