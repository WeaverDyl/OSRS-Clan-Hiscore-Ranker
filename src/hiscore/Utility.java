package hiscore;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.io.File;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

/*
 * Contains many useful methods for the hiscore tool
 * 
 * @author Dylan Weaver
 */
public class Utility {
	
	final static String getPositionSuffix(int position) {
		if (position >= 11 && position <= 13) {
			return "th: ";
		} else {
			switch (position % 10) {
			case 1:
				return "st: ";
			case 2:
				return "nd: ";
			case 3:
				return "rd: ";
			default:
				return "th: ";
			}
		}
	}
	
	static String getFileExtension(File file) {
	    String name = file.getName();
	    try {
	        return name.substring(name.lastIndexOf(".") + 1);
	    } catch (Exception e) {
	        return "";
	    }
	}
	
	static String addExtension(String file, String extension) {
		if (!file.toString().endsWith("." + extension)) {
			file += ("." + extension);
		}
		
		return file;
	}
	
	static void copyToClipboard(JFrame frame, JTextArea textArea) {
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Clipboard clipboard = toolkit.getSystemClipboard();
		StringSelection toCopy = new StringSelection(textArea.getText());
		clipboard.setContents(toCopy, null);
		JOptionPane.showMessageDialog(frame, 
				"Results copied to clipboard!", "Message", JOptionPane.INFORMATION_MESSAGE);
	}
	
	static void clearTextAreas(JTextArea... toClear) {
		for (JTextArea textAreas : toClear) {
			textAreas.setText("");
		}
	}

}
