FontAwesome-Vaadin
==================

Do you want some of the newer FontAwesome icons not currently packaged in Vaadin? This addon implements an enum to include them and enhance the Vaddin functionality. 

The enum implements com.vaadin.server.FontIcon, so it works just like the existing implementation with a few modifications. You can use setIcon() with it as expected, but also included is a getLabel() method that returns a label with all of the fancy FontAwesome modifier classes available. You can chain methods like setSize2x(), setBorder(), setPullLeft(), spin(), etc. to set the modifiers. 

This addon is currently packaged with FontAwesome 4.1.0.

Vaadin Directory: http://vaadin.com/addon/fontawesomelabel


Code Example
============

```java
import com.kbdunn.vaadin.addons.fontawesome.FontAwesome;

// 3x Large Spinning Cog!
content.addComponent(FontAwesome.COG.getLabel().setSize3x().spin());

// Stack icons
content.addComponent(FontAwesome.TWITTER.getLabel().stack(FontAwesome.SQUARE_O).setSize3x());

// Stack icons with separate modifiers
content.addComponent(FontAwesome.HDD_O.getLabel().stack(FontAwesome.EXCLAMATION.getLabel().inverseColor()).setSize3x().reverseStackSize());

// Set the icon of another Vaadin component
button.setIcon(FontAwesome.SMILE_O);
```
