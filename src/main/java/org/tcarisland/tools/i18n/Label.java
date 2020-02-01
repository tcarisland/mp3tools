package org.tcarisland.tools.i18n;

import java.io.Serializable;
import java.util.Locale;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class Label implements Serializable {

	private static final long serialVersionUID = 1L;

	private String key;
	private Map<Locale, String> values;
	
	public Label(@JsonProperty("key") String key, @JsonProperty("values") Map<Locale, String> values) {
		this.key = key;
		this.values = values;
	}
	
}
