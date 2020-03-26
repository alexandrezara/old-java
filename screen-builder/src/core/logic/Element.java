package core.logic;

import interfaces.Displayable;

import java.util.LinkedList;
import java.util.List;

import enums.LoadMode;

public abstract class Element<R extends Displayable> implements Displayable {
	private String		title;
	private List<R>		children;
	private LoadMode	loadMode;

	protected Element(String title, LoadMode loadMode) {
		this.title = title;
		this.loadMode = loadMode;
		this.children = new LinkedList<R>();
	}

	public void setTitle(String title) {
		this.title = title;
	}
	public String getTitle() {
		return title;
	}
	public LoadMode getLoadMode() {
		return loadMode;
	}
	public void setLoadMode(LoadMode loadMode) {
		this.loadMode = loadMode;
	}
	public void add(R child) {
		child.setLoadMode(loadMode);
		children.add(child);
	}

	@SuppressWarnings("unchecked")
	public Item getItem(String... titles) {
		Element<? extends Displayable> d = this;
		for (int i = 0 ; i < titles.length - 1; i++) {
			d = (Element<? extends Displayable>)d.getChild(titles[i]);
		}
		return (Item)d.getChild(titles[titles.length - 1]);
	}
	public R getChild(String title) {
		for (R child : children) {
			if (child.getTitle().equalsIgnoreCase(title)) {
				return child;
			}
		}
		return null;
	}
	public List<R> getChildren() {
		return children;
	}
}