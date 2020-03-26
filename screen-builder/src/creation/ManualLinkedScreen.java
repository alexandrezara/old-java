package creation;

import linking.Linker;
import core.logic.Item;
import core.logic.l_Elements.Screen;
import enums.LoadMode;

public class ManualLinkedScreen extends Screen {
	private Linker linker;

	public ManualLinkedScreen(String title, LoadMode loadMode) {
		super(title, loadMode);
	}

	public void setLink(Object object) {
		linker = new Linker(object);
	}
	public boolean link(Item screenItem, String objectField) {
		for (Item i : allItems) {
			if (i.equals(screenItem)) {
				linker.link(screenItem, objectField);
				return true;
			}
		}
		return false;
	}
	public boolean link(String itemName, String objectField) {
		for (Item i : allItems) {
			if (i.getTitle().equalsIgnoreCase(objectField)) {
				linker.link(i, objectField);
				return true;
			}
		}
		return false;
	}
	public boolean link(String tab, String panel, String item, String objectField) {
		Item screenItem = getItem(tab, panel, item);
		if (screenItem == null) {
			return false;
		}
		linker.link(screenItem, objectField);
		return true;
	}
	public void save() {
		linker.update();
	}
}