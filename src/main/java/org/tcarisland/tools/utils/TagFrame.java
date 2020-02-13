package org.tcarisland.tools.utils;

import org.jaudiotagger.tag.FieldKey;
import org.jaudiotagger.tag.id3.AbstractID3v2Tag;

import lombok.Data;

@Data
public class TagFrame {

  private final FieldKey key;
  private final String name;
  private final TagFrameExtractor extractor;
  private final TagFrameUpdater updater;

  public boolean isNotEmpty(AbstractID3v2Tag tag) {
    return !tag.getFields(key).isEmpty();
  }

}