package org.tcarisland.tools.utils;

import org.jaudiotagger.tag.FieldKey;
import org.jaudiotagger.tag.id3.AbstractID3v2Tag;

public class TagFrame {

  private final FieldKey key;
  private final String name;
  private final TagFrameExtractor extractor;
  private final TagFrameUpdater updater;

  public TagFrame(FieldKey key, String name, TagFrameExtractor extractor, TagFrameUpdater updater) {
    this.key = key;
    this.name = name;
    this.extractor = extractor;
    this.updater = updater;
  }

  public boolean isNotEmpty(AbstractID3v2Tag tag) {
    return !tag.getFields(key).isEmpty();
  }

  public String getName() {
    return name;
  }

  public TagFrameExtractor getExtractor() {
    return extractor;
  }

  public TagFrameUpdater getUpdater() {
    return updater;
  }

  public FieldKey getKey() {
    return key;
  }

}