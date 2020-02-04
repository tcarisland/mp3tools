package org.tcarisland.tools;

import java.awt.Color;

import javax.swing.JTextField;

import org.tcarisland.tools.utils.TagFrame;

import com.mpatric.mp3agic.ID3v2;

import lombok.Getter;

public class Mp3TagTextField extends JTextField {

  private static final long serialVersionUID = 1L;
  @Getter
  private TagFrame<?> tagFrame;

  public Mp3TagTextField(TagFrame<?> tagFrame, ID3v2 tag) {
    super(tagFrame.isNotEmpty(tag) ? "" + tagFrame.getExtractor().extractTagData(tag) : "");
    this.tagFrame = tagFrame;
    setEditable(tagFrame.getUpdater() != null);
    this.setOpaque(true);
    this.setBackground(isEditable() ? Color.WHITE : new Color(0xEF, 0xEF, 0xEF));
  }

}
