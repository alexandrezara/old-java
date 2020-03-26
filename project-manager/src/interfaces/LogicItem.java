package interfaces;


public interface LogicItem {
	public enum Type {PUSH, RADIO, CHECK, CASCADE}

	public Type getType();
	public String getTitle();
	public String getImage();
	public boolean isVisible();
	public boolean isEnabled();
	public void setEnabled(boolean enabled);
	public void hit(int modifier);
	public void update();
}