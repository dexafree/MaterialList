# MaterialListView Changelog

## 2.5.0
* Now Cards that have an Image (Such as BigImageCard or BigImageButtonsCard) provide methods to choose the ScaleType (CENTER_CROP, FIT_XY...). By default is set to `CENTER_CROP`

## 2.4.6
* Now `BigImageCard` and `BigImageButtonsCard` center the image and crop it.

## 2.4.5
* Updated RecyclerView library version as requested: https://github.com/dexafree/MaterialList/issues/83
* Changed ic_launcher name as requested: https://github.com/dexafree/MaterialList/issues/81

## 2.4.4
* Prevented NPE when ButtonsCard have no text

## 2.4.3
* Button color change fix (https://github.com/dexafree/MaterialList/issues/69)

## 2.4.2
* NPE prevention
* Added the `MaterialListView.addAtStart(Card)` method (https://github.com/dexafree/MaterialList/issues/67)

## 2.4.1
* NPE Fix (https://github.com/dexafree/MaterialList/issues/66)

## 2.4.0
* Added OnItemClickListener
* Fixed the OnDismiss callback not working
* Updated wiki documentation

## 2.3.2
* Bugfixes

## 2.3.1
* Added `MaterialListView.clear()` method, which clears the adapter

## 2.3.0
* Ported to RecyclerView (Thanks [Fabio Hellmann](https://github.com/FHellmann))
* Added the ability to define columns as an XML Attribute

## 2.2.0
* Integration with Picasso (Thanks [Ricardo Romero](https://github.com/RicardoRB))

## 2.2.0
* Fixed compilation bugs
* Library structure again in a single module
