package core.botLevel;

import interfaces.Record;

import java.util.HashMap;

import core.topLevel.Table;

public class TableLoader {
	private static HashMap<Class<? extends Record>, Table<? extends Record>> tables =
		new HashMap<Class<? extends Record>, Table<? extends Record>>();
	
	private static <T extends Record> boolean load(Class<T> clazz) {
		if (!tables.containsKey(clazz)) {
			try {
				tables.put(clazz, new Table<T>(clazz));
			} catch (Exception e) {
				return false;
			}
		}
		return true;
	}

	@SuppressWarnings("unchecked")
	public static <T extends Record> Table<T> get(Class<T> clazz) {
		if (load(clazz)) {
			return (Table<T>)tables.get(clazz);
		}
		return null;
	}
}