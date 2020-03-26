package components;

import gui.logic.Group;
import gui.logic.Item;

import java.util.List;

import core.pojo.Properties;

public class BaseGroup extends Group {

	public BaseGroup() {
		super("Project Manager");
		enabled = true;
		visible = true;

		this.addItem(new TopGroup());
		this.addItem(new MidGroup());
		this.addItem(new BotGroup());
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