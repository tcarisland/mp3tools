package org.tcarisland.tools;

import org.tcarisland.tools.utils.Mp3ToolsSwingUtils;

/**
 * Hello world!
 *
 */
public class Mp3Tools 
{
    public static void main( String[] args ) {
    	Mp3ToolsFrame frame = Mp3ToolsFrame.getInstance();
    	Mp3ToolsSwingUtils.enableOSXFullscreen(frame);
    	frame.setVisible(true);
    }
}
