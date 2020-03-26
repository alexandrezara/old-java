package gui.graphic;

import gui.logic.Cascade;
import gui.logic.Group;
import gui.logic.Item;
import interfaces.LogicItem;
import interfaces.LogicItem.Type;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;

import core.Manager;

public class Deployer {
	private static final String GROUP_DEF_IMG = "img/img2.GIF";

	private Deployer() {
	}

	public static void deploy(LogicItem item, Menu menu) {
		if (!item.isVisible()) {
			return;
		}

		if (item instanceof Group) {
			deploy((Group)item, menu);
		}
		else if (item instanceof Cascade) {
			deploy((Cascade)item, menu);
		}
		else if (item instanceof Item) {
			deploy((Item)item, menu);
		}
	}

	public static void deploy(Item item, Menu menu) {
		MenuItem mi = new MenuItem(menu, getSwtType(item.getType()));
		mi.setText(item.getTitle());
		if (item.getImage() != null) {
			mi.setImage(new Image(menu.getDisplay(), item.getImage()));
		}
		mi.setEnabled(item.isEnabled());
		mi.addListener(SWT.Selection, new HitListener(item));
	}

	public static void deploy(Cascade item, Menu menu) {
		MenuItem mi = new MenuItem(menu, getSwtType(item.getType()));
		mi.setText(item.getTitle());
		if (item.getImage() != null) {
			mi.setImage(new Image(menu.getDisplay(), item.getImage()));
		}
		mi.setEnabled(item.isEnabled());

		if (item.getType() == Type.CASCADE) {
			Menu subMenu = new Menu(menu);
			mi.setMenu(subMenu);
			for (LogicItem child : item.getChildren()) {
				deploy(child, subMenu);
			}
		}
	}
	
	public static void deploy(Group group, Menu menu) {
		if (group.isTitled()) {
			MenuItem mi = new MenuItem(menu, getSwtType(group.getType()));
			mi.setText(group.getTitle());
			mi.setImage(new Image(menu.getDisplay(), GROUP_DEF_IMG));
			mi.setEnabled(group.isEnabled());
			mi.addListener(SWT.Selection, new HitListener(group));
		}

		for (LogicItem i : group) {
			i.setEnabled(group.isEnabled());
			deploy(i, menu);
		}
	}

	private static int getSwtType(Type type) {
		switch (type) {
		case PUSH:
			return SWT.PUSH;
		case RADIO:
			return SWT.RADIO;
		case CHECK:
			return SWT.CHECK;
		case CASCADE:
			return SWT.CASCADE;
		default:
			return SWT.NONE;
		}
	}
}


class HitListener implements Listener {
	private Item item;

	public HitListener(Item item) {
		this.item = item;
	}

	public void handleEvent (Event event) {
		item.hit(event.stateMask);
		Manager.update();
	}
}