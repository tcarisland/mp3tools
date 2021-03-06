package org.tcarisland.tools;

import java.awt.Color;

import javax.swing.JTextField;

import org.jaudiotagger.tag.id3.AbstractID3v2Tag;
import org.tcarisland.tools.utils.TagFrame;

public class Mp3TagTextField extends JTextField {

  private static final long serialVersionUID = 1L;
  private TagFrame tagFrame;

  public Mp3TagTextField(TagFrame tagFrame, AbstractID3v2Tag tag) {
    super(tagFrame.isNotEmpty(tag) ? "" + tagFrame.getExtractor().extractTagData(tag) : "");
    this.setTagFrame(tagFrame);
    setEditable(tagFrame.getUpdater() != null);
    this.setOpaque(true);
    this.setBackground(isEditable() ? Color.WHITE : new Color(0xEF, 0xEF, 0xEF));
  }

  public TagFrame getTagFrame() {
    return tagFrame;
  }

  public void setTagFrame(TagFrame tagFrame) {
    this.tagFrame = tagFrame;
  }

}
