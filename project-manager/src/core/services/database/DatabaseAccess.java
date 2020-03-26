package core.services.database;

import interfaces.Record;

import java.lang.reflect.Constructor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import core.pojo.Properties;
import core.services.AbstractService;
import core.services.bundle.Bundle;

public class DatabaseAccess extends AbstractService {
	private static DatabaseAccess instance;

	private DatabaseAccess() {
		super(DatabaseAccess.class);
	}
	public static DatabaseAccess getInstance() {
		if (instance == null) {
			instance = new DatabaseAccess();
		}
		return instance;
	}

	public long startService() throws SQLException {
		ConnectionFactory.getConnection().isValid(0);
		return 0;
	}
	public void stopService() {
		execStatement("SHUTDOWN");
	}

	public static void commit() {
		execStatement("COMMIT");
	}

	public static <T extends Record> List<T> getTable(Class<T> clazz) throws Exception {
		try {
			Constructor<T> c = clazz.getConstructor(ResultSet.class);
	
			ResultSet rs = execQuery("SELECT * FROM MANAGER." + clazz.getSimpleName());
			List<T> table = new LinkedList<T>();
			while (rs.next()) {
				table.add(c.newInstance(rs));	
			}
			return table;
		} catch (Exception e) {
			DatabaseAccess.instance.logger.error(Bundle.EXC, "couldNotLoadTable");
		}

		return null;
	}
	public static ResultSet getRecord(Class<? extends Record> clazz, int id) {
		try {
			ResultSet rs = execQuery("SELECT * FROM " + clazz.getSimpleName() + " WHERE ID = " + id);
			if (rs.next()) {
				return rs;
			}
		} catch (Exception e) {
			DatabaseAccess.instance.logger.error(Bundle.EXC, "couldNotLoadTable");
		}

		return null;
	}

	public static PreparedStatement updateRecord(Class<? extends Record> clazz, String... fields) {
		try {
			Connection conn = ConnectionFactory.getConnection();
			StringBuilder sb = new StringBuilder(128);
			sb.append("UPDATE MANAGER." + clazz.getSimpleName() + " SET ");
			
			for (String f : fields) {
				sb.append(f + " = ?, ");
			}
			sb.deleteCharAt(sb.length() - 1);
			sb.deleteCharAt(sb.length() - 1);
			sb.append(" WHERE ID = ?");
			
			return conn.prepareStatement(sb.toString());
		} catch (SQLException e) {
			DatabaseAccess.instance.logger.error(Bundle.EXC, "couldNotExecuteSQL");
			return null;
		}
	}
	public static PreparedStatement insertRecord(Class<? extends Record> clazz, int fieldCount) {
		try {
			Connection conn = ConnectionFactory.getConnection();
			StringBuilder sb = new StringBuilder(128);
			sb.append("INSERT INTO MANAGER." + clazz.getSimpleName() + " VALUES( ");
			
			for (int i = 0 ; i < fieldCount ; i++) {
				sb.append("?, ");
			}
			sb.deleteCharAt(sb.length() - 1);
			sb.deleteCharAt(sb.length() - 1);
			sb.append(")");
			
			return conn.prepareStatement(sb.toString());
		} catch (SQLException e) {
			DatabaseAccess.instance.logger.error(Bundle.EXC, "couldNotExecuteSQL");
			return null;
		}
	}
	public static boolean deleteRecord(Class<? extends Record> clazz, int id) {
		try {
			Connection conn = ConnectionFactory.getConnection();
			StringBuilder sb = new StringBuilder(128);
			sb.append("DELETE FROM MANAGER." + clazz.getSimpleName() + " WHERE ID = ?");
			PreparedStatement ps = conn.prepareStatement(sb.toString());
			ps.setInt(1, id);
			return ps.execute();
		} catch (SQLException e) {
			DatabaseAccess.instance.logger.error(Bundle.EXC, "couldNotExecuteSQL");
			return false;
		}
	}

	private static boolean execStatement(String sql) {
		Statement stmt;
		try {
			Connection conn = ConnectionFactory.getConnection();
			stmt = conn.createStatement();
			stmt.execute(sql);
			return true;
		} catch (SQLException e) {
			DatabaseAccess.instance.logger.error(Bundle.EXC, "couldNotExecuteSQL");
			return false;
		}
	}
	private static ResultSet execQuery(String sql) {
		Statement stmt;
		try {
			Connection conn = ConnectionFactory.getConnection();
			stmt = conn.createStatement();
			return stmt.executeQuery(sql);
		} catch (SQLException e) {
			DatabaseAccess.instance.logger.error(Bundle.EXC, "couldNotExecuteSQL");
			return null;
		}
	}
	public List<Properties> getDefaultProps() {
		return null;
	}
}