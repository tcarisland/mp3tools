package org.tcarisland.tools;

import org.jaudiotagger.tag.id3.AbstractID3v2Tag;

public interface TagFrameUpdater {

  public abstract AbstractID3v2Tag update(AbstractID3v2Tag tag, String value);

}