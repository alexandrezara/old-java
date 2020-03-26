package interfaces;

import java.awt.Container;
import java.awt.GraphicsEnvironment;

public interface Graphical {
	public static final int H_GAP_SIZE = 5;
	public static final int V_GAP_SIZE = 2;
	public static final int MAX_WIDTH = GraphicsEnvironment.
										getLocalGraphicsEnvironment().
										getDefaultScreenDevice().
										getDisplayMode().
										getWidth();	

	public String getTitle();
	public Container getGraph();
	public void save();
	public void load();
}
