#FontAwesome-Vaadin

Do you want some of the newer FontAwesome icons not currently packaged in Vaadin? This addon implements an enum to include them and enhance the Vaddin functionality. 

The enum implements `com.vaadin.server.FontIcon`, so it works just like the existing implementation with a few modifications. You can use `setIcon()` with it as expected, but also included is a `getLabel()` method that returns a label with all of the fancy FontAwesome modifier classes available. You can chain methods like `setSize2x()`, `setBorder()`, `setPullLeft()`, `spin()`, etc. to set the modifiers. 

This addon is currently packaged with FontAwesome 4.2.0. To use it add the JAR file to your classpath. It may be necessary to re-compile your theme.

Vaadin Directory: http://vaadin.com/addon/fontawesomelabel


##Code Example

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

##Troubleshooting

This addon leverages Vaadin's custom manifest attribute `Vaadin-Stylesheets`, which updates the `addons.scss` file in your theme directory with the stylesheets required by this (and other) addons. I've personally had trouble with the Eclipse plugin executing this process reliably. If you have the same issue you may need to:

  1.  Run the process manually to update `addons.scss`. You'll need to find the location of `vaadin-server-xxx.jar` (it may be in your Ivy cache) and run this command:
	```bash
	java -cp /path/to/vaadin-server-xxx.jar;/path/to/font-awesome-label-1.1.3.jar com.vaadin.server.themeutils.SASSAddonImportFileCreator /path/to/VAADIN/themes/mythemedir
	```
      
      This will scan addons on your classpath (only font-awesome-label-1.1.3.jar in the example above) and will update `addons.scss` to look like the example in option 2.
  2.  ...Or just edit `addons.scss` manually. It will be overwritten the next time the automatic process runs (if it ever does), but this will turn the lights on. The file should have lines to `@import` and `@include` the stylesheet like this:
      ```scss
      /* This file is automatically managed and will be overwritten from time to time. */
      /* Do not manually edit this file. */
      
      /* Provided by font-awesome-label-1.1.4.jar */
      @import "../../../VAADIN/addons/font-awesome-4.2.0/css/_font-awesome.scss";
      
      /* Import and include this mixin into your project theme to include the addon themes */
      @mixin addons {
      	@include _font-awesome;
      }
      ```

If you've upgraded from an older version of Vaadin (prior to 7.1) you may also need to update the `styles.scss` file for your theme. It should include the `addons.scss` mixin like this:

```scss
@import "addons.scss";
@import "mytheme.scss";

.mytheme {
	@include addons;
	@include mytheme;
}
```
