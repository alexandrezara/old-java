package core.graphic.g_Itens;

import java.awt.Dimension;
import java.util.Enumeration;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.ButtonModel;
import javax.swing.GroupLayout;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.GroupLayout.Group;
import javax.swing.GroupLayout.ParallelGroup;
import javax.swing.border.EmptyBorder;

import core.graphic.AbstractGraphItem;
import core.logic.l_Itens.RadioButton;

public class GraphRadioButton extends AbstractGraphItem<RadioButton> {
	private JLabel 		label;
	private ButtonGroup group;

	public GraphRadioButton(RadioButton item) {
		super(item);
	}

	protected void init() {
		label = new JLabel(item.getTitle());
		group = new ButtonGroup();

		for (String str : item.getValue().getAvaliableValues()) {
			group.add(new JRadioButton(str));
		}

		GroupLayout layout = new GroupLayout(panel);
		Group hGroup = layout.createParallelGroup(GroupLayout.Alignment.LEADING);
		Group vGroup = layout.createSequentialGroup();

		ParallelGroup parallelGroup;
		Enumeration<AbstractButton> rbts = group.getElements();

		parallelGroup = layout.createParallelGroup();
		parallelGroup.addComponent(label);
		while (rbts.hasMoreElements()) {
			parallelGroup.addGroup(
					layout.createSequentialGroup()
					.addGap(H_GAP_SIZE)
					.addComponent(rbts.nextElement()));
		}
		hGroup.addGroup(parallelGroup);

		vGroup.addComponent(label);
		rbts = group.getElements();
		while (rbts.hasMoreElements()) {
			vGroup.addGroup(
					layout.createParallelGroup()
					.addGap(H_GAP_SIZE)
					.addComponent(rbts.nextElement()));
		}


		layout.setHorizontalGroup(hGroup);
		layout.setVerticalGroup(vGroup);

		label.setMaximumSize(new Dimension(2048, 25));
		panel.setBorder(new EmptyBorder(4,5,4,5));
		panel.setLayout(layout);
	}

	protected void setEnabled(boolean enabled) {
		label.setEnabled(enabled);
		Enumeration<AbstractButton> buttons = group.getElements();
		while (buttons.hasMoreElements()) {
			buttons.nextElement().setEnabled(enabled);
		}
	}
	public void save() {
		/*ButtonModel model = group.getSelection();
		if (model != null) {
			Object[] selection = model.getSelectedObjects();
			item.set((String)selection[0]);
		}*/
	}
	public void load() {
		JRadioButton jrb;

		while(group.getElements().hasMoreElements()) {
			jrb = (JRadioButton)group.getElements().nextElement();
			if (jrb.getText().equalsIgnoreCase(item.get())) {
				jrb.setSelected(true);
				return;
			}
		}
	}
}