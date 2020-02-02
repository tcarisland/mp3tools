package org.tcarisland.tools.i18n;

import java.io.IOException;
import java.io.InputStream;
import java.util.Locale;
import java.util.Map;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Mp3ToolsLabel {

	public static String FILENAME = "LabelsList";

	public static void main(String args[]) {
		try {
			ObjectMapper mapper = new ObjectMapper();
			InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(String.format("%s.json", FILENAME));
			Map<String, Map<Locale, String>> labelMap = mapper.readValue(inputStream, new TypeReference<Map<String, Map<Locale, String>>>() {});
			labelMap.keySet().stream().forEach(u -> System.out.println(u));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
