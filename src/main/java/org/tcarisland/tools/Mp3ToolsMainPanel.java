package org.tcarisland.tools;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import org.tcarisland.tools.utils.Mp3TagList;

import com.mpatric.mp3agic.ID3v2;
import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.Mp3File;
import com.mpatric.mp3agic.UnsupportedTagException;

public class Mp3ToolsMainPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private static Mp3ToolsMainPanel handle = null;
	
	public static Mp3ToolsMainPanel getInstance() {
		handle = handle != null ? handle : new Mp3ToolsMainPanel();
		return handle;
	}

	final Dimension labelSize = new Dimension(150, 20);
	
	public void setTag(ID3v2 tag) {
		this.invalidate();
		List<Object[]> data = Mp3TagList.getTagList(tag);
		this.setLayout(new BorderLayout());
		JPanel contentPanel = new JPanel();
		contentPanel.setLayout(new GridLayout(data.size(), 1));
		Border border = BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.DARK_GRAY),BorderFactory.createEmptyBorder(5, 5, 5, 5));
		for(Object obj[] : data) {
			JPanel panel = new JPanel(new BorderLayout());
			JTextArea textField = new JTextArea("" + obj[0]);
			textField.setEditable(false);
			JLabel label = new JLabel("" + obj[1]);
			label.setSize(labelSize);
			label.setPreferredSize(labelSize);
			label.setMinimumSize(labelSize);
			List<JComponent> comps = Arrays.asList(label, textField);
			comps.forEach(u -> u.setBorder(border));
			panel.add(label, BorderLayout.WEST);
			panel.add(textField, BorderLayout.CENTER);
			contentPanel.add(panel);
		}
		JScrollPane scrollPane = new JScrollPane(contentPanel);
		this.add(scrollPane, BorderLayout.CENTER);
		this.revalidate();
		this.repaint();
	}
	
	public void onOpenFile(File file) {
		System.out.println(file.getAbsolutePath());
		try {
			Mp3File mp3File = new Mp3File(file);
			/*
		tag.setComment("This is another comment " + new Date());
		mp3File.removeId3v1Tag();
		mp3File.removeId3v2Tag();
		mp3File.setId3v2Tag(tag);
		mp3File.removeCustomTag();
		mp3File.save(file.getAbsolutePath().replaceAll(".mp3", "Two.mp3"));
			 */
			setTag(mp3File.getId3v2Tag());
		} catch (UnsupportedTagException | InvalidDataException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
