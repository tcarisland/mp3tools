package org.tcarisland.tools.i18n;

import java.util.Arrays;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.stream.Collectors;

public class Labels {
	
	private static Locale defaultLocale = Locale.ENGLISH;
	private static Locale currentLocale = defaultLocale;
	private static Set<Locale> supportedLocales = (Set<Locale>) Arrays.asList("no", "en").stream().map(u -> Locale.forLanguageTag(u)).collect(Collectors.toSet());
	private static ResourceBundle resourceBundle = ResourceBundle.getBundle("Labels", defaultLocale);
	
	public static void init(Locale locale) {
		currentLocale = supportedLocales.contains(locale) ? locale : defaultLocale;
		resourceBundle = ResourceBundle.getBundle("Labels", currentLocale);
	}
	
	public static String getLabel(String key) {
		return resourceBundle.getString(key);
	}
	
	public static String getLabel(Locale locale, String key) {
		return ResourceBundle.getBundle("Labels", locale).getString(key);
	}
	
	public static void main(String args[]) {
		Arrays.asList("no", "en").stream().forEach(u -> {
			Locale locale = Locale.forLanguageTag(u);
			String label = Labels.getLabel(locale, "file");
			System.out.printf("%s %s \n", locale, label);
			
		});
	}

}
