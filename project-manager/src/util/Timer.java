package util;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Timer {
	private boolean running;
	private long total;
	private long curr;
	
	public Timer() {
		running = true;
		total = 0;
		curr = System.currentTimeMillis();
	}

	public void init() {
		running = true;
		curr = System.currentTimeMillis();
	}
	public void stop() {
		if (running) {
			total += System.currentTimeMillis() - curr;
			curr = 0;
		}
	}
	public void sub(long time) {
		total -= time;
	}
	public void sub(Timer timer) {
		total -= timer.total;
	}
	public long get() {
		return total;
	}
	
	public static Timestamp getTimestamp() {
		return new Timestamp(GregorianCalendar.getInstance().getTime().getTime());
	}
	public static Timestamp getTimestamp(Calendar calendar) {
		return new Timestamp(calendar.getTime().getTime());
	}
	public static String simpleDate(Calendar calendar) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		return sdf.format(calendar.getTime());
	}
}