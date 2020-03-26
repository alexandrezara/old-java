package components.projectSelection;

import gui.logic.Item;

import java.util.List;

import util.Message;
import core.Manager;
import core.pojo.Properties;

public class ProjectItem extends Item {
	private String name;

	public ProjectItem(String name) {
		super(Type.PUSH, name);
		enabled = true;
		visible = true;

		this.name = name;
	}

	public void hit(int modifier) {
		Message.info("Project: " + name);
		Manager.setProp("selectedProject", name);
	}
	protected List<Properties> getDefaultProps() {
		return null;
	}
}