package util.solver;

import java.util.LinkedList;
import java.util.List;

public class Solver {
	private List<Warning> warnings;
	
	protected Solver() {
		this.warnings = new LinkedList<Warning>();
	}

	protected void addWarning(Warning warning) {
		warnings.add(warning);
	}
	protected void remWarning(Warning warning) {
		warnings.remove(warning);
	}

	public List<Warning> getWarnings() {
		return warnings;
	}
	
	public boolean solve(Warning warning, Solution solution) {
		remWarning(warning);
		return true;
	}
}