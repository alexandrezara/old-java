package core.logic;

import interfaces.Displayable;
import enums.LoadMode;

public abstract class Item implements Displayable {
	protected String	title;
	protected Value		value;
	protected LoadMode	loadMode;

	protected Item(String title) {
		this.title = title;
		this.value = new Value();
	}
	protected Item(String title, String[] values) {
		this.title = title;
		this.value = new Value(values);
	}

	public void setLoadMode(LoadMode loadMode) {
		this.loadMode = loadMode;
	}

	public String getTitle() {
		return title;
	}
	public LoadMode getLoadMode() {
		return loadMode;
	}
	public Value getValue() {
		return value;
	}

	public abstract void set(String... value);
	public abstract Object get();
}