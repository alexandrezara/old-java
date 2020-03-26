package core.logic.l_Elements;

import core.logic.Element;
import core.logic.Item;
import enums.LoadMode;

public class Panel extends Element<Item> {
	protected Panel(String title, LoadMode loadMode) {
		super(title, loadMode);
	}
	public String toString() {
		StringBuilder sb = new StringBuilder(getTitle() + " {");
		for (Item i : getChildren()) {
			if (i != null) {
				sb.append(i.toString() + ", ");
			}
		}
		if (getChildren().size() > 0) {
			sb.setLength(sb.length() - 2);
		}
		//sb.append("}");
		return sb.toString();
	}
}
