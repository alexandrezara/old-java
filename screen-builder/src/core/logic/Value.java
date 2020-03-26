package core.logic;

import java.util.LinkedList;
import java.util.List;

public class Value {
	private List<String>	avaliableValues;
	private List<String>	selectedValues;

	public Value() {
		this.avaliableValues = new LinkedList<String>();
		this.selectedValues = new LinkedList<String>();
	}
	public Value(String[] avaliable) {
		this.avaliableValues = new LinkedList<String>();
		this.selectedValues = new LinkedList<String>();

		for (String str : avaliable) {
			addAvaliableValue(str);
		}
	}

	private void addAvaliableValue(String value) {
		for (String str : avaliableValues) {
			if (value.equalsIgnoreCase(str)) {
				return;
			}
		}
		avaliableValues.add(value);
	}
	public void clearSelection() {
		selectedValues = new LinkedList<String>();
	}
	public boolean addSelection(String value) {
		if (avaliable(value) && !selected(value)) {
			selectedValues.add(value);
			return true;
		}
		return false;
	}
	public boolean setSelection(String value) {
		if (avaliable(value)) {
			selectedValues.clear();
			selectedValues.add(value);
			return true;
		}
		return false;
	}
	public boolean toggleSelection(String value) {
		if (avaliable(value)) {
			if (!selected(value)) {
				selectedValues.add(value);
				return true;
			}
			else {
				for (int i = 0 ; i < selectedValues.size() ; i++) {
					if (selectedValues.get(i).equalsIgnoreCase(value)) {
						selectedValues.remove(i);
						return true;
					}
				}
			}
		}
		return false;
	}
	private boolean avaliable(String value) {
		if (avaliableValues.size() == 0) {
			return true;
		}
		for (String avaliable : avaliableValues) {
			if (avaliable.equalsIgnoreCase(value)) {
				return true; 
			}
		}
		return false;
	}
	private boolean selected(String value) {
		for (String avaliable : selectedValues) {
			if (avaliable.equalsIgnoreCase(value)) {
				return true; 
			}
		}
		return false;
	}

	public String[] getAvaliableValues() {
		return avaliableValues.toArray(new String[avaliableValues.size()]);
	}
	public String[] getSelectedValues() {
		return selectedValues.toArray(new String[selectedValues.size()]);
	}
}