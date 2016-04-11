#FontAwesome-Vaadin

Do you want the newer Font Awesome icons not currently packaged in Vaadin? This addon includes a replacement FontAwesome enum with the latest icons, and enhances their native functionality. 

The enum works just like the existing implementation, so  `setIcon()` works as you would expect. Also included is a `getLabel()` method, which returns a label component with all of the CSS classes that Font Awesome provides. This component allows you to add multiple modifiers to your icons in a one-liner fashion by chaining methods such as `stack()`, `spin()`, `setSize2x()`, `setBorder()`, etc.

This addon is currently packaged with FontAwesome 4.5.0. To use it add the JAR file to your classpath. It may be necessary to re-compile your theme. If you're having trouble using this addon please see the Troubleshooting section below.

The code written for this addon (FontAwesome, FontAwesomeLabel and HTML scraper) is licensed under the Apache 2.0 license. Font Awesome is licensed under the MIT and SIL OFL 1.1 licenses. See fontawesome.io/license for more information.

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

If you've upgraded from an older version of Vaadin (prior to 7.1) you may need to update the `styles.scss` file for your theme. It should include the `addons.scss` mixin like this:

```scss
@import "addons.scss";
@import "mytheme.scss";

.mytheme {
	@include addons;
	@include mytheme;
}
```

This addon leverages Vaadin's custom manifest attribute `Vaadin-Stylesheets`, which updates the `addons.scss` file in your theme directory with the stylesheets required by this addon. I've had trouble getting the Eclipse plugin to execute this process. If you have the same issue follow these steps:

  1.  Verify that your Eclipse `.project` file has the following `buildCommand` nodes:

	```xml
	<buildSpec>
		...
		<buildCommand>
			<name>com.vaadin.integration.eclipse.widgetsetBuilder</name>
			<arguments>
			</arguments>
		</buildCommand>
		<buildCommand>
			<name>com.vaadin.integration.eclipse.addonStylesBuilder</name>
			<arguments>
			</arguments>
		</buildCommand>
	</buildSpec>
	```
  2.  Verify that the "Suspend automatic addon theme scanning" property in the "Vaadin" page of your project properties is unchecked. If it's unchecked, try checking it > Apply > unchecking it > Ok. This fixed the problem for me. If it doesn't for you, continue to steps 2 and 3.
  3.  Run the process manually to update `addons.scss`. You'll need to find the location of `vaadin-server-xxx.jar` (it may be in your Ivy cache) and run this command:
	```bash
	java -cp /path/to/vaadin-server-xxx.jar;/path/to/font-awesome-label-1.1.4.jar com.vaadin.server.themeutils.SASSAddonImportFileCreator /path/to/VAADIN/themes/mythemedir
	```
      
      This will scan addons on your classpath (only font-awesome-label-1.1.4.jar in the example above) and will update `addons.scss` to look like the example in option 2.
  4.  ...Or just edit `addons.scss` manually. It will be overwritten the next time the automatic process runs (if it ever does), but this will turn the lights on. The file should have lines to `@import` and `@include` the stylesheet like this:
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
