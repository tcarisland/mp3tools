package org.tcarisland.tools;

import java.awt.GridLayout;
import java.io.File;

import javax.swing.JLabel;
import javax.swing.JPanel;

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
	}
	
}
