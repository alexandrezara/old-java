package core.pojo;

import interfaces.Link;
import interfaces.Spec;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import util.Param;
import core.logic.l_Itens.CheckBox;
import core.logic.l_Itens.TextField;
import core.services.bundle.Bundle;
import core.services.database.AbstractRecord;
import core.services.database.TableLoader;

public class Projects extends AbstractRecord<Projects> {
	private static final String SCHEMA = "MANAGER";

	@Param (size = 32)
	@Link (tab = "Basic Info", panel = "Basic", name = "Name", type = TextField.class)
	public String		name;

	@Param (size = 10)
	@Link (tab = "Basic Info", panel = "Basic", name = "Kaleo", type = TextField.class)
	public String		kaleo;

	@Param (size = 10)
	@Link (tab = "Basic Info", panel = "Basic", name = "GMP", type = TextField.class)
	public String		gmp;

	@Param (size = 10)
	@Link (tab = "Basic Info", panel = "Basic", name = "System", type = CheckBox.class)
	@Spec (values = {"Vantive", "People", "Mediação"})
	public String		system;

	@Link (tab = "Extra Info", panel = "Schedule", name = "Start Date", type = TextField.class)
	public Timestamp	startDate;

	@Link (tab = "Extra Info", panel = "Schedule", name = "Duration (hours)", type = TextField.class)
	public int			duration;

	@Link (tab = "Extra Info", panel = "Schedule", name = "Conclusion", type = TextField.class)
	public Timestamp	conclusion;

	@Param (size = 32)
	@Link (tab = "Extra Info", panel = "People", name = "Requestor", type = TextField.class)
	public String		requestor;

	@Param (size = 32)
	@Link (tab = "Extra Info", panel = "People", name = "Analyst", type = TextField.class)
	public String		analyst;
	
	public Projects() {
		super(Projects.class, SCHEMA);
	}

	public Projects(ResultSet rs) {
		super(Projects.class, SCHEMA, rs);
		try {
			name = rs.getString(2);
			kaleo = rs.getString(3);
			gmp = rs.getString(4);
			system = rs.getString(5);
			startDate = rs.getTimestamp(6);
			duration = rs.getInt(7);
			conclusion = rs.getTimestamp(8);
			requestor = rs.getString(9);
			analyst = rs.getString(10);
			
		}
		catch (SQLException e) {
			super.logger.error(Bundle.EXC, "couldNotInstantiateRecord");
		}
	}

	protected void fill(PreparedStatement ps) {
		try {
			ps.setString(1, name);
			ps.setString(2, kaleo);
			ps.setString(3, gmp);
			ps.setString(4, system);
			ps.setTimestamp(5, startDate);
			ps.setInt(6, duration);
			ps.setTimestamp(7, conclusion);
			ps.setString(8, requestor);
			ps.setString(9, analyst);
		} catch (SQLException e) {
			super.logger.error(Bundle.EXC, "couldNotExecuteSQL");
		}
	}

	protected void insertIntoTable() {
		TableLoader.get(Projects.class).insert(this);
	}
	protected void deleteFromTable() {
		TableLoader.get(Projects.class).delete(this);
	}
}
