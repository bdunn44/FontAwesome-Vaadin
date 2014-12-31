package com.kbdunn.vaadin.addons.fontawesome.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class GenerateEnum {

	public static void main(String[] args) {
		Map<String, String> icons = new LinkedHashMap<String, String>();
		try {
			Document html = Jsoup.connect("http://fortawesome.github.io/Font-Awesome/cheatsheet/").get();
			Elements cheatsheet = html.getElementsByClass("row"); // Cheatsheet has only 1 "row"
			
			// Loop through icons, get class and hex, add to map
			String cssClass, hex;
			for (Element e : cheatsheet.get(0).getElementsByTag("div")) {
				cssClass = e.ownText();
				hex = e.getElementsByClass("muted").get(0).ownText().replace("[&#x", "").replace(";]", "");
				System.out.println(cssClass + " [" + hex.toString() + "]");
				icons.put(cssClass, hex);
			}
			
			writeFile(icons);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private static void writeFile(Map<String, String> icons) {
		File outfile = new File(ClassLoader.getSystemClassLoader().getResource(".").getPath() + "/enum.txt");
		BufferedWriter writer = null;
		try {
			writer = new BufferedWriter(new FileWriter(outfile));
			String line;
			for (Entry<String, String> icon : icons.entrySet()) {
				line = icon.getKey().toUpperCase().replace("FA-",  "").replace("-", "_");
				line += "(\"" + icon.getKey() + "\", ";
				line += "0x" + icon.getValue();
				line += ")," + String.format("%n");
				
				writer.write(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (writer != null) writer.close();
			} catch (Exception e) {
			    //Ignore
			}
	        }
	}
}
