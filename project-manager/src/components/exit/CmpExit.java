package components.exit;

import gui.logic.Item;

import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

import util.Message;
import core.Manager;
import core.pojo.Properties;
import core.services.bundle.Bundle;

public class CmpExit extends Item {
	private static final String PROP_CONFIRM_EXIT = "confirmExit";

	public CmpExit() {
		super(Type.PUSH, "Exit");
		enabled = true;
		visible = true;
	}

	public void hit(int modifier) {
		String[] options;
		if (Calendar.getInstance().get(Calendar.HOUR_OF_DAY) >= 18 &&
				Manager.getProp(PROP_CONFIRM_EXIT).equalsIgnoreCase("true")) {
			options = new String[] {
					Bundle.get(Bundle.MSG, "exit.opt1"),
					Bundle.get(Bundle.MSG, "exit.opt2"),
					Bundle.get(Bundle.MSG, "exit.opt3")};
		}
		else {
			options = new String[] {
					Bundle.get(Bundle.MSG, "exit.opt1"),
					Bundle.get(Bundle.MSG, "exit.opt2")};
		}
		int opt = Message.ask(Bundle.get(Bundle.MSG, "exit.question"), options);
		if (opt == 0) {
			Manager.stop();			
		}
		if (opt == 2) {
			Manager.setProp(PROP_CONFIRM_EXIT, "false");
			Manager.stop();
		}
	}

	protected List<Properties> getDefaultProps() {
		List<Properties> props = new LinkedList<Properties>();
		props.add(new Properties(PROP_CONFIRM_EXIT, "true", true));
		return props;
	}
}