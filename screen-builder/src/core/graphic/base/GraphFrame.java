package core.graphic.base;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Point;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.metal.MetalLookAndFeel;

import ch.randelshofer.quaqua.QuaquaLookAndFeel;

import com.birosoft.liquid.LiquidLookAndFeel;
import com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel;
import com.sun.java.swing.plaf.windows.WindowsLookAndFeel;

import core.graphic.GraphFactory;
import core.graphic.g_Elements.GraphScreen;
import core.logic.l_Elements.Screen;
import de.javasoft.plaf.synthetica.SyntheticaBlackMoonLookAndFeel;

public class GraphFrame {
	public enum Look {SYSTEM, JAVA, WINDOWS, NIMBUS, BLACK_MOON, QUAQUA, LIQUID}

	private JDialog		dialog;
	private Screen		screen;
	private GraphScreen	graphScreen;

	public GraphFrame(Screen screen) {
		this.dialog = new JDialog();
		this.dialog.setTitle(screen.getTitle());
		this.screen = screen;
		this.graphScreen = GraphFactory.create(screen);
	}

	public static void setLook(Look look) {
		String nextLook;

		try {
			switch (look) {
			case SYSTEM:
				nextLook = UIManager.getSystemLookAndFeelClassName();
				break;
			case WINDOWS:
				nextLook = WindowsLookAndFeel.class.getName();
				break;

			case JAVA:
				nextLook = MetalLookAndFeel.class.getName();
				break;

			case NIMBUS:
				nextLook = NimbusLookAndFeel.class.getName();
				break;
				
			case BLACK_MOON:
				nextLook = SyntheticaBlackMoonLookAndFeel.class.getName();
				break;
				
			case QUAQUA:
				nextLook = QuaquaLookAndFeel.class.getName();
				break;

			case LIQUID:
				nextLook = LiquidLookAndFeel.class.getName();
				break;

			default:
				nextLook = UIManager.getSystemLookAndFeelClassName();
			break;
			}

			UIManager.setLookAndFeel(nextLook);
		} 
		catch (UnsupportedLookAndFeelException e) {
			System.err.println("Look and Feel Exception");
		}
		catch (ClassNotFoundException e) {
			System.err.println("Look and Feel Exception");
		}
		catch (InstantiationException e) {
			System.err.println("Look and Feel Exception");
		}
		catch (IllegalAccessException e) {
			System.err.println("Look and Feel Exception");
		}
	}
	public void show(boolean resizable) {
		dialog.setLayout(new BorderLayout());
		initPanels();
		setPosition();
		setOptions(resizable);
		dialog.setVisible(true);
	}

	private void initPanels() {
		dialog.add(graphScreen.getGraph(), BorderLayout.NORTH);
		dialog.add(new ButtonPane(this, screen.getLoadMode()).getGraph(), BorderLayout.SOUTH);
	}
	private void setPosition() {
		dialog.pack();
		dialog.setLocationRelativeTo(null);
		Point p = dialog.getLocation();
		Dimension d = dialog.getSize();
		p.x = p.x - d.width / 2;
		p.y = p.y - d.height / 2;
		dialog.setLocation(p);
	}
	private void setOptions(boolean resizable) {
		dialog.setModal(true);
		dialog.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		dialog.setResizable(resizable);
	}

	public void save() {
		graphScreen.save();
	}
	public void exit() {
		dialog.dispose();
	}
}