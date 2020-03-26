package core.topLevel;

import interfaces.Record;

import java.sql.SQLException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import core.botLevel.DatabaseAccess;


public class Table <R extends Record> implements Iterable<R> {
	//base
	private Class<R>		clazz;
	private List<R>			records;

	//selection
	private String			field;
	private String			value;

	//structure
	private Table<R>		parent;
	private List<Table<R>>	children;
	
	public Table(Class<R> clazz) throws Exception {
		this.clazz = clazz; 
		this.records = DatabaseAccess.getTable(clazz);
		this.parent = null;
		this.children = new LinkedList<Table<R>>();
	}
	private Table(Table<R> parent, String field, String value) {
		this.clazz = parent.clazz;
		this.records = new LinkedList<R>();
		this.field = field;
		this.value = value;
		this.parent = parent;
		this.children = new LinkedList<Table<R>>();

		getRecords();
	}

	private boolean getRecords() {
		List<R> ret = new LinkedList<R>();
		try {
			for (R rec : parent.records) {
				if (clazz.getField(field).get(rec).toString().equalsIgnoreCase(value)) {
					ret.add(rec);
				}
			}
		} catch (Exception e) {
			return false;
		}
		records = ret;
		return true;
	}
	private void insertChildren(R rec) {
		if (rec.matches(field, value)) {
			records.add(rec);
			for (Table<R> child : children) {
				child.insertChildren(rec);
			}
		}
	}
	private boolean matchSelection(String field, String value) {
		return this.field.equalsIgnoreCase(field) &&
			this.value.equalsIgnoreCase(value);
	}

	public Table<R> select(String field, String value) {
		for (Table<R> child : children) {
			if (child.matchSelection(field, value)) {
				return child;
			}
		}
		Table<R> child = new Table<R>(this, field, value);
		children.add(child);
		return child;
	}
	public R get(String field, String value) {
		for (R rec : records) {
			if (rec.matches(field, value)) {
				return rec;
			}
		}
		return null;
	}
	public R getSingle(String field, String value) throws SQLException {
		R result = null;
		for (R rec : records) {
			if (rec.matches(field, value)) {
				if (result != null) {
					//ExceptionHandler.throwException(Bundle.get(Bundle.EXC, "TooManyRows"));
				}
				result = rec;
			}
		}
		return result;
	}
	public void updateAll() {
		for (R rec : records) {
			rec.update();
		}
	}
	public void insert(R rec) {
		if (parent != null) {
			parent.insert(rec);
		}
		else {
			insertChildren(rec);
		}
	}
	public void delete(R rec) {
		records.remove(rec);
	}
	public int count() {
		return records.size();
	}
	public boolean free() {
		if (parent != null) {
			this.parent.records.remove(this);
		}
		return false;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(clazz.getSimpleName());
		if (field != null) {
			sb.append(" {" + field + "=" + value + "}");
		}
		sb.append(" [");
		for (R r : records) {
			sb.append(r.toString()+ ", ");
		}
		if (records.size() > 0) {
			sb.setLength(sb.length() - 2);
		}
		sb.append("]");
		return sb.toString();
	}
	public Iterator<R> iterator() {
		return records.iterator();
	}
}