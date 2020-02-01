package org.tcarisland.tools.i18n;

import java.io.UnsupportedEncodingException;
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
		try {
			return new String(resourceBundle.getString(key).getBytes("ISO-8859-1"), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return resourceBundle.getString(key);
		}
	}
	
	public static String getLabel(Locale locale, String key) {
		return ResourceBundle.getBundle("Labels", locale).getString(key);
	}

}
