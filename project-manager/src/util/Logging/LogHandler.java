package util.Logging;

import java.util.LinkedList;
import java.util.List;

import util.Timer;

public class LogHandler {
	public enum Level {INFO, WARN, ERROR, DEBUG}
	private static final int MAX_LOG_SIZE = 1 * 1024 * 1024; //bytes

	private static String		mask = "[?T] ?L - ?C - ?M"; // time / level / class / message
	private static int 			logSize = 0;
	private static boolean		verbose = true;
	private static List<Log>	logList = new LinkedList<Log>();
	
	public static void log(Log log) {
		if (verbose) {
			print(log);
		}
		else {
			while(logSize + log.size() > MAX_LOG_SIZE) {
				removeLast();
			}
			logList.add(log);
			logSize += log.size();
		}
	}

	public static void setVerbose(boolean verb) {
		flush();
		verbose = verb;
	}
	public static void flush() {
		while (logList.size() > 0) {
			print(logList.get(0));
			removeFirst();
		}
	}
	
	private static void removeLast() {
		logSize -= logList.get(logList.size() - 1).size();
		logList.remove(logList.size() - 1);
	}
	private static void removeFirst() {
		logSize -= logList.get(0).size();
		logList.remove(0);
	}

	private static void print(Log log) {
		String out = mask;
		out = out.replace("?T", Timer.simpleDate(log.getTime()));
		out = out.replace("?L", log.getLevel().toString());
		out = out.replace("?C", log.getOwner().getSimpleName());
		out = out.replace("?M", log.getMessage());

		if (log.getLevel() == Level.ERROR) {
			System.err.println(out);
		}
		else {
			System.out.println(out);
		}
	}

	/*
	public static void logUserMsg(String msg, int priority) {
		LogHandler.log(Bundle.get(Bundle.LOG, "message") + priority + " : " + msg);
	}
	public static void logServiceInit(Class<?> clazz) {
		LogHandler.log(Bundle.get(Bundle.LOG, "initService") + clazz.getSimpleName());
	}
	public static void logServiceInitOk(Object obj, long time) {
		LogHandler.log(Bundle.get(Bundle.LOG, "initServiceOk") + obj.getClass().getSimpleName() + "(" + time + "ms)");
	}
	public static void logServiceInitFail(Object obj) {
		LogHandler.log(Bundle.get(Bundle.LOG, "initServiceFail") + obj.getClass().getSimpleName());
	}
	public static void logServiceStopOk(Object obj) {
		LogHandler.log(Bundle.get(Bundle.LOG, "stopServiceOk") + obj.getClass().getSimpleName());
	}
	public static void logServiceStopFail(Object obj) {
		LogHandler.log(Bundle.get(Bundle.LOG, "stopServiceFail") + obj.getClass().getSimpleName());
	}
	public static void logException(String msg, Exception e) {
		String exceptionName = e != null ? e.getClass().getSimpleName() : Bundle.get(Bundle.LOG, "userDefinedException");
		LogHandler.log(Bundle.get(Bundle.LOG, "exception") + msg + " - " + exceptionName);
	}
	 */
}