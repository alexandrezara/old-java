package core.topLevel;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class ConnectionManager {
	private static final String PREFIX = "jdbc:hsqldb:file:";

	private static Connection connection;

	private static String URL;;
	private static String USR;;
	private static String PSW;;

	public static boolean setConnection(String folder, String database, String user, String pass) {
		ConnectionManager.URL = PREFIX + folder + database;
		ConnectionManager.USR = user;
		ConnectionManager.PSW = pass;

		return getConnection() != null;
	}
	public static Connection getConnection() {
		try {
			if (connection == null || connection.isClosed()) {
				Class.forName("org.hsqldb.jdbcDriver");
				connection = DriverManager.getConnection(URL, USR, PSW);
			}
		} catch (ClassNotFoundException e) {
			//ExceptionHandler.logException(Bundle.get(Bundle.EXC, "driverNotFound"), e);
		} catch (SQLException e) {
			//ExceptionHandler.logException(Bundle.get(Bundle.EXC, "couldNotConnect"), e);
		}

		return connection;
	}
}