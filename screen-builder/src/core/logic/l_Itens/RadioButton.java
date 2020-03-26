package core.logic.l_Itens;

import core.logic.Item;

public class RadioButton extends Item {
	public RadioButton(String title, String[] values) {
		super(title, values);
	}

	public void setValue(String value) {
		this.value.setSelection(value);
	}

	public void set(String... value) {
		//TODO
	}
	public String get() {
		//TODO
		return new String();
	}
}