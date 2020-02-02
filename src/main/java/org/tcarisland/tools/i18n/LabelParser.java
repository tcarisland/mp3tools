package org.tcarisland.tools.i18n;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Locale;
import java.util.Map;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class LabelParser {

	HashSet<Locale> locales;
	public static String FILENAME = "Labels";
	
	public LabelParser() {
		try {
			ObjectMapper mapper = new ObjectMapper();
			InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(String.format("%s.json", FILENAME));
			Map<String, Map<Locale, String>> labelMap = mapper.readValue(inputStream, new TypeReference<Map<String, Map<Locale, String>>>() {});
			labelMap.keySet().stream().forEach(u -> System.out.println(u));
			locales = new HashSet<Locale>();
			labelMap.values().stream().forEach(u -> locales.addAll(u.keySet()));
			Map<Locale, PrintWriter> outputMap = new HashMap<Locale, PrintWriter>();
			for (Locale locale : locales) {
				File file = new File(String.format("src/main/resources/%s_%s.properties", FILENAME, locale.getLanguage()));
				OutputStream os = new FileOutputStream(file);
				outputMap.put(locale, new PrintWriter(new OutputStreamWriter(os, "UTF-8")));
			}
			labelMap.keySet().stream().forEach(key -> labelMap.get(key).keySet().stream().forEach(locale -> {
				outputMap.get(locale).println(String.format("%s=%s", key, labelMap.get(key).get(locale)));
			}));
			locales.stream().forEach(u -> outputMap.get(u).close());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String args[]) {
		new LabelParser();
	}

}
