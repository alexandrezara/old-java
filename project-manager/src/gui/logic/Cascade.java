package gui.logic;

import interfaces.LogicItem;

import java.util.LinkedList;
import java.util.List;

public abstract class Cascade extends Item {
	private List<LogicItem> children;

	public Cascade(String title) {
		super(Type.CASCADE, title);
		this.children = new LinkedList<LogicItem>();
	}

	public void hit(int modifier) {
	}

	public List<LogicItem> getChildren() {
		return children;
	}
	protected void addChild(LogicItem item) {
		children.add(item);
	}
}