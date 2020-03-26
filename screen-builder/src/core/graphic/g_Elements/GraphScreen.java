package core.graphic.g_Elements;

import javax.swing.JTabbedPane;

import core.graphic.AbstractGraphElement;
import core.logic.l_Elements.Screen;

public class GraphScreen extends AbstractGraphElement<Screen, JTabbedPane, GraphTab> {

	public GraphScreen(Screen screen) {
		super(screen, new JTabbedPane());

		for (GraphTab tab : children) {
			container.add(tab.getTitle(), tab.getGraph());
		}
	}

	public void save() {
		for (GraphTab tab : children) {
			tab.save();
		}
		element.save();
	}
}