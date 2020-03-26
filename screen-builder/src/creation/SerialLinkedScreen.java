package creation;

import core.logic.Item;
import enums.LoadMode;

public class SerialLinkedScreen extends ManualLinkedScreen {

	public SerialLinkedScreen(String title, LoadMode loadMode, Object linkedObject) {
		super(title, loadMode);
		setLink(linkedObject);
	}

	public boolean addItem(String tab, String panel, Item item, String objectField) {
		if (addItem(tab, panel, item)) {
			link(item, objectField);
		}
		return true;
	}
}