package core.graphic.g_Itens;

import interfaces.Graphical;

import java.awt.Dimension;

import javax.swing.GroupLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.GroupLayout.Group;
import javax.swing.border.EmptyBorder;

import core.graphic.AbstractGraphItem;
import core.logic.l_Itens.TextField;

public class GraphTextField extends AbstractGraphItem<TextField> {

	private JLabel		label;
	private JTextField	textField;

	public GraphTextField(TextField item) {
		super(item);
	}

	protected void init() {
		label = new JLabel(item.getTitle());
		textField = new JTextField(item.getSize());

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
						.addComponent(textField)));

		vGroup
		.addComponent(label)
		.addGap(V_GAP_SIZE)
		.addGroup(
				layout.createParallelGroup()
				.addGap(H_GAP_SIZE)
				.addComponent(textField));

		layout.setHorizontalGroup(hGroup);
		layout.setVerticalGroup(vGroup);

		textField.setMaximumSize(new Dimension(Graphical.MAX_WIDTH, 0));
		panel.setBorder(new EmptyBorder(4,5,4,5));
		panel.setLayout(layout);
	}

	protected void setEnabled(boolean enabled) {
		label.setEnabled(enabled);
		textField.setEnabled(enabled);
	}
	public void save() {
		String txt = textField.getText(); 
		item.set(txt);
	}
	public void load() {
		textField.setText(item.get());
	}
}