package components.project;

import enums.LoadMode;
import gui.logic.Group;

import java.util.List;

import core.Manager;
import core.graphic.base.GraphFrame;
import core.graphic.base.GraphFrame.Look;
import core.pojo.Projects;
import core.pojo.Properties;
import creation.AutoLinkedScreen;

public class CmpProject extends Group {
	private Projects project;

	public CmpProject() {
		super("Project");
		titled = true;
		update();
	}

	public void update() {
		String selectedProject = Manager.getProp("selectedProject");
		
		if (selectedProject.equalsIgnoreCase("")) {
			enabled = false;
			visible = false;			
		}
		else {
			enabled = true;
			visible = true;
			setTitle(selectedProject);
		}
		project = new Projects();

	}
	public void hit(int modifier) {
		AutoLinkedScreen screen = new AutoLinkedScreen(getTitle(), LoadMode.NEW, project, false);
		GraphFrame.setLook(Look.BLACK_MOON);
		GraphFrame gf = new GraphFrame(screen);
		gf.show(false);
		System.out.println();
	}
	protected List<Properties> getDefaultProps() {
		return null;
	}
}