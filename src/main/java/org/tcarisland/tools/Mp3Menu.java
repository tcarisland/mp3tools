package org.tcarisland.tools;

import java.awt.event.WindowEvent;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import org.tcarisland.tools.i18n.Label;

public class Mp3Menu extends JMenuBar {

	private static final long serialVersionUID = 1L;

	private static Mp3Menu handle;
	
	public static Mp3Menu getInstance() {
		return handle != null ? handle : new Mp3Menu();
	}
	
	private Mp3Menu() {
		this.add(initFileMenu());
	}
	
	private JMenu initFileMenu() {
		JMenu fileMenu = new JMenu(Label.FILE.getLabel());
		JMenuItem open = new JMenuItem(Label.OPEN.getLabel());
		open.addActionListener(u -> {
			JFileChooser chooser = new JFileChooser();
			int status = chooser.showOpenDialog(Mp3ToolsFrame.getInstance());
			if(status == JFileChooser.APPROVE_OPTION) {
				File file = chooser.getSelectedFile();
				Mp3ToolsMainPanel.getInstance().onOpenFile(file);
			}
		});
		JMenuItem exit = new JMenuItem(Label.EXIT.getLabel());
		exit.addActionListener(u -> {
			Mp3ToolsFrame frame = Mp3ToolsFrame.getInstance();
			frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
			System.exit(0);
		});
		fileMenu.add(open);
		fileMenu.add(exit);
		return fileMenu;
	}
	
}
