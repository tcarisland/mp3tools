package org.tcarisland.tools.i18n;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class LabelParser {

	List<Label> labels;
	HashSet<Locale> locales;
	private static String FILENAME = "Labels";

	public LabelParser() {
		try {
			ObjectMapper mapper = new ObjectMapper();
			InputStream inputStream = Thread.currentThread().getContextClassLoader()
					.getResourceAsStream(String.format("%s.json", FILENAME));
			if (inputStream != null && inputStream.available() > 0) {
				labels = mapper.readValue(inputStream, new TypeReference<List<Label>>() {
				});
			}
			locales = new HashSet<Locale>();
			labels.stream().forEach(u -> u.getValues().keySet().stream().forEach(x -> locales.add(x)));
			Map<Locale, PrintWriter> outputMap = new HashMap<Locale, PrintWriter>();
			for (Locale locale : locales) {
				File file = new File(
						String.format("src/main/resources/%s_%s.properties", FILENAME, locale.getLanguage()));
				outputMap.put(locale, new PrintWriter(file));
			}
			for (Label label : labels) {
				for (Locale locale : label.getValues().keySet()) {
					outputMap.get(locale)
							.println(String.format("%s=%s", label.getKey(), label.getValues().get(locale)));
				}
			}
			for (Locale locale : locales) {
				outputMap.get(locale).close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String args[]) {
		new LabelParser();
	}

}
