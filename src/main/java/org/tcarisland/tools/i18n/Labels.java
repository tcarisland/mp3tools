package org.tcarisland.tools.i18n;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;

public class Labels {
	
	private static Locale defaultLocale = Locale.ENGLISH;
	private static Locale currentLocale = defaultLocale;
	private static Set<Locale> supportedLocales = (Set<Locale>) Arrays.asList("no", "en").stream().map(u -> Locale.forLanguageTag(u)).collect(Collectors.toSet());
	
	public static void init(Locale locale) {
		currentLocale = supportedLocales.contains(locale) ? locale : defaultLocale;
	}
	
	public static String getLabel(String key) {
		return getLabel(key, currentLocale);
	}
	
	private static String getLabelWithEncoding(String key, Locale locale) {
		try {
			return new String(ResourceBundle.getBundle("Labels", locale).getString(key).getBytes("ISO-8859-1"), "UTF-8");
		} catch (MissingResourceException |UnsupportedEncodingException e) {
			return null;
		}
	}

	private static String getLabelWithLocale (String key, Locale locale) {
		try {
			return ResourceBundle.getBundle("Labels", locale).getString(key);
		} catch (MissingResourceException e) {
			return null;
		}
	}

	private static String getLabelWithDefaultLocale (String key) {
		try {
			return ResourceBundle.getBundle("Labels", defaultLocale).getString(key);
		} catch (MissingResourceException e) {
			return null;
		}
	}
	
	private static String getLabelFromKey(String key) {
		int index = key.lastIndexOf('.');
		index += index < (key.length() - 1) ? 1 : 0;
		return StringUtils.capitalize(key.substring(index));
	}
	
	public static String getLabel(String key, Locale locale) {
		String label = getLabelWithEncoding(key, locale);
		label = StringUtils.isNotEmpty(label) ? label : getLabelWithLocale(key, locale);
		label = StringUtils.isNotEmpty(label) ? label : getLabelWithDefaultLocale(key);
		return StringUtils.isNotEmpty(label) ? label : getLabelFromKey(key);
	}


}
