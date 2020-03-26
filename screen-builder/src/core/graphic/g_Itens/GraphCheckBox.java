package core.graphic.g_Itens;

import java.awt.Dimension;

import javax.swing.GroupLayout;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.GroupLayout.Group;
import javax.swing.GroupLayout.ParallelGroup;
import javax.swing.border.EmptyBorder;

import core.graphic.AbstractGraphItem;
import core.logic.l_Itens.CheckBox;

public class GraphCheckBox extends AbstractGraphItem<CheckBox> {

	private JLabel		label;
	private JCheckBox[]	boxes;

	public GraphCheckBox(CheckBox item) {
		super(item);
	}

	protected void init() {
		label = new JLabel(item.getTitle());
		boxes = new JCheckBox[item.getValue().getAvaliableValues().length];
		for (int i = 0 ; i < item.getValue().getAvaliableValues().length ; i++) {
			boxes[i] = new JCheckBox(item.getValue().getAvaliableValues()[i]);
		}

		GroupLayout layout = new GroupLayout(panel);
		Group hGroup = layout.createParallelGroup(GroupLayout.Alignment.LEADING);
		Group vGroup = layout.createSequentialGroup();

		ParallelGroup parallelGroup;
		parallelGroup = layout.createParallelGroup();
		parallelGroup.addComponent(label);
		for (JCheckBox jcb : boxes) {
			parallelGroup.addGroup(
					layout.createSequentialGroup()
					.addGap(H_GAP_SIZE)
					.addComponent(jcb));
		}
		hGroup.addGroup(parallelGroup);

		vGroup.addComponent(label);
		for (JCheckBox jcb : boxes) {
			vGroup.addGroup(
					layout.createParallelGroup()
					.addGap(H_GAP_SIZE)
					.addComponent(jcb));
		}
		layout.setHorizontalGroup(hGroup);
		layout.setVerticalGroup(vGroup);

		label.setMaximumSize(new Dimension(2048, 25));
		panel.setBorder(new EmptyBorder(4,5,4,5));
		panel.setLayout(layout);
	}

	protected void setEnabled(boolean enabled) {
		label.setEnabled(enabled);
		for (JCheckBox jcb : boxes) {
			jcb.setEnabled(enabled);	
		}
	}
	public void save() {
		item.getValue().clearSelection();
		for (JCheckBox jcb : boxes) {
			if (jcb.isSelected()) {
				item.getValue().addSelection(jcb.getText());
			}
		}
	}
	public void load() {
		for (JCheckBox jcb : boxes) {
			jcb.setSelected(false);
		}
		for (String str : item.get()) {
			for (JCheckBox jcb : boxes) {
				if (jcb.getText().equalsIgnoreCase(str)) {
					jcb.setSelected(true);
					break;
				}
			}
		}
	}
}