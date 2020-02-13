package org.tcarisland.tools.utils;

import org.jaudiotagger.tag.id3.AbstractID3v2Tag;

public interface TagFrameExtractor {

  String extractTagData(AbstractID3v2Tag tag);

}