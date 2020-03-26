package sample;

import interfaces.Link;
import interfaces.Spec;

import java.sql.ResultSet;
import java.sql.Timestamp;

import core.logic.l_Itens.CheckBox;
import core.logic.l_Itens.TextField;

public class Projects {
	@Link (tab = "Basic Info", panel = "Basic", name = "Name", type = TextField.class)
	public String name;

	@Link (tab = "Basic Info", panel = "Basic", name = "Kaleo", type = TextField.class)
	public String kaleo;

	@Link (tab = "Basic Info", panel = "Basic", name = "GMP", type = TextField.class)
	public String gmp;

	@Link (tab = "Basic Info", panel = "Basic", name = "System", type = CheckBox.class)
	@Spec (values = {"Vantive", "People", "Mediação"})
	public String system;

	@Link (tab = "Extra Info", panel = "Schedule", name = "Start Date", type = TextField.class)
	public Timestamp startDate;

	@Link (tab = "Extra Info", panel = "Schedule", name = "Duration (hours)", type = TextField.class)
	public int duration;

	@Link (tab = "Extra Info", panel = "Schedule", name = "Conclusion", type = TextField.class)
	public Timestamp conclusion;

	@Link (tab = "Extra Info", panel = "People", name = "Requestor", type = TextField.class)
	public String requestor;

	@Link (tab = "Extra Info", panel = "People", name = "Analyst", type = TextField.class)
	public String analyst;
	
	public Projects() {
	}

	public Projects(ResultSet rs) {
	}
}
