package util.Logging;

import java.util.Calendar;

import core.services.bundle.Bundle;

import util.Logging.LogHandler.Level;

public class Logger {
	private Class<?> owner;

	public Logger(Class<?> owner) {
		this.owner = owner;
	}

	private String compose(String str, String[] args) {
		StringBuilder sb = new StringBuilder(str.length() + args.length * 10);
		for (int i = 0, j = 0 ; i < str.length() - 1 ; i++) {
			if (str.charAt(i) == '{' && str.charAt(i + 1) == '}') {
				sb.append(args[j]);
				j++;
				i++;
			}
			else {
				sb.append(str.charAt(i));
			}
		}
		return sb.toString();
	}

	public void info(String msg) {
		LogHandler.log(new Log(Level.INFO, owner, Calendar.getInstance(), msg));
	}
	public void info(String bundle, String key) {
		LogHandler.log(new Log(Level.INFO, owner, Calendar.getInstance(), Bundle.get(bundle, key)));
	}
	public void info(String msg, String...args) {
		LogHandler.log(new Log(Level.INFO, owner, Calendar.getInstance(), compose(msg, args)));
	}
	public void info(String bundle, String key, String...args) {
		String msg = compose(Bundle.get(bundle, key), args);
		LogHandler.log(new Log(Level.INFO, owner, Calendar.getInstance(), msg));
	}

	public void warn(String msg) {
		LogHandler.log(new Log(Level.WARN, owner, Calendar.getInstance(), msg));
	}
	public void warn(String bundle, String key) {
		LogHandler.log(new Log(Level.WARN, owner, Calendar.getInstance(), Bundle.get(bundle, key)));
	}
	public void warn(String msg, String...args) {
		LogHandler.log(new Log(Level.WARN, owner, Calendar.getInstance(), compose(msg, args)));
	}
	public void warn(String bundle, String key, String...args) {
		String msg = compose(Bundle.get(bundle, key), args);
		LogHandler.log(new Log(Level.WARN, owner, Calendar.getInstance(), msg));
	}
	
	public void error(String msg) {
		LogHandler.log(new Log(Level.ERROR, owner, Calendar.getInstance(), msg));
	}
	public void error(String bundle, String key) {
		LogHandler.log(new Log(Level.ERROR, owner, Calendar.getInstance(), Bundle.get(bundle, key)));
	}
	public void error(String msg, String...args) {
		LogHandler.log(new Log(Level.ERROR, owner, Calendar.getInstance(), compose(msg, args)));
	}
	public void error(String bundle, String key, String...args) {
		String msg = compose(Bundle.get(bundle, key), args);
		LogHandler.log(new Log(Level.ERROR, owner, Calendar.getInstance(), msg));
	}
	
	public void debug(String msg) {
		LogHandler.log(new Log(Level.DEBUG, owner, Calendar.getInstance(), msg));
	}
	public void debug(String bundle, String key) {
		LogHandler.log(new Log(Level.DEBUG, owner, Calendar.getInstance(), Bundle.get(bundle, key)));
	}
	public void debug(String msg, String...args) {
		LogHandler.log(new Log(Level.DEBUG, owner, Calendar.getInstance(), compose(msg, args)));
	}
	public void debug(String bundle, String key, String...args) {
		String msg = compose(Bundle.get(bundle, key), args);
		LogHandler.log(new Log(Level.DEBUG, owner, Calendar.getInstance(), msg));
	}
}