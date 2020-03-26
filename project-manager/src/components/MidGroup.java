package components;

import gui.logic.Group;
import gui.logic.Item;

import java.util.List;

import components.projectSelection.CmpProjectSelection;

import core.pojo.Properties;

public class MidGroup extends Group {

	public MidGroup() {
		super("MidGroup");
		enabled = true;
		visible = true;

		this.addItem(new CmpProjectSelection());
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
