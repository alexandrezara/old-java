package core.logic.l_Itens;

import core.logic.Item;

public class Spinner extends Item {
	private int min;
	private int max;
	private int step;

	public Spinner(String title, int min, int max, int step) {
		super(title);
		this.min = min;
		this.max = max;
		this.step = step;
	}

	public int getMin() {
		return min;
	}
	public int getMax() {
		return max;
	}
	public int getStep() {
		return step;
	}

	public void setValue(int value) {
		this.value.setSelection(Integer.toString(value));
	}

	public void set(String... value) {
		//TODO
	}
	public Integer get() {
		//TODO
		return 0;
	}
}