package gui.logic;

import interfaces.LogicItem;

import java.util.List;

import core.pojo.Properties;
import core.services.database.Table;
import core.services.database.TableLoader;

public abstract class Item implements LogicItem {
	private static final int MAX_LENGTH = 30;

	private Type	type;
	private String	title;
	public String	image;
	public boolean	visible;
	public boolean	enabled;

	public Item(Type type, String title) {
		this.type = type;
		this.setTitle(title);

		List<Properties> list = getDefaultProps();
		if (list != null) {
			for (Properties p : list) {
				Table<Properties> props = TableLoader.get(Properties.class);
				if (props.get("name", p.name) == null) {
					p.insert();
				}
			}
		}
	}
	public Item(Type type, String title, boolean visible, boolean enabled) {
		this.visible = visible;
		this.enabled = enabled;
		this.type = type;
		this.setTitle(title);

		List<Properties> list = getDefaultProps();
		if (list != null) {
			for (Properties p : list) {
				Table<Properties> props = TableLoader.get(Properties.class);
				if (props.get("name", p.name) == null) {
					p.insert();
				}
			}
		}
	}

	public Type getType() {
		return type;
	}
	public String getTitle() {
		return title;
	}
	public String getImage() {
		return image;
	}
	public boolean isVisible() {
		return visible;
	}
	public boolean isEnabled() {
		return enabled;
	}
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public void update() {
	}
	public abstract void hit(int modifier);
	protected abstract List<Properties> getDefaultProps();

	protected void setTitle(String title) {
		StringBuilder sb = new StringBuilder(MAX_LENGTH);
		for (int i = 0 ; i < MAX_LENGTH ; i++) {
			sb.append(' ');
		}
		for (int i = 0 ; i < title.length() && i < MAX_LENGTH ; i++) {
			sb.setCharAt(i + 4, title.charAt(i));
		}
		if (title.length() > MAX_LENGTH) {
			sb.setCharAt(MAX_LENGTH - 1, '.');
			sb.setCharAt(MAX_LENGTH - 2, '.');
			sb.setCharAt(MAX_LENGTH - 3, '.');
		}
		this.title = sb.toString();
	}
}