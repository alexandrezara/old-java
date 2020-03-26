package core.services.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import util.Logging.Logger;
import core.services.bundle.Bundle;

public abstract class ConnectionFactory {
	private static Logger logger;
	private static final String URL = "jdbc:hsqldb:file:C:/Documents and Settings/alexandreg/Meus documentos/Java/Workspace/Manager/database/default";
	private static final String USR = "SA";
	private static final String PSW = "";
	
	private static Connection connection;

	public static Connection getConnection() {
		ConnectionFactory.logger = new Logger(ConnectionFactory.class);
		try {
			if (connection == null || connection.isClosed()) {
				Class.forName("org.hsqldb.jdbcDriver");
				connection = DriverManager.getConnection(URL, USR, PSW);
			}
		} catch (ClassNotFoundException e) {
			ConnectionFactory.logger.error(Bundle.EXC, "driverNotFound");
		} catch (SQLException e) {
			ConnectionFactory.logger.error(Bundle.EXC, "couldNotConnect");
		}
		return connection;
	}
}