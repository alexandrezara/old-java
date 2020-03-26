package core.pojo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Calendar;

import util.Timer;
import core.services.bundle.Bundle;
import core.services.database.AbstractRecord;
import core.services.database.TableLoader;

public class Sessions extends AbstractRecord<Sessions>{
	private static final String SCHEMA = "MANAGER";

	public Timestamp	loginDate;
	public Timestamp	logoutDate;
	public Boolean		active;

	public Sessions(ResultSet rs) {
		super(Sessions.class, SCHEMA, rs);
		try {
			loginDate = rs.getTimestamp(2);
			logoutDate = rs.getTimestamp(3);
			active = rs.getBoolean(4);
		}
		catch (SQLException e) {
			super.logger.error(Bundle.EXC, "couldNotInstantiateRecord");
		}
	}
	public Sessions(Calendar loginDate, Calendar logoutDate, boolean active) {
		super(Sessions.class, SCHEMA);
		this.loginDate = loginDate == null ? null : Timer.getTimestamp();
		this.logoutDate = logoutDate == null ? null : Timer.getTimestamp();
		this.active = active;
	}

	protected void fill(PreparedStatement ps) {
		try {
			ps.setTimestamp(1, loginDate);
			ps.setTimestamp(2, logoutDate);
			ps.setBoolean(3, active);
		} catch (SQLException e) {
			super.logger.error(Bundle.EXC, "couldNotExecuteSQL");
		}
	}
	protected void insertIntoTable() {
		TableLoader.get(Sessions.class).insert(this);
	}
	protected void deleteFromTable() {
		TableLoader.get(Sessions.class).delete(this);
	}
}