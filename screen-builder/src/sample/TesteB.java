package sample;

import core.graphic.base.GraphFrame;
import core.graphic.base.GraphFrame.Look;
import creation.AutoLinkedScreen;
import enums.LoadMode;

public class TesteB {
	public static void main(String[] args) {
		Projects projects = new Projects();
		AutoLinkedScreen als = new AutoLinkedScreen("TesteB", LoadMode.NEW, projects, true);

		GraphFrame.setLook(Look.BLACK_MOON);
		GraphFrame gf = new GraphFrame(als);
		gf.show(false);
	}
}