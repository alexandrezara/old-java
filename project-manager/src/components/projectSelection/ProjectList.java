package components.projectSelection;

import gui.logic.Group;

import java.util.LinkedList;
import java.util.List;

import core.pojo.Projects;
import core.pojo.Properties;
import core.services.database.Table;
import core.services.database.TableLoader;

public class ProjectList extends Group {
	public ProjectList() {
		super("Projects");
		enabled = true;
		visible = false;
		update();
	}

	public void update() {
		Table<Projects> projects = TableLoader.get(Projects.class);
		for (Projects p : projects) {
			addItem(new ProjectItem(p.name));
			visible = true;
		}
	}

	public void hit(int modifier) {
		System.out.println("hit project");
	}
	protected List<Properties> getDefaultProps() {
		List<Properties> props = new LinkedList<Properties>();
		props.add(new Properties("selectedProject", "", true));
		return props;
	}
}