package core.services;

import interfaces.Service;

import java.util.List;

import util.Timer;
import util.Logging.Logger;
import core.pojo.Properties;
import core.services.database.TableLoader;

public abstract class AbstractService implements Service {
	protected Logger logger;

	private boolean running;
	
	protected AbstractService(Class<?> clazz) {
		logger = new Logger(clazz);

		List<Properties> list = getDefaultProps();
		if (list != null) {
			for (Properties p : list) {
				if (TableLoader.get(Properties.class).get("name", p.name) == null) {
					p.insert();
				}
			}
		}
	}
	
	public void start() {
		Timer timer = new Timer();
		if (!running) {
			try {
				timer.init();
				timer.sub(startService());
				timer.stop();
			} catch (Exception e) {
				logger.error("service init fail");
				return;
			}
			running = true;
			logger.info("service init ok");
		}
	}
	
	public void stop() {
		if (running) {
			try {
				stopService();
			} catch (Exception e) {
				logger.error("service stop fail");
				return;
			}
			running = false;
			logger.info("service stop ok");
		}
	}
	
	public boolean isRunning() {
		return running;
	}
	
	protected abstract long startService() throws Exception;
	protected abstract void stopService() throws Exception;
	protected abstract List<Properties> getDefaultProps();
}