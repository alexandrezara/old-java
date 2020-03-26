package core.graphic.g_Elements;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import core.graphic.AbstractGraphElement;
import core.logic.l_Elements.Tab;

public class GraphTab extends AbstractGraphElement<Tab, JPanel, GraphPanel> {

	public GraphTab(Tab element) {
		super(element, new JPanel());

		container.setBorder(new EmptyBorder(3,4,3,4));
		container.setLayout(getDefaultLayout());
	}
}