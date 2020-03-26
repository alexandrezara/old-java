package core.logic.l_Itens;

import core.logic.Item;

public class TextField extends Item {
	private static final int DEFAULT_SIZE = 20;
	private int size;

	public TextField(String title) {
		super(title);
		this.size = DEFAULT_SIZE;
	}
	public TextField(String title, int size) {
		super(title);
		this.size = size;
	}

	public int getSize() {
		return size;
	}
	public void set(String... value) {
		this.value.setSelection(value[0]);
	}
	public String get() {
		return this.value.getSelectedValues()[0];
	}
}
