package org.tcarisland.tools.i18n;

public enum Label {
	FILE("general.file"),
	EDIT("general.edit"),
	OPEN("general.open"),
	EXIT("general.exit"),
	ABOUT("general.about"),
	COPY("general.copy"),
  SAVE("general.save"),
  SAVE_AS("general.save_as"),
	PASTE("general.paste");

	private final String key;

	public String getLabel() {
		return Labels.getLabel(key);
	}

	Label(String key) {
		this.key = key;
	}

}
