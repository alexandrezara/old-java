package core.pojo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import util.Param;
import core.services.bundle.Bundle;
import core.services.database.AbstractRecord;
import core.services.database.TableLoader;

public class Plugins extends AbstractRecord<Plugins> {
	private static final String SCHEMA = "MANAGER";

	@Param (size = 32)
	public String	name;

	@Param (size = 64)
	public String	file;

	public boolean	active;

	public Plugins(ResultSet rs) {
		super(Plugins.class, SCHEMA, rs);
		try {
			name = rs.getString(2);
			file= rs.getString(3);
			active = rs.getBoolean(4);
		}
		catch (SQLException e) {
			super.logger.error(Bundle.EXC, "couldNotInstantiateRecord");
		}
	}
	public Plugins(String name, String file, boolean active) {
		super(Plugins.class, SCHEMA);
		this.name = name;
		this.file = file;
		this.active = active;
	}

	protected void fill(PreparedStatement ps) {
		try {
			ps.setString(1, name);
			ps.setString(2, file);
			ps.setBoolean(3, active);
		} catch (SQLException e) {
			super.logger.error(Bundle.EXC, "couldNotExecuteSQL");
		}
	}
	protected void insertIntoTable() {
		TableLoader.get(Plugins.class).insert(this);
	}
	protected void deleteFromTable() {
		TableLoader.get(Plugins.class).delete(this);
	}
}