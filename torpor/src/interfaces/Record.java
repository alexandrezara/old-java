package interfaces;

public interface Record {
	public boolean create();
	public boolean insert();
	public boolean update();
	public boolean delete();
	public boolean matches(String field, String value);
}