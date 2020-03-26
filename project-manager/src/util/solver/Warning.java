package util.solver;

import java.util.Iterator;
import java.util.List;

public class Warning {
	private Solver			solver;
	private String			message;
	private List<Solution>	solutions;

	public Warning(Solver solver, String message, List<Solution> solutions) {
		this.solver = solver;
		this.message = message;
		this.solutions = solutions;
	}
	
	public String getMessage() {
		return message;
	}
	public Iterator<Solution> getSolutions() {
		return solutions.iterator();
	}
	
	public boolean solve(Solution solution) {
		return solver.solve(this, solution);
	}
}
