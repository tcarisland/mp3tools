package org.tcarisland.tools;

import java.io.IOException;
import java.io.InputStream;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

import org.junit.Test;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class LabelsTest {

	public static String FILENAME = "Labels";
	
	@Test
	public void printLabels() {
		System.out.println("Labels:\n");
		try {
			ObjectMapper mapper = new ObjectMapper();
			InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(String.format("%s.json", FILENAME));
			Map<String, Map<Locale, String>> labelMap = mapper.readValue(inputStream, new TypeReference<Map<String, Map<Locale, String>>>() {});
			labelMap.keySet().stream().forEach(key -> {
				Map<Locale, String> label = labelMap.get(key);
				String labelString = String.join("\n", label
						.keySet()
						.stream()
						.map(locale -> String.format("\t%s : %s", locale, label.get(locale)))
						.collect(Collectors.toList())
						);
				System.out.println(key);
				System.out.println(labelString);
			});
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("");
	}

	@Test
	public void printLabelNames() {
		System.out.println("Label names:\n");
		try {
			ObjectMapper mapper = new ObjectMapper();
			InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(String.format("%s.json", FILENAME));
			Map<String, Map<Locale, String>> labelMap = mapper.readValue(inputStream, new TypeReference<Map<String, Map<Locale, String>>>() {});
			System.out.println(String.join(",\n", labelMap.keySet().stream().map(u -> String.format("\"%s\"", u)).collect(Collectors.toList())));
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("");
	}
	
}
