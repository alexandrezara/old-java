package core.graphic;

import interfaces.Displayable;
import interfaces.Graphical;

import java.awt.Container;
import java.awt.LayoutManager;
import java.util.LinkedList;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Group;
import javax.swing.GroupLayout.ParallelGroup;

import core.logic.Element;

public abstract class AbstractGraphElement<E extends Element<? extends Displayable>, C extends Container, G extends Graphical> implements Graphical {
	protected E			element;
	protected C			container;
	protected List<G>	children;

	protected AbstractGraphElement(E element, C container) {
		this.element = element;
		this.container = container;
		this.children = new LinkedList<G>();
		for (Displayable d : element.getChildren()) {
			G graph = GraphFactory.create(d);
			children.add(graph);
		}
	}

	public String getTitle() {
		return element.getTitle();
	}
	public Container getGraph() {
		return container;
	}

	protected LayoutManager getDefaultLayout() {
		GroupLayout layout = new GroupLayout(container);

		Group hGroup = layout.createSequentialGroup();
		Group vGroup = layout.createSequentialGroup();
		
		ParallelGroup pGroup = layout.createParallelGroup();
		for (G gp : children) {
			pGroup.addComponent(gp.getGraph());
		}
		hGroup.addGroup(pGroup);
		
		for (G gp : children) {
			vGroup.addComponent(gp.getGraph());
		}
				
		layout.setHorizontalGroup(hGroup);
		layout.setVerticalGroup(vGroup);

		return layout;
	}

	public void save() {
		for (G gp : children) {
			gp.save();
		}
	}
	public void load() {
		for (G gp : children) {
			gp.save();
		}
	}
}