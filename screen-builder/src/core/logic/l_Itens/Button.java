package core.logic.l_Itens;

import core.logic.Item;
import core.logic.l_Elements.Screen;

public class Button extends Item {
	private Screen screen;

	public Button(String title, Screen screen) {
		super(title);
		this.screen = screen;
	}

	public Screen getScreen() {
		return screen;
	}

	public void set(String... value) {
	}
	public String get() {
		return new String();
	}
}