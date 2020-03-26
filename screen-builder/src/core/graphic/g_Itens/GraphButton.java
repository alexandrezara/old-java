package core.graphic.g_Itens;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.GroupLayout.Group;
import javax.swing.border.EmptyBorder;

import core.graphic.AbstractGraphItem;
import core.graphic.base.GraphFrame;
import core.logic.l_Itens.Button;

public class GraphButton extends AbstractGraphItem<Button> {

	private JButton button;

	public GraphButton(Button item) {
		super(item);
	}

	protected void init() {
		button = new JButton(item.getTitle());
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GraphFrame gf = new GraphFrame(item.getScreen());
				gf.show(false);
			}
		});

		GroupLayout layout = new GroupLayout(panel);

		Group hGroup = layout.createParallelGroup(GroupLayout.Alignment.LEADING);
		Group vGroup = layout.createSequentialGroup();

		button.setMaximumSize(new Dimension(2048, 25));
		hGroup.addComponent(button);
		vGroup.addComponent(button);

		layout.setHorizontalGroup(hGroup);
		layout.setVerticalGroup(vGroup);

		panel.setBorder(new EmptyBorder(4,5,4,5));
		panel.setLayout(layout);
	}

	protected void setEnabled(boolean enabled) {
		button.setEnabled(enabled);
	}
	public void save() {
	}
	public void load() {
	}
}
