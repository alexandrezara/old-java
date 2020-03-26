package core.services;

import gui.ManagerTray;
import interfaces.Service;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JProgressBar;

import core.graphic.base.GraphFrame;
import core.graphic.base.GraphFrame.Look;

public class Services {
	private Map<Class<?>, Service> services;
	private Map<Integer, Class<?>> startOrder;
	private Map<Integer, Class<?>> stopOrder;
	private ManagerTray tray;

	public Services() {
		this.services = new HashMap<Class<?>, Service>();
		this.startOrder = new HashMap<Integer, Class<?>>();
		this.stopOrder = new HashMap<Integer, Class<?>>();
	}

	public void addService(Class<?> service, int startPos, int stopPos) {
		services.put(service, null);
		startOrder.put(startPos, service);
		stopOrder.put(stopPos, service);
	}

	@SuppressWarnings("unchecked")
	public <C> C get(Class<C> serviceClass) {
		return (C)services.get(serviceClass);
	}

	public ManagerTray getTray() {
		return tray;
	}

	public void init() {
		GraphFrame.setLook(Look.BLACK_MOON);

		JFrame jf = new JFrame("Project Manager");
		JProgressBar jpb = new JProgressBar(0, (services.size() * 2) + 1);
		jf.add(jpb);
		jf.setSize(300, 70);
		jf.setLocationRelativeTo(null);
		jf.setVisible(true);

		jpb.setValue(0);
		for (int i = 0 ; i < startOrder.size() ; i++) {
			try {
				Method method = startOrder.get(i).getMethod("getInstance", new Class[] {});
				Service service = (Service) method.invoke(null, new Object[] {});
				jpb.setValue(jpb.getValue() + 1);
				services.put(startOrder.get(i), service);
				service.start();
				jpb.setValue(jpb.getValue() + 1);
			} catch (IllegalArgumentException e) {
			} catch (IllegalAccessException e) {
			} catch (InvocationTargetException e) {
			} catch (SecurityException e) {
			} catch (NoSuchMethodException e) {
			}
		}

		tray = ManagerTray.getInstance();
		jpb.setValue(jpb.getValue() + 1);
		jf.dispose();
	}
}