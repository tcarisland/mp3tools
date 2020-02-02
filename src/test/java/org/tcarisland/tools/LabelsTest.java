package org.tcarisland.tools;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.junit.Test;
import org.tcarisland.tools.i18n.Labels;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
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
			List<String> labelNames = getLabelNames();
			labelNames.stream().forEach(u -> System.out.printf("\t%s\n", u));
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("");
	}
	
	@Test
	public void printLabelsUsingLabelsClass() {
		System.out.println("Labels using Labels class: \n");
		try {
			Set<Locale> locales = getLocales();
			List<String> labelNames = getLabelNames();
			for(Locale locale : locales) {
				System.out.printf("Setting locale : %s\n", locale);
				Labels.init(locale);
				for(String label : labelNames) {
					System.out.printf("\t%s : %s\n", label, Labels.getLabel(label));
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("");
	}
	
	private Map<String, Map<Locale, String>> getLabelMap() throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(String.format("%s.json", FILENAME));
		return mapper.readValue(inputStream, new TypeReference<Map<String, Map<Locale, String>>>() {});
	}
	
	private List<String> getLabelNames() throws JsonParseException, JsonMappingException, IOException {
		return getLabelMap()
				.keySet()
				.stream()
				.collect(Collectors.toList());
	}
	
	private Set<Locale> getLocales() throws JsonParseException, JsonMappingException, IOException {
		Map<String, Map<Locale, String>> labelMap = getLabelMap();
		return labelMap
				.keySet()
				.stream()
				.flatMap(u -> labelMap.get(u).keySet().stream())
				.collect(Collectors.toSet());
	}
	
}
