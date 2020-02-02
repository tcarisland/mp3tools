package org.tcarisland.tools;

import java.awt.GridLayout;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.ByteBuffer;
import java.nio.channels.SeekableByteChannel;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.Date;
import java.util.EnumSet;

import javax.swing.JLabel;
import javax.swing.JPanel;

import com.mpatric.mp3agic.ID3v2;
import com.mpatric.mp3agic.ID3v24Tag;
import com.mpatric.mp3agic.ID3v2TagFactory;
import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.Mp3File;
import com.mpatric.mp3agic.NotSupportedException;
import com.mpatric.mp3agic.UnsupportedTagException;

public class Mp3ToolsMainPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private static Mp3ToolsMainPanel handle = null;
	
	public static Mp3ToolsMainPanel getInstance() {
		handle = handle != null ? handle : new Mp3ToolsMainPanel();
		return handle;
	}
	
	private Mp3ToolsMainPanel() {
		System.out.println("Called");
		int randomNumber = ((int) Math.random() * 100) + 10;
		this.setLayout(new GridLayout(randomNumber, 1));
		System.out.printf("Random number %d \n", randomNumber);
		for(int i = 0; i < randomNumber; i++) {
			JLabel label = new JLabel(String.format("%04d", i));
			System.out.printf("adding label %s \n", label.getText());
			this.add(label);
		}
	}
	
	public void onOpenFile(File file) {
		System.out.println(file.getAbsolutePath());
		try {
			Mp3File mp3File = new Mp3File(file);
			ID3v24Tag tag = new ID3v24Tag();
			tag.setComment("This is another comment " + new Date());
			mp3File.removeId3v1Tag();
			mp3File.removeId3v2Tag();
			mp3File.setId3v2Tag(tag);
			mp3File.removeCustomTag();
			mp3File.save(file.getAbsolutePath().replaceAll(".mp3", "Two.mp3"));
		} catch (UnsupportedTagException | InvalidDataException | IOException | NotSupportedException e) {
			e.printStackTrace();
		}
	}
	
}
