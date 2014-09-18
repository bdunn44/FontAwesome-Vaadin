package com.kbdunn.vaadin.addons.fontawesome.servlet;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinServlet;

@WebServlet(value = "/*", asyncSupported = true)
@VaadinServletConfiguration(productionMode = false, ui = FontAwesomeUI.class)
public class VdnServlet extends VaadinServlet {
	private static final long serialVersionUID = 1L;
	
	
}
