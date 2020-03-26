package core.logic.l_Itens;

import core.logic.Item;

public class TextArea extends Item {
	public TextArea(String title) {
		super(title);
	}

	public void setValue(String value) {
		this.value.setSelection(value);
	}

	public void set(String... value) {
		//TODO
	}
	public String get() {
		return new String();
	}
}
