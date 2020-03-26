package core.graphic;

import interfaces.Displayable;
import interfaces.Graphical;
import core.graphic.g_Elements.GraphPanel;
import core.graphic.g_Elements.GraphScreen;
import core.graphic.g_Elements.GraphTab;
import core.graphic.g_Itens.GraphButton;
import core.graphic.g_Itens.GraphCheckBox;
import core.graphic.g_Itens.GraphComboBox;
import core.graphic.g_Itens.GraphRadioButton;
import core.graphic.g_Itens.GraphTextArea;
import core.graphic.g_Itens.GraphTextField;
import core.logic.l_Elements.Panel;
import core.logic.l_Elements.Screen;
import core.logic.l_Elements.Tab;
import core.logic.l_Itens.Button;
import core.logic.l_Itens.CheckBox;
import core.logic.l_Itens.ComboBox;
import core.logic.l_Itens.RadioButton;
import core.logic.l_Itens.TextArea;
import core.logic.l_Itens.TextField;

public abstract class GraphFactory {
	@SuppressWarnings("unchecked")
	public static <G extends Graphical> G create(Displayable d) {
		Graphical result = null;

		if (d instanceof Screen) {
			result = new GraphScreen((Screen)d);			
		}
		else if (d instanceof Tab) {
			result = new GraphTab((Tab)d);
		}
		else if (d instanceof Panel) {
			result = new GraphPanel((Panel)d);
		}
		
		else if (d instanceof TextField) {
			result = new GraphTextField((TextField)d);			
		}
		else if (d instanceof Button) {
			result = new GraphButton((Button)d);
		}
		else if (d instanceof CheckBox) {
			result = new GraphCheckBox((CheckBox)d);
		}
		else if (d instanceof ComboBox) {
			result = new GraphComboBox((ComboBox)d);
		}
		else if (d instanceof RadioButton) {
			result = new GraphRadioButton((RadioButton)d);
		}
		else if (d instanceof TextArea) {
			result = new GraphTextArea((TextArea)d);
		}
		else {
			return null;
		}
		return (G)result;
	}
}