package core.logic.l_Itens;

import core.logic.Item;

public class CheckBox extends Item {
	public CheckBox(String title, String[] values) {
		super(title, values);
	}

	public void setValue(String value) {
		this.value.toggleSelection(value);
	}

	public void set(String... value) {
		//TODO
	}
	public String[] get() {
		//TODO
		return new String[0];
	}
}