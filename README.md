FontAwesome-Vaadin
==================

Vaadin add-on that allows you to add FontAwesome icons to your applications with modifiers like stack, spin, and flip.


Code Example:

import com.kbdunn.vaadin.addons.fontawesome.FontAwesome;

// 3x Large Spinning Cog!
content.addComponent(FontAwesome.COG.getLabel().setSize3x().spin());

// Stack icons
content.addComponent(FontAwesome.TWITTER.getLabel().stack(FontAwesome.SQUARE_O).setSize3x());

// Stack icons with separate modifiers
content.addComponent(FontAwesome.HDD_O.getLabel().stack(FontAwesome.EXCLAMATION.getLabel().inverseColor()).setSize3x().reverseStackSize());

// Set the icon of another Vaadin component
button.setIcon(FontAwesome.SMILE_O);
