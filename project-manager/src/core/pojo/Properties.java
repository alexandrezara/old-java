package core.pojo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import util.Param;
import core.services.bundle.Bundle;
import core.services.database.AbstractRecord;
import core.services.database.TableLoader;

public class Properties extends AbstractRecord<Properties> {
	private static final String SCHEMA = "MANAGER";

	@Param (size = 64)
	public String	name;

	@Param (size = 256)
	public String	value;

	public Boolean	active;

	public Properties(ResultSet rs) {
		super(Properties.class, SCHEMA, rs);
		try {
			name = rs.getString(2);
			value = rs.getString(3);
			active = rs.getBoolean(4);
		}
		catch (SQLException e) {
			super.logger.error(Bundle.EXC, "couldNotInstantiateRecord");
		}
	}
	public Properties(String name, String value, boolean active) {
		super(Properties.class, SCHEMA);
		this.name = name;
		this.value = value;
		this.active = active;
	}
	
	protected void fill(PreparedStatement ps) {
		try {
			ps.setString(1, name);
			ps.setString(2, value);
			ps.setBoolean(3, active);
		} catch (SQLException e) {
			super.logger.error(Bundle.EXC, "couldNotExecuteSQL");
		}
	}
	protected void insertIntoTable() {
		TableLoader.get(Properties.class).insert(this);
	}
	protected void deleteFromTable() {
		TableLoader.get(Properties.class).delete(this);
	}
}