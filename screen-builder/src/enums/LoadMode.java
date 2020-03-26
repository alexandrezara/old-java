package enums;

public enum LoadMode {

	NEW(true, false),
	VIEW(false, true),
	EDIT(true, true);

	public final boolean enable;
	public final boolean load;

	private LoadMode(boolean enable, boolean load) {
		this.enable = enable;
		this.load = load;
	}
}