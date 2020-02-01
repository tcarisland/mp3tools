package org.tcarisland.tools;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import org.tcarisland.tools.i18n.Labels;

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
		JMenu fileMenu = new JMenu(Labels.getLabel("general.file"));
		JMenuItem open = new JMenuItem(Labels.getLabel("general.open"));
		JMenuItem exit = new JMenuItem(Labels.getLabel("general.exit"));
		System.out.println(open.getText());
		System.out.println(exit.getText());
		fileMenu.add(open);
		fileMenu.add(exit);
		return fileMenu;
	}
	
}
