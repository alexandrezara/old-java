package core.graphic;

import interfaces.Graphical;

import java.awt.Container;

import javax.swing.JPanel;

import core.logic.Item;

public abstract class AbstractGraphItem<I extends Item> implements Graphical {
	protected I			item;
	protected JPanel	panel;

	protected AbstractGraphItem(I item) {
		this.item = item;
		this.panel = new JPanel();
		init();
		setEnabled(item.getLoadMode().enable);
		if (item.getLoadMode().load) {
			load();
		}
	}

	public String getTitle() {
		return item.getTitle();
	}
	public Container getGraph() {
		return panel;
	}

	protected Item getItem() {
		return item;
	}

	protected abstract void init();
	protected abstract void setEnabled(boolean enabled);
	public abstract void save();
	public abstract void load();
}