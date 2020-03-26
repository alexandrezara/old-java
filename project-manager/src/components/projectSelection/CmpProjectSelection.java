package components.projectSelection;

import gui.logic.Cascade;

import java.util.List;

import core.pojo.Properties;

public class CmpProjectSelection extends Cascade {
	public CmpProjectSelection() {
		super("Projects");
		enabled = true;
		visible = true;
		update();
	}

	public void update() {
		getChildren().clear();
		addChild(new ProjectControl());
		addChild(new ProjectList());
	}

	public void hit(int modifier) {
		System.out.println("hit selection");
	}
	protected List<Properties> getDefaultProps() {
		return null;
	}
}