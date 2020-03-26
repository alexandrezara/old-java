package core.services.bundle;

import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import core.pojo.Properties;
import core.services.AbstractService;
import core.services.database.Table;
import core.services.database.TableLoader;

public class Bundle extends AbstractService {
	private static Bundle instance;

	public static final String EXC = "Exceptions";
	public static final String GUI = "GuiMessages";
	public static final String LOG = "LogMessages";
	public static final String MSG = "UserMessages";

	private static final String BUNDLE_PATH = "core.services.bundle.";
	private static Locale locale = Locale.getDefault();

	private Bundle() {
		super(Bundle.class);
	}
	public static Bundle getInstance() {
		if (instance == null) {
			instance = new Bundle();
		}
		return instance;
	}

	protected long startService() throws Exception {
		Table<Properties> props = TableLoader.get(Properties.class);
		String lang = props.get("name", "language").value;
		String country = props.get("name", "country").value;
		
		if (lang.equalsIgnoreCase("auto")) {
			lang = Locale.getDefault().getLanguage();
		}
		if (country.equalsIgnoreCase("auto")) {
			country = Locale.getDefault().getCountry();
		}

		locale =  new Locale(lang, country);
		return 0;
	}
	protected void stopService() throws Exception {
		Table<Properties> props = TableLoader.get(Properties.class);
		Properties lang = props.get("name", "language");
		Properties country = props.get("name", "country");
		
		lang.value = locale.getLanguage();
		country.value = locale.getCountry();
		
		lang.update();
		country.update();
	}

	public static void setLocale(String language, String country) {
		locale = new Locale(language, country);
	}

	public static String get(String base, String key) {
		try {
			return ResourceBundle.getBundle(BUNDLE_PATH + base, locale).getString(key);
		} catch (MissingResourceException e) {
			Bundle.instance.logger.error("InvalidLocale/MissingResource: " + locale.toString() + "." + base + "." + key);
		}
		return null;
	}
	public List<Properties> getDefaultProps() {
		List<Properties> props = new LinkedList<Properties>();
		props.add(new Properties("language", "pt", true));
		props.add(new Properties("country", "BR", true));
		return props;
	}
}