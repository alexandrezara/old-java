package components;

import gui.logic.Group;
import gui.logic.Item;

import java.util.List;

import components.about.CmpAbout;
import components.exit.CmpExit;

import core.pojo.Properties;

public class BotGroup extends Group {

	public BotGroup() {
		super("BottonGroup");
		enabled = true;
		visible = true;

		this.addItem(new CmpAbout());
		this.addItem(new CmpExit());
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