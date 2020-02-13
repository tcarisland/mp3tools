package org.tcarisland.tools.utils;

import org.jaudiotagger.tag.id3.AbstractID3v2Tag;

public interface TagFrameUpdater {
  AbstractID3v2Tag updateTagData(AbstractID3v2Tag tag, String data);
}