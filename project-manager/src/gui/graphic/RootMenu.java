package gui.graphic;

import gui.logic.Group;
import interfaces.LogicItem;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;

public class RootMenu {
	private Menu	menu;
	private Group	group;

	public RootMenu(Menu parent, Group group) {
		this.menu = parent;
		this.group = group;
	}

	private void clean() {
		for (MenuItem mi : menu.getItems()) {
			mi.dispose();
		}
	}
	private void addSeparator() {
		new MenuItem(menu, SWT.SEPARATOR);
	}
	public void update() {
		clean();
		for (LogicItem item : group) {
			if (menu.getItemCount() > 0) {
				addSeparator();
			}
			Deployer.deploy(item, menu);
		}
	}
}