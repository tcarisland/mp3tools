package org.tcarisland.tools;

import java.awt.event.WindowEvent;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import org.tcarisland.tools.i18n.Label;

public class Mp3MenuBar extends JMenuBar {

	private static final long serialVersionUID = 1L;

	private static Mp3MenuBar handle;

	private static File lastDirectory = null;;

	public static Mp3MenuBar getInstance() {
		return handle != null ? handle : new Mp3MenuBar();
	}

	private Mp3MenuBar() {
		this.add(initFileMenu());
		this.add(initEditMenu());
	}

	private JMenu initEditMenu() {
		JMenu editMenu = new JMenu(Label.EDIT.getLabel());
		JMenuItem copy = new JMenu(Label.COPY.getLabel());
		JMenuItem paste = new JMenu(Label.PASTE.getLabel());
		editMenu.add(copy);
		editMenu.add(paste);
		return editMenu;
	}

	private JMenu initFileMenu() {
		JMenu fileMenu = new JMenu(Label.FILE.getLabel());
		JMenuItem open = new JMenuItem(Label.OPEN.getLabel());
		open.addActionListener(u -> {
			JFileChooser chooser = new JFileChooser();
			if(lastDirectory != null) {
			  chooser.setCurrentDirectory(lastDirectory);
			}
			int status = chooser.showOpenDialog(Mp3ToolsFrame.getInstance());
			if(status == JFileChooser.APPROVE_OPTION) {
				File file = chooser.getSelectedFile();
				lastDirectory = file.getParentFile();
				Mp3ToolsMainPanel.getInstance().onOpenFile(file);
			}
		});
		JMenuItem save = new JMenuItem(Label.SAVE.getLabel());
		save.addActionListener(u -> {
		  JFileChooser chooser = new JFileChooser();
		  int status = chooser.showSaveDialog(Mp3ToolsFrame.getInstance());
		  if(status == JFileChooser.APPROVE_OPTION) {
		    File file = chooser.getSelectedFile();
		    Mp3ToolsMainPanel.getInstance().onSaveFile(file);
		  }
		});
		JMenuItem exit = new JMenuItem(Label.EXIT.getLabel());
		exit.addActionListener(u -> {
			Mp3ToolsFrame frame = Mp3ToolsFrame.getInstance();
			frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
			System.exit(0);
		});
		fileMenu.add(open);
		fileMenu.add(save);
		fileMenu.add(exit);
		return fileMenu;
	}

}
