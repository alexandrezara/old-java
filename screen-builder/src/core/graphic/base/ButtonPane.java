package core.graphic.base;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import enums.LoadMode;

public class ButtonPane {
	private static final long serialVersionUID = 1L;

	private GraphFrame	frame;
	private JPanel		panel;
	private JButton[]	buttons;

	public ButtonPane(GraphFrame frame, LoadMode loadMode) {
		this.panel = new JPanel();
		this.frame = frame;

		panel.setLayout(new FlowLayout(FlowLayout.RIGHT));

		switch (loadMode) {
		case NEW:
			setButtonsForNewMode();
			break;
		case VIEW:
			setButtonsForViewMode();
			break;
		case EDIT:
			setButtonsForEditMode();
			break;
		}

		for (JButton button : buttons) {
			panel.add(button);
		}
	}

	public JPanel getGraph() {
		return panel;
	}

	private void setButtonsForNewMode() {
		buttons = new JButton[2];
		buttons[0] = new JButton("OK");
		buttons[1] = new JButton("Cancel");

		buttons[0].addActionListener(getSaveListener());
		buttons[0].addActionListener(getExitListener());
		buttons[1].addActionListener(getExitListener());
	}
	private void setButtonsForViewMode() {
		buttons = new JButton[1];
		buttons[0] = new JButton("Exit");

		buttons[0].addActionListener(getExitListener());
	}
	private void setButtonsForEditMode() {
		buttons = new JButton[3];
		buttons[0] = new JButton("OK");
		buttons[1] = new JButton("Apply");
		buttons[2] = new JButton("Cancel");

		buttons[0].addActionListener(getSaveListener());
		buttons[0].addActionListener(getExitListener());
		buttons[1].addActionListener(getSaveListener());
		buttons[2].addActionListener(getExitListener());
	}

	private ActionListener getSaveListener() {
		return new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.save();
			}
		};
	}
	private ActionListener getExitListener() {
		return new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.exit();
			}
		};
	}
}
