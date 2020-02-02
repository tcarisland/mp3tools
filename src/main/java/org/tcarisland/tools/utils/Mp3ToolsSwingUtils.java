package org.tcarisland.tools.utils;

import java.awt.Window;

import javax.swing.JFrame;

public class Mp3ToolsSwingUtils {

	public static void enableOSXFullscreen(JFrame frame) {
		try {
			Class<? extends Object> fsu = Class.forName("com.apple.eawt.FullScreenUtilities");
			fsu.getMethod("setWindowCanFullScreen", Window.class, Boolean.TYPE).invoke(null, frame, true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
