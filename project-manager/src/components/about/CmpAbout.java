package components.about;

import java.util.List;

import core.pojo.Properties;
import util.Message;
import gui.logic.Item;

public class CmpAbout extends Item {
	public CmpAbout() {
		super(Type.PUSH, "About");
		enabled = true;
		visible = true;
	}

	public void hit(int modifier) {
		Message.info("ProjectManager\n" +
				"v0.5 beta\n" +
				"by nOEL =D");
	}
	protected List<Properties> getDefaultProps() {
		return null;
	}
}