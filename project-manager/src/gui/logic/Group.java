package gui.logic;

import interfaces.LogicItem;

import java.util.Calendar;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import util.Logging.Log;
import util.Logging.LogHandler;
import core.services.bundle.Bundle;

public abstract class Group extends Item implements Iterable<Item> {

	protected boolean	titled;
	private List<Item>	itens;

	public Group(String title) {
		super(Type.PUSH, title);
		itens = new LinkedList<Item>();
	}

	public void move(Item item, int index) {
		itens.remove(item);
		itens.add(index, item);
	}

	public void moveDown(Item item) {
		int index = itens.indexOf(item);

		if (index == itens.size() - 1) {
			return;
		}
		itens.remove(index);
		itens.add(index + 1, item);
	}
	public void moveUp(Item item) {
		int index = itens.indexOf(item);

		if (index == 0) {
			return;
		}
		itens.remove(index);
		itens.add(index - 1, item);
	}

	public void addItem(Item item) {
		itens.add(item);
	}

	public void addChild(LogicItem item) {
		LogHandler.log(new Log(LogHandler.Level.ERROR, Group.class, Calendar.getInstance(), Bundle.get(Bundle.EXC, "cannotAddChild")));
	}

	public abstract void update();

	public boolean isTitled() {
		return titled;
	}
	public Iterator<Item> iterator() {
		return itens.iterator();
	}
}