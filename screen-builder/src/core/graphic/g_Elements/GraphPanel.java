package core.graphic.g_Elements;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import core.graphic.AbstractGraphElement;
import core.graphic.AbstractGraphItem;
import core.logic.l_Elements.Panel;

public class GraphPanel extends AbstractGraphElement<Panel, JPanel, AbstractGraphItem<?>> {

	public GraphPanel(Panel element) {
		super(element, new JPanel());

		container.setBorder(BorderFactory.createTitledBorder(element.getTitle()));
		container.setLayout(getDefaultLayout());
	}
}