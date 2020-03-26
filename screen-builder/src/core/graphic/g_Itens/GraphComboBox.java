package core.graphic.g_Itens;

import interfaces.Graphical;

import java.awt.Dimension;

import javax.swing.GroupLayout;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.GroupLayout.Group;
import javax.swing.border.EmptyBorder;

import core.graphic.AbstractGraphItem;
import core.logic.Value;
import core.logic.l_Itens.ComboBox;

public class GraphComboBox extends AbstractGraphItem<ComboBox> {
	private JLabel		label;
	private JComboBox	combo;

	public GraphComboBox(ComboBox item) {
		super(item);
	}

	protected void init() {
		label = new JLabel(item.getTitle());
		combo = new JComboBox(item.getValue().getAvaliableValues());

		GroupLayout layout = new GroupLayout(panel);
		Group hGroup = layout.createParallelGroup(GroupLayout.Alignment.LEADING);
		Group vGroup = layout.createSequentialGroup();

		hGroup
		.addGroup(
				layout.createParallelGroup()
				.addComponent(label)
				.addGap(V_GAP_SIZE)
				.addGroup(layout.createSequentialGroup()
						.addGap(H_GAP_SIZE)
						.addComponent(combo)));

		vGroup
		.addComponent(label)
		.addGap(V_GAP_SIZE)
		.addGroup(
				layout.createParallelGroup()
				.addGap(H_GAP_SIZE)
				.addComponent(combo));
		layout.setHorizontalGroup(hGroup);
		layout.setVerticalGroup(vGroup);

		label.setMaximumSize(new Dimension(2048, 0));
		combo.setMaximumSize(new Dimension(Graphical.MAX_WIDTH, 0));
		panel.setBorder(new EmptyBorder(4,5,4,5));
		panel.setLayout(layout);
	}

	protected void setEnabled(boolean enabled) {
		label.setEnabled(enabled);
		combo.setEnabled(enabled);
	}
	public void save() {
		item.getValue().clearSelection();
		item.getValue().addSelection((String)combo.getSelectedItem());
	}
	public void load() {
		Value value = item.getValue();
		if (value.getSelectedValues().length == 0) {
			return;
		}
		for (int i = 0 ; i < combo.getItemCount() ; i++) {
			if (value.getSelectedValues()[0].equalsIgnoreCase((String)combo.getItemAt(i))) {
				combo.setSelectedIndex(i);
				return;
			}
		}
	}
}
