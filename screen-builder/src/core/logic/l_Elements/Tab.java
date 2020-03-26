package core.logic.l_Elements;

import core.logic.Element;
import enums.LoadMode;

public class Tab extends Element<Panel> {
	protected Tab(String title, LoadMode loadMode) {
		super(title, loadMode);
	}
	public String toString() {
		StringBuilder sb = new StringBuilder(getTitle() + " {");
		for (Panel p : getChildren()) {
			sb.append(p.toString() + ", ");
		}
		if (getChildren().size() > 0) {
			sb.setLength(sb.length() - 2);
		}
		sb.append("}");
		return sb.toString();
	}
}