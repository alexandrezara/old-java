package core.logic;

import interfaces.Link;
import interfaces.Spec;
import core.logic.l_Itens.Button;
import core.logic.l_Itens.CheckBox;
import core.logic.l_Itens.ComboBox;
import core.logic.l_Itens.RadioButton;
import core.logic.l_Itens.TextArea;
import core.logic.l_Itens.TextField;

public abstract class ItemFactory {
	public static Item create(Link link, Spec spec) {
		Class<?> type = link.type();
		
		if (type == TextField.class) {
			return new TextField(link.name());			
		}
		else if (type == Button.class) {
			return new Button(link.name(), null);			
		}
		else if (type == CheckBox.class) {
			return new CheckBox(link.name(), spec.values());			
		}
		else if (type == ComboBox.class) {
			return new ComboBox(link.name(), spec.values());			
		}
		else if (type == RadioButton.class) {
			return new RadioButton(link.name(), spec.values());			
		}
		else if (type == TextArea.class) {
			return new TextArea(link.name());			
		}
		else {
			return null;
		}
	}
}