package sample;

import core.graphic.base.GraphFrame;
import core.graphic.base.GraphFrame.Look;
import core.logic.l_Elements.Screen;
import core.logic.l_Itens.Button;
import core.logic.l_Itens.CheckBox;
import core.logic.l_Itens.ComboBox;
import core.logic.l_Itens.RadioButton;
import core.logic.l_Itens.TextArea;
import core.logic.l_Itens.TextField;
import enums.LoadMode;

public class TesteA {

	public static void main(String[] args) {
		String[] interesses = {"Esporte", "Musica", "Filmes"};
		String[] universidades = {"UFU", "Unitri", "Uniube", "Uniminas"};
		String[] areas = {"Exatas", "Humanas", "Biom�dicas"};

		Screen screen = new Screen("Teste", LoadMode.NEW);

		//Tab 1
		screen.addItem("Pessoal", "Geral", new TextField("Nome"));
		screen.addItem("Pessoal", "Geral", new CheckBox("Interesses", interesses));
		screen.addItem("Pessoal", "Avan�ado", new Button("Editar Informa��es Avan�adas", null));

		//Tab 2
		screen.addItem("Profissional", "Geral", new TextArea("Descri��o"));

		//Tab 3
		screen.addItem("Acad�mico", "Geral", new ComboBox("Universidade", universidades));
		screen.addItem("Acad�mico", "Geral", new RadioButton("Area", areas));

		
		GraphFrame.setLook(Look.BLACK_MOON);
		GraphFrame gf = new GraphFrame(screen);
		gf.show(false);
	}
}