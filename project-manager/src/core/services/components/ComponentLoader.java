package core.services.components;

import interfaces.LogicItem;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import components.BaseGroup;

import core.pojo.Properties;
import core.services.AbstractService;

public class ComponentLoader extends AbstractService implements Iterable<LogicItem> {
	private static ComponentLoader instance;

	private List<LogicItem>	components;
	
	private BaseGroup base;

	private ComponentLoader() {
		super(ComponentLoader.class);
	}
	public static ComponentLoader getInstance() {
		if (instance == null) {
			instance = new ComponentLoader();
		}
		return instance;
	}

	public long startService() {
		components = new LinkedList<LogicItem>();
		/*
		for (LogicItem i : DefaultComponents.getItens()) {
			components.add(i);
		}
		*/
		base = new BaseGroup();
		return 0;
	}
	public void stopService() {
	}
	public void update() {
		base.update();
	}
	public List<Properties> getDefaultProps() {
		return null;
	}
	public BaseGroup getBaseGroup() {
		return base;
	}
	public Iterator<LogicItem> iterator() {
		return components.iterator();
	}

	public LogicItem getComponent(String name) {
		for (LogicItem i : components) {
			if (i.getClass().getSimpleName().equalsIgnoreCase(name)) {
				return i;
			}
		}
		return null;
	}

	public void show() {	
		for (LogicItem i : components) {
			System.out.println(" - '" + i.getTitle() + "'");
		}
	}
}