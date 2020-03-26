package gui;

import gui.graphic.RootMenu;
import gui.logic.Group;

import java.awt.DisplayMode;
import java.awt.GraphicsEnvironment;
import java.awt.MouseInfo;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Tray;
import org.eclipse.swt.widgets.TrayItem;

import util.Logging.Logger;
import core.pojo.Properties;
import core.services.bundle.Bundle;

public class ManagerTray {
	private static final String IMG_PATH = "img/img2.GIF";

	private static ManagerTray instance;
	private static Logger	   logger;

	private Display	 display = new Display();
	private Shell	 shell = new Shell(display);
	private Image	 image = new Image(display, IMG_PATH);
	private Tray	 tray;
	private TrayItem trayItem;
	
	private boolean	 closeTray = false;

	private RootMenu menu;
	private Menu	 root;
	private Group	 base;

	private ManagerTray() {
		ManagerTray.logger = new Logger(ManagerTray.class);
		tray = display.getSystemTray();

		if (tray == null) {
			ManagerTray.logger.error(Bundle.get(Bundle.EXC, "systrayNotAvailable"));
		}
	}
	public static ManagerTray getInstance() {
		if (ManagerTray.instance == null) {
			ManagerTray.instance = new ManagerTray();
		}
		return ManagerTray.instance;
	}

	public void start(Group base) {
		this.base = base;
		initTray();
		initMenu(base);

		while (!closeTray) {
			if (!display.readAndDispatch ()) display.sleep ();
		}
		image.dispose ();
		display.dispose ();
	}
	public void stop() {
		closeTray = true;
	}
	public void update() {
		root.dispose();
		initMenu(base);
	}
	protected List<Properties> getDefaultProps() {
		return null;
	}

	private void initTray() {
		trayItem = new TrayItem(tray, SWT.NONE);
		trayItem.setToolTipText(Bundle.get(Bundle.GUI, "trayToolTip"));
		trayItem.setImage(image);
	}
	private void initMenu(Group base) {
		root = new Menu(shell, SWT.POP_UP);
		menu = new RootMenu(root, base);
		
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		DisplayMode dm = ge.getDefaultScreenDevice().getDisplayMode();

	    final int screenWidth = dm.getWidth();
	    final int screenHeight = dm.getHeight();

		trayItem.addListener(SWT.MenuDetect, new Listener () {
			public void handleEvent (Event event) {
				root.setLocation(screenWidth - 50,
						         screenHeight - (screenHeight - MouseInfo.getPointerInfo().getLocation().y));
				root.setVisible(true);
			}
		});

		menu.update();
	}
}