package org.tcarisland.tools;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.Border;

import org.apache.commons.lang3.StringUtils;
import org.tcarisland.tools.utils.Mp3TagList;
import org.tcarisland.tools.utils.TagFrame;

import com.mpatric.mp3agic.ID3v2;
import com.mpatric.mp3agic.ID3v23Tag;
import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.Mp3File;
import com.mpatric.mp3agic.NotSupportedException;
import com.mpatric.mp3agic.UnsupportedTagException;

import lombok.Getter;

public class Mp3ToolsMainPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	private static Mp3ToolsMainPanel handle = null;

	public static Mp3ToolsMainPanel getInstance() {
		handle = handle != null ? handle : new Mp3ToolsMainPanel();
		return handle;
	}

	final Dimension labelSize = new Dimension(150, 20);

  private JScrollPane scrollPane;

  private Mp3File mp3File;

  @Getter
  private List<Mp3TagTextField> textFields = new ArrayList<>();

	public void setTag(ID3v2 tag) {
	  if(scrollPane != null) {
	    this.remove(scrollPane);
	    scrollPane.invalidate();
	  }
		this.invalidate();
		textFields.clear();
		List<TagFrame<?>> data;
    try {
      data = Mp3TagList.getTagList(tag);
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
      return;
    }
		this.setLayout(new BorderLayout());
		JPanel contentPanel = new JPanel();
		contentPanel.setLayout(new GridLayout(data.size(), 1));
		Border border = BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(new Color(0xDA, 0xDA, 0xDA)),BorderFactory.createEmptyBorder(5, 5, 5, 5));
		for(TagFrame<?> tagFrame : data) {
			JPanel panel = new JPanel(new BorderLayout());
      Mp3TagTextField textField = new Mp3TagTextField(tagFrame, tag);
      textFields.add(textField);
			JLabel label = new JLabel("" + tagFrame.getName());
			JLabel typeLabel = new JLabel(tagFrame.getGenericType().getSimpleName());
			List<JComponent> comps = Arrays.asList(label, textField, typeLabel);
			comps.forEach(u -> u.setBorder(border));
      comps.stream().filter(u -> u instanceof JLabel).forEach(x -> {
        x.setSize(labelSize);
        x.setPreferredSize(labelSize);
        x.setMinimumSize(labelSize);
      });
			panel.add(label, BorderLayout.WEST);
			panel.add(textField, BorderLayout.CENTER);
			panel.add(typeLabel, BorderLayout.EAST);
			contentPanel.add(panel);
		}
		scrollPane = new JScrollPane(contentPanel);
		this.add(scrollPane, BorderLayout.CENTER);
		scrollPane.revalidate();
		this.revalidate();
		this.repaint();
	}

	public void onOpenFile(File file) {
		System.out.println(file.getAbsolutePath());
		try {
			mp3File = new Mp3File(file);
			setTag(mp3File.getId3v2Tag());
		} catch (UnsupportedTagException | InvalidDataException | IOException e) {
			e.printStackTrace();
		}
	}

  public void onSaveFile(File file) {
    ID3v2 tag = mp3File.getId3v2Tag();
    tag = tag != null ? tag : new ID3v23Tag();
    tag.setComment("This is another comment " + new Date());
    for(Mp3TagTextField textField : textFields) {
      TagFrame<?> tagFrame = textField.getTagFrame();
      if(tagFrame.getUpdater() != null) {
        if(tagFrame.getGenericType().equals(String.class)) {
          try {
            @SuppressWarnings("unchecked")
            TagFrame<String> stringTagFrame = (TagFrame<String>) textField.getTagFrame();
            tag = stringTagFrame.getUpdater().updateTagData(tag, textField.getText());
          } catch(ClassCastException e) {
            e.printStackTrace();
          }
        }
      }
    }
    mp3File.removeId3v2Tag();
    mp3File.setId3v2Tag(tag);
    try {
      String absolutePath = file.getAbsolutePath();
      if(!StringUtils.endsWith(absolutePath, ".mp3")) {
        absolutePath = absolutePath + ".mp3";
      }
      mp3File.save(absolutePath);
    } catch (NotSupportedException | IOException e) {
      e.printStackTrace();
    }
  }

}
