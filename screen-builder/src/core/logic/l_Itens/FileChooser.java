package core.logic.l_Itens;

import java.io.File;

import core.logic.Item;

public class FileChooser extends Item {
	public enum Content {FILE, FOLDER}

	private Content content;
	private String	filter;

	public FileChooser(String title, Content content) {
		super(title);
		this.content = content;
	}
	public FileChooser(String title, Content content, String filter) {
		super(title);
		this.content = content;
		this.filter = filter;
	}

	public Content getContent() {
		return content;
	}
	public String getFilter() {
		return filter;
	}

	public void setValue(String path) {
		File file = new File(path);
		if (content == Content.FILE && !file.isFile()) {
			return;
		}
		if (content == Content.FOLDER && file.isFile()) {
			return;
		}
		if (!file.getName().endsWith(filter)) {
			return;
		}
		this.value.setSelection(path);
	}

	public void set(String... value) {
		//TODO
	}
	public String get() {
		//TODO
		return new String();
	}
}