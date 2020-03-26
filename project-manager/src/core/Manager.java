package core;

import gui.ManagerTray;
import util.Timer;
import util.Logging.LogHandler;
import util.Logging.Logger;
import core.pojo.Properties;
import core.services.bundle.Bundle;
import core.services.components.ComponentLoader;
import core.services.database.DatabaseAccess;
import core.services.database.TableLoader;
import core.services.session.SessionManagement;

public class Manager {
	private static Logger				logger;
	private static DatabaseAccess		database;
	private static Bundle				bundle;
	private static SessionManagement	sessions;
	private static ComponentLoader		components;
	private static ManagerTray			tray;

	private Manager() {
		logger = new Logger(Manager.class);
		database = DatabaseAccess.getInstance();
		bundle = Bundle.getInstance();
		sessions = SessionManagement.getInstance();
		components = ComponentLoader.getInstance();
		tray = ManagerTray.getInstance();
	}

	public static void start() {
		new Manager();

		database.start();
		bundle.start();
		sessions.start();
		components.start();
		tray.start(components.getBaseGroup());
	}
	public static void stop() {
		sessions.stop();
		bundle.stop();
		database.stop();
		components.stop();
		tray.stop();
	}

	public static void update() {
		Timer timer = new Timer();
		components.update();
		tray.update();
		timer.stop();
		System.out.println(Bundle.get(Bundle.LOG, "updateTray") + " (" + timer.get() + "ms)");
	}

	public static void setProp(String name, String value) {
		Properties p = TableLoader.get(Properties.class).get("name", name);
		if (p == null) {
			p = new Properties(name, value, true);
			p.insert();
		}
		else {
			p.value = value;
			p.update();
		}
	}
	public static String getProp(String name) {
		return TableLoader.get(Properties.class).getSingle("name", name).value;
	}

	public static void main(String[] args) {
		try {
			Manager.start();
		} catch (Exception e) {
			logger.error("Exception " + e.getMessage());
		}
		LogHandler.flush();
	}
}