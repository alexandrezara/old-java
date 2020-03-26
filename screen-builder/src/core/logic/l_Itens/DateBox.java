package core.logic.l_Itens;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import core.logic.Item;

public class DateBox extends Item {
	public enum DateMask {DD_MM_AA, DD_MM_AAAA}

	private final DateMask mask;

	public DateBox(String title, DateMask mask) {
		super(title);
		this.mask = mask;
	}

	public void setValue(String value) {
		String pattern;
		switch (mask) {
		case DD_MM_AA:
			pattern = "dd/MM/yy";
			break;
		case DD_MM_AAAA:
			pattern = "dd/MM/yyyy";
			break;
		default:
			pattern = null;
			break;
		}

		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		try {
			sdf.parse(value);
			this.value.setSelection(value);
		} catch (ParseException e) {
			return;
		}
	}

	public void set(String... value) {
		//TODO
	}
	public String[] get() {
		//TODO
		return new String[0];
	}
}