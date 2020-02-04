package org.tcarisland.tools.utils;

import java.util.Collection;

import org.apache.commons.lang3.StringUtils;

import com.mpatric.mp3agic.ID3v2;

import lombok.Data;

@Data
public class TagFrame<E> {

  public static interface TagFrameExtractor<E> { E extractTagData(ID3v2 tag); }
  public static interface TagFrameUpdater<E> { ID3v2 updateTagData(ID3v2 tag, String data); }

  private final String name;
  private final TagFrameExtractor<E> extractor;
  private final TagFrameUpdater<E> updater;
  private final Class<?> genericType;

  public boolean isNotEmpty(ID3v2 tag) {
    Object data = extractor.extractTagData(tag);
    if(data instanceof String) {
      return StringUtils.isNotEmpty((String) data);
    } else if (data instanceof Integer) {
      return (int) data != -1;
    } else if(data instanceof Boolean) {
      return data != null;
    } else if(data instanceof Collection<?>) {
      return data != null;
    } else if(data instanceof byte[]) {
      return data != null && ((byte[]) data).length > 0;
    } else {
      return false;
    }
  }

}