package util;

import javax.swing.JOptionPane;

import core.graphic.base.GraphFrame;
import core.graphic.base.GraphFrame.Look;

public class Message {
	static {
		GraphFrame.setLook(Look.BLACK_MOON);
	}
	public static void warn(String message) {
		JOptionPane.showMessageDialog(null, message);
	}

	public static void info(String message) {
		JOptionPane.showMessageDialog(null, message);
	}
	
	public static int ask(String message, String[] options) {
		return JOptionPane.showOptionDialog(null,
				message,
				"System Message",
				JOptionPane.YES_NO_OPTION,
				JOptionPane.QUESTION_MESSAGE,
				null,
				options,
				0);
	}
}