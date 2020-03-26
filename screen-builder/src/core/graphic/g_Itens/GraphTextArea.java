package core.graphic.g_Itens;

import javax.swing.GroupLayout;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.GroupLayout.Group;
import javax.swing.border.EmptyBorder;

import core.graphic.AbstractGraphItem;
import core.logic.l_Itens.TextArea;

public class GraphTextArea extends AbstractGraphItem<TextArea> {

	private JScrollPane pane;
	private JLabel		label;
	private JTextArea	textArea;

	public GraphTextArea(TextArea item) {
		super(item);
	}

	protected void init() {
		label = new JLabel(item.getTitle());
		textArea = new JTextArea();
		pane = new JScrollPane(textArea);

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
						.addComponent(pane)));

		vGroup
		.addComponent(label)
		.addGap(V_GAP_SIZE)
		.addGroup(
				layout.createParallelGroup()
				.addGap(H_GAP_SIZE)
				.addComponent(pane));

		layout.setHorizontalGroup(hGroup);
		layout.setVerticalGroup(vGroup);

		panel.setBorder(new EmptyBorder(4,5,4,5));
		panel.setLayout(layout);
	}

	protected void setEnabled(boolean enabled) {
		label.setEnabled(enabled);
		textArea.setEnabled(enabled);
	}
	public void save() {
		String txt = textArea.getText(); 
		item.set(txt);
	}
	public void load() {
		textArea.setText(item.get());
	}
}