package linking;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import core.logic.Item;

public class Linker {
	private Object			 object;
	private Map<Item, Field> links;

	public Linker(Object object) {
		this.object = object;
		this.links = new HashMap<Item, Field>();
	}

	public boolean link(Item item, String linkedField) {
		try {
			Field field = object.getClass().getField(linkedField);
			links.put(item, field);
			return true;
		} catch (SecurityException e) {
			return false;
		} catch (NoSuchFieldException e) {
			return false;
		}
	}

	public boolean update() {
		try {
			for (Item i : links.keySet()) {
				
				Field f = links.get(i);
				Object obj = i.get();
				
				f.set(object, obj);
			}
			return true;
		} catch (IllegalArgumentException e) {
			return false;
		} catch (IllegalAccessException e) {
			return false;
		}
	}
}