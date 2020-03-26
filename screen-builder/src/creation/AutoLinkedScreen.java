package creation;

import interfaces.Link;
import interfaces.Spec;

import java.lang.reflect.Field;

import core.logic.ItemFactory;
import enums.LoadMode;

public class AutoLinkedScreen extends SerialLinkedScreen {

	public AutoLinkedScreen(String title, LoadMode loadMode, Object linkedObject, boolean literal) {
		super(title, loadMode, linkedObject);
		autoLink(linkedObject, literal);
	}

	private void autoLink(Object linkedObject, boolean literal) {
		Field[] fields = linkedObject.getClass().getFields();
		for (Field f : fields) {
			Link p = f.getAnnotation(Link.class);
			addItem(p.tab(), p.panel(), ItemFactory.create(p, f.getAnnotation(Spec.class)));
		}
	}

	
}