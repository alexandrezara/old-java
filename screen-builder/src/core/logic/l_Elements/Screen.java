package core.logic.l_Elements;

import java.util.LinkedList;
import java.util.List;

import core.logic.Element;
import core.logic.Item;
import enums.LoadMode;

public class Screen extends Element<Tab> {
	protected List<Item> allItems;

	public Screen(String title, LoadMode loadMode) {
		super(title, loadMode);
		allItems = new LinkedList<Item>();
	}

	public void addTab(String tab, LoadMode mode) {
		this.add(new Tab(tab, mode));
	}
	public void addPanel(String tab, String panel, LoadMode mode) {
		this.getChild(tab).add(new Panel(panel, mode));
	}
	public boolean addItem(String tab, String panel, Item item) {
		if (this.getChild(tab) == null) {
			addTab(tab, this.getLoadMode());
		}
		if (this.getChild(tab).getChild(panel) == null) {
			addPanel(tab, panel, getChild(tab).getLoadMode());
		}
		for (Item i : allItems) {
			if (item.getTitle().equalsIgnoreCase(i.getTitle())) {
				return false;
			}
		}
		this.getChild(tab).getChild(panel).add(item);
		this.allItems.add(item);
		return true;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder(getTitle() + " {");
		for (Tab t : getChildren()) {
			sb.append(t.toString() + ", ");
		}
		if (getChildren().size() > 0) {
			sb.setLength(sb.length() - 2);
		}
		sb.append("}");
		return sb.toString();
	}

	public void save() {}
}