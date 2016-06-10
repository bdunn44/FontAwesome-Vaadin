package com.kbdunn.vaadin.addons.fontawesome.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class GenerateEnum {

	private static final String SOURCE = "http://fortawesome.github.io/Font-Awesome/cheatsheet/";
	private static final String ENUM_FILE = "src/com/kbdunn/vaadin/addons/fontawesome/FontAwesome.java";
	private static final String ENUM_ENTRY_REGEX = "\\t[A-Z0-9_]+\\(\\\"fa-.+\\),";
	
	public static void main(String[] args) {
			writeFile(getIcons());
			System.out.println("Done.");
	}
	
	private static Map<String, String> getIcons() {
		Map<String, String> icons = new LinkedHashMap<String, String>();
		try {
			Document html = Jsoup.connect(SOURCE).get();
			String desc = html.getElementsByClass("jumbotron").get(0).getElementsByTag("p").get(0).ownText();
			System.out.println("Scraping icon data from '" + desc + "' [" + SOURCE + "]....");
			
			// Loop through icons, get class and hex, add to map
			String cssClass, hex;
			for (Element e : html.getElementsByClass("row").get(1).children()) { // Cheatsheet has only 1 .row element
				cssClass = e.ownText();
				hex = e.getElementsByClass("text-muted").last().ownText().replace("[&#x", "").replace(";]", "");
				icons.put(cssClass, hex);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("\tRead " + icons.size() + " icons.");
		return icons;
	}
	
	private static void writeFile(Map<String, String> icons) {
		System.out.println("Updating " + ENUM_FILE + "....");
		File infile = new File(ENUM_FILE);
		File outfile = new File(ENUM_FILE + ".tmp");
		BufferedReader reader = null;
		BufferedWriter writer = null;
		try {
			reader = new BufferedReader(new FileReader(infile));
			writer = new BufferedWriter(new FileWriter(outfile));
			String inline = null;
			int current = 0;
			int enumstart = 0;
			while ((inline = reader.readLine()) != null) {
				current++;
				if (enumstart > 0) {
					// Check if we're still in the enum
					if (!inline.matches(ENUM_ENTRY_REGEX)) {
						System.out.println("\tIcons end on line " + current + ". "
								+ "Replaced " + (current - enumstart + 1) + " icons.");
						enumstart = 0;
					}
				} else if (inline.contains("public enum FontAwesome")) {
					// Enum entries start on next line
					enumstart = current + 1;
					System.out.println("\tIcons start on line " + enumstart + ".");
					writer.write(inline + "\n");
					
					// Write the new enum entries
					writeEnumLines(writer, icons);
				} else {
					// Copy the rest of the file
					writer.write(inline + "\n");
				}
			}
			
			writer.close();
			reader.close();
			
			// Delete FontAwesome.java
			if (!infile.delete()) 
				throw new IOException("Could not delete " + infile);
			
			// Rename .tmp file
			if (!outfile.renameTo(infile)) 
				throw new IOException("Could not rename " + outfile + " to " + infile);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private static void writeEnumLines(BufferedWriter writer, Map<String, String> icons) throws IOException {
		String outline = null;
		int outlines = 0;
		int count = icons.size();
		for (Entry<String, String> icon : icons.entrySet()) {
			outline = "\t" + icon.getKey().toUpperCase().replace("FA-",  "").replace("-", "_").replace("500PX", "_500PX");
			outline += "(\"" + icon.getKey() + "\", ";
			outline += "0x" + icon.getValue();
			outline += ")" + (++outlines == count ? ";" : ",") + "\n";
			
			writer.write(outline);
		}
	}
}
