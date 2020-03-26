package core.services.plugin;

import java.io.File;
import java.io.FileFilter;
import java.util.LinkedList;
import java.util.List;

import util.Message;
import util.Timer;
import core.pojo.Plugins;
import core.pojo.Properties;
import core.services.AbstractService;
import core.services.bundle.Bundle;
import core.services.database.Table;
import core.services.database.TableLoader;

public class PluginLoader extends AbstractService {
	private static final File PATH = new File("base/plugins/");
	private static final String EXT = ".plg";
	private static final String AUTO_LOAD_PROP = "askForPlugins";
	private static final FileFilter FILE_FILTER = new FileFilter() {
		public boolean accept(File pathname) {
			return pathname.getName().endsWith(EXT);
		}
	};

	private static PluginLoader instance;

	private PluginLoader() {
		super(PluginLoader.class);
	}
	public static PluginLoader getInstance() {
		if (instance == null) {
			instance = new PluginLoader();
		}
		return instance;
	}

	private void loadDialog(List<String> newPlugins) {
		String ss = "";
		for (String s : newPlugins) {
			ss += (s + "\n");
		}
		Message.warn("Os seguintes plugins foram encontrados:\n" + ss);		
	}

	private void initPlugins() {
		/*try {
			Table<Plugins> components = TableLoader.get(Plugins.class);
			File f = null;
			for (Plugins c : plugins) {
				f = new File(PATH, "PlgTeste.jar"); //c.file
				ClassLoader cl = new URLClassLoader(new URL[] { f.toURI().toURL() });

				Class<?> cls = cl.loadClass("plg.ExitTeste");
				
			}
		} catch (ClassNotFoundException e) {
			System.err.println("Class not Found");
		} catch (MalformedURLException e) {
			System.err.println("MalformedURLException");
		} catch (IllegalArgumentException e) {
			System.err.println("IllegalArgumentException");
		}*/
	}

	public long startService() {
		Timer timer = new Timer();
		Table<Plugins> plugins = TableLoader.get(Plugins.class);
		List<String> newPlugins = new LinkedList<String>();

		for (File f : PATH.listFiles(FILE_FILTER)) {
			if (plugins.get("file", f.getName()) == null) {
				newPlugins.add(f.getName());
			}
		}

		if (newPlugins.size() > 0 &&
				TableLoader.get(Properties.class).get("name", AUTO_LOAD_PROP).value.equalsIgnoreCase("true")) {

			int opt;
			timer.init();
			opt = Message.ask(Bundle.get(Bundle.MSG, "newPlugins"), new String[] {
				Bundle.MSG, "newPlugins.opt1", 
				Bundle.MSG, "newPlugins.opt2", 
				Bundle.MSG, "newPlugins.opt3" 
			});
			timer.stop();


			switch (opt) {
			case 0:
				timer.init();
				loadDialog(newPlugins);
				timer.stop();
				break;
			case 1:
				break;
			case 2:
				Properties p = TableLoader.get(Properties.class).get("name", AUTO_LOAD_PROP); 
				p.value = "false";
				p.update();
				break;
			}
		}

		initPlugins();
		return timer.get();
	}
	public void stopService() {
	}
	public List<Properties> getDefaultProps() {
		List<Properties> props = new LinkedList<Properties>();
		props.add(new Properties(AUTO_LOAD_PROP, "true", true));
		return props;
	}
}