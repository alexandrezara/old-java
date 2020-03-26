package components;

import gui.logic.Group;
import gui.logic.Item;

import java.util.List;

import components.project.CmpProject;

import core.pojo.Properties;

public class TopGroup extends Group {

	public TopGroup() {
		super("TopGroup");
		enabled = true;
		visible = true;

		this.addItem(new CmpProject());
	}

	public void hit(int modifier) {
	}
	public void update() {
		for (Item i : this) {
			i.update();
		}
	}

	protected List<Properties> getDefaultProps() {
		return null;
	}
}