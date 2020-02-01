package org.tcarisland.tools;

import java.util.Locale;

import org.apache.lucene.util.Constants;
import org.tcarisland.tools.i18n.Labels;
import org.tcarisland.tools.utils.Mp3ToolsSwingUtils;

/**
 * Hello world!
 *
 */
public class Mp3Tools 
{
    public static void main( String[] args ) {
    	System.setProperty("file.encoding", "UTF-8");
    	Labels.init(Locale.forLanguageTag("no"));
    	Mp3ToolsFrame frame = Mp3ToolsFrame.getInstance();
    	if(Constants.MAC_OS_X) {
    		Mp3ToolsSwingUtils.enableOSXFullscreen(frame);
    	}
    	if(Constants.WINDOWS) {
    		System.out.println("Hello, Windows!");
    	}
    	frame.setVisible(true);
    }
}
