package org.tcarisland.tools;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
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
import org.jaudiotagger.audio.exceptions.InvalidAudioFrameException;
import org.jaudiotagger.audio.exceptions.ReadOnlyFileException;
import org.jaudiotagger.audio.mp3.MP3File;
import org.jaudiotagger.tag.FieldDataInvalidException;
import org.jaudiotagger.tag.FieldKey;
import org.jaudiotagger.tag.KeyNotFoundException;
import org.jaudiotagger.tag.TagException;
import org.jaudiotagger.tag.id3.AbstractID3v2Tag;
import org.jaudiotagger.tag.id3.ID3v23Tag;
import org.tcarisland.tools.utils.Mp3TagList;
import org.tcarisland.tools.utils.TagFrame;

public class Mp3ToolsMainPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	private static Mp3ToolsMainPanel handle = null;

	public static Mp3ToolsMainPanel getInstance() {
		handle = handle != null ? handle : new Mp3ToolsMainPanel();
		return handle;
	}

	final Dimension labelSize = new Dimension(150, 20);

	private JScrollPane scrollPane;

	private MP3File mp3File;

	private List<Mp3TagTextField> textFields = new ArrayList<>();

	public List<Mp3TagTextField> getTextFields() {
	  return this.textFields;
	}

	public void setTag(AbstractID3v2Tag tag) {
		if (scrollPane != null) {
			this.remove(scrollPane);
			scrollPane.invalidate();
		}
		this.invalidate();
		textFields.clear();
		List<TagFrame> data;
		try {
			data = Mp3TagList.getTagList(tag);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return;
		}
		this.setLayout(new BorderLayout());
		JPanel contentPanel = new JPanel();
		contentPanel.setLayout(new GridLayout(data.size(), 1));
		Border border = BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(new Color(0xDA, 0xDA, 0xDA)),
				BorderFactory.createEmptyBorder(5, 5, 5, 5));
		for (TagFrame tagFrame : data) {
			JPanel panel = new JPanel(new BorderLayout());
			Mp3TagTextField textField = new Mp3TagTextField(tagFrame, tag);
			textFields.add(textField);
			JLabel label = new JLabel("" + tagFrame.getName());
			List<JComponent> comps = Arrays.asList(label, textField);
			comps.forEach(u -> u.setBorder(border));
			comps.stream().filter(u -> u instanceof JLabel).forEach(x -> {
				x.setSize(labelSize);
				x.setPreferredSize(labelSize);
				x.setMinimumSize(labelSize);
			});
			panel.add(label, BorderLayout.WEST);
			panel.add(textField, BorderLayout.CENTER);
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
			mp3File = new MP3File(file);
			setTag(mp3File.getID3v2Tag());
		} catch (IOException | TagException | ReadOnlyFileException | InvalidAudioFrameException e) {
			e.printStackTrace();
		}
	}

	public void onSaveFile(File file) {
		AbstractID3v2Tag tag = mp3File.getID3v2Tag();
		tag = tag != null ? tag : new ID3v23Tag();
		try {
			tag.setField(FieldKey.COMMENT, "This is another comment " + new Date());
			for (Mp3TagTextField textField : textFields) {
				TagFrame tagFrame = textField.getTagFrame();
				if (tagFrame.getUpdater() != null) {
					try {
						TagFrame stringTagFrame = textField.getTagFrame();
						tag = stringTagFrame.getUpdater().updateTagData(tag, textField.getText());
					} catch (ClassCastException e) {
						e.printStackTrace();
					}
				}
			}
			mp3File.setID3v1Tag(null);
			mp3File.setID3v2Tag(null);
			mp3File.setID3v2Tag(tag);
			String absolutePath = file.getAbsolutePath();
			if (!StringUtils.endsWith(absolutePath, ".mp3")) {
				absolutePath = absolutePath + ".mp3";
			}
			Files.deleteIfExists(Paths.get(absolutePath));
			Files.copy(mp3File.getFile().toPath(), Paths.get(absolutePath));
			mp3File.save(new File(absolutePath));
		} catch (IOException | KeyNotFoundException | FieldDataInvalidException e1) {
			e1.printStackTrace();
		}
	}

}
