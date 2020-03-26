package interfaces;

import enums.LoadMode;

public interface Displayable {
	public String getTitle();

	public void setLoadMode(LoadMode loadMode);
	public LoadMode getLoadMode();
}