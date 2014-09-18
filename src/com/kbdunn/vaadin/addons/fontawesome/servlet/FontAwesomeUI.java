package com.kbdunn.vaadin.addons.fontawesome.servlet;

import com.kbdunn.vaadin.addons.fontawesome.FontAwesome;
import com.vaadin.server.VaadinRequest;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

public class FontAwesomeUI extends UI {
	private static final long serialVersionUID = 1L;
	
	public FontAwesomeUI() { }
	
	@Override
	protected void init(VaadinRequest request) {
		VerticalLayout content = new VerticalLayout();
		content.setMargin(true);
		content.addComponent(new Label("<h1>FontAwesome Label</h1>", ContentMode.HTML));
		
		// 3x Large Spinning Cog!
		content.addComponent(FontAwesome.COG.getLabel().setSize3x().spin());

		// Stack icons
		content.addComponent(FontAwesome.TWITTER.getLabel().stack(FontAwesome.SQUARE_O).setSize3x());

		// Stack icons with separate modifiers
		content.addComponent(FontAwesome.HDD_O.getLabel().stack(FontAwesome.EXCLAMATION.getLabel().inverseColor()).setSize3x().reverseStackSize());

		// Set the icon of another Vaadin component
		Button button = new Button("Click here");
		button.setIcon(FontAwesome.SMILE_O);
		content.addComponent(button);
		
		setContent(content);
	}
}
