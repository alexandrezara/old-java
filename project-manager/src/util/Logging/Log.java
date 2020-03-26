package util.Logging;

import java.util.Calendar;

import util.Logging.LogHandler.Level;

public class Log {
	private Level	 level;
	private Class<?> owner;
	private Calendar time;
	private String	 message;

	public Log(Level level, Class<?> owner, Calendar time, String message) {
		this.level = level;
		this.owner = owner;
		this.time = time;
		this.message = message;
	}

	public Level getLevel() {
		return level;
	}
	public Class<?> getOwner() {
		return owner;
	}
	public Calendar getTime() {
		return time;
	}
	public String getMessage() {
		return message;
	}

	public int size() {
		return message.length();
	}
}