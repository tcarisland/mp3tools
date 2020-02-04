package org.tcarisland.tools;

import com.mpatric.mp3agic.ID3v2;

public interface TagFrameUpdater<E> {
  public abstract ID3v2 update(ID3v2 tag, E value);
}