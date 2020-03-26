package core.services.session;

import java.util.GregorianCalendar;
import java.util.List;

import util.Message;
import util.Timer;
import core.pojo.Properties;
import core.pojo.Sessions;
import core.services.AbstractService;
import core.services.bundle.Bundle;
import core.services.database.Table;
import core.services.database.TableLoader;

public class SessionManagement extends AbstractService {
	private static SessionManagement instance;

	private Table<Sessions>	sessions;
	private Sessions 		current;

	private SessionManagement() {
		super(SessionManagement.class);
	}
	public static SessionManagement getInstance() {
		if (instance == null) {
			instance = new SessionManagement();
		}
		return instance;
	}

	public long startService() throws Exception {
		Timer timer = new Timer();
		sessions = TableLoader.get(Sessions.class);
		Table<Sessions> activeSessions = sessions.select("active", "true");
		for (Sessions s : activeSessions) {
			s.active = false;
			s.update();
		}
		if (activeSessions.count() > 0) {
			timer.init();
			Message.warn(Bundle.get(Bundle.MSG, "OpenSession"));
			timer.stop();
		}
		current = new Sessions(GregorianCalendar.getInstance(), null, true);
		current.insert();
		return timer.get();
	}
	public void stopService() {
		current.active = false;
		current.logoutDate = Timer.getTimestamp();
		current.update();
	}
	public List<Properties> getDefaultProps() {
		return null;
	}
}