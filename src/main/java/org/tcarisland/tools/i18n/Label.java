package org.tcarisland.tools.i18n;

import java.util.Locale;

public enum Label {
	FILE("general.file"),
	EDIT("general.edit"),
	OPEN("general.open"),
	EXIT("general.exit"),
	ABOUT("general.about");
	
	private final String key;

	public String getLabel() {
		return Labels.getLabel(key);
	}
	
	Label(String key) {
		this.key = key;
	}
}
