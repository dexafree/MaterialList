# MaterialListView Changelog

# MaterialList v3

## 3.1.2
* More methods: Add the string resource option to TextViewAction; Open add with scroll option in MaterialListAdapter
* BugFix: Fixed the scroll behaivor with SwipeDismissLayout
* Thanks to [Fabio Hellmann](https://github.com/FHellmann)

## 3.1.1
* Updated [RecyclerViewAnimators](https://github.com/wasabeef/recyclerview-animators) for latest RecyclerView compatibility

## 3.1.0
* [Styles, Templates, Empty View, Simplifications, Code Refactoring, Animations, Actions](https://github.com/dexafree/MaterialList/pull/98)
* Thanks to [Fabio Hellmann](https://github.com/FHellmann)

## 3.0.1
* Clear method now divided:
    * `clear` removes the dismissible Cards.
    * `clearAll` dismisses all the Cards.

## 3.0.0
* **Builder Pattern**: to easily create new Cards
* **Observer Pattern**: to replace the Eventbus Otto with native Java Observers
* **Library shrinking**: by replacing Libraries with an easy java implementation
* **Java Doc**: added to understand the code much easier and faster
* Thanks to [Fabio Hellmann](https://github.com/FHellmann)

# MaterialList v2

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
