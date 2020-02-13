package org.tcarisland.tools.utils;

import java.util.ArrayList;
import java.util.List;

import org.jaudiotagger.tag.FieldDataInvalidException;
import org.jaudiotagger.tag.FieldKey;
import org.jaudiotagger.tag.KeyNotFoundException;
import org.jaudiotagger.tag.TagField;
import org.jaudiotagger.tag.id3.AbstractID3v2Tag;

public class Mp3TagList {

  public static List<TagFrame> getTagList(AbstractID3v2Tag tag) throws ClassNotFoundException {
	  List<TagFrame> list = new ArrayList<TagFrame>();
    for(FieldKey fieldKey : FieldKey.values()) {
      for(TagField tagField : tag.getFields(fieldKey)) {
        if(!tagField.isBinary()) {
          TagFrameExtractor extractor = u -> u.getFirst(fieldKey);
          TagFrameUpdater updater = (a, b) -> {
            try {
              a.setField(fieldKey, b);
            } catch (KeyNotFoundException | FieldDataInvalidException e) {
              e.printStackTrace();
            } return a;
          };
          TagFrame tagFrame = new TagFrame(fieldKey, tagField.getId(), extractor, updater);
          list.add(tagFrame);
        }
      }
    }
    return list;
	}

}
