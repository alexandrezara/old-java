package components.projectSelection;

import gui.logic.Group;
import gui.logic.Item;

import java.util.List;

import core.pojo.Properties;

public class ProjectControl extends Group {
	public ProjectControl() {
		super("Control");
		enabled = true;
		visible = true;
		update();
	}

	public void update() {
		addItem(new Item(Type.PUSH, "Create", true, true) {
			public void hit(int modifier) {
				System.out.println("hit create");
			}
			protected List<Properties> getDefaultProps() {
				return null;
			}
		});
		addItem(new Item(Type.PUSH, "Remove", true, true) {
			public void hit(int modifier) {
				System.out.println("hit remove");
			}
			protected List<Properties> getDefaultProps() {
				return null;
			}
		});
	}

	public void hit(int modifier) {
		System.out.println("hit project");
	}
	protected List<Properties> getDefaultProps() {
		return null;
	}
}