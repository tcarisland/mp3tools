package org.tcarisland.tools;

import javax.swing.JFrame;

public class Mp3ToolsFrame extends JFrame {

	private static final long serialVersionUID = 1L;

	public static final int WIDTH = 1024;
	public static final int HEIGHT = 768;
	
	private static Mp3ToolsFrame handle;
	
	public static Mp3ToolsFrame getInstance() {
		handle = handle != null ? handle : new Mp3ToolsFrame();
		return handle;
	}
	
	private Mp3ToolsFrame() {
		this.setSize(WIDTH, HEIGHT);
		this.setTitle("Mp3 Tools");
		this.setContentPane(Mp3ToolsMainPanel.getInstance());
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	
}
