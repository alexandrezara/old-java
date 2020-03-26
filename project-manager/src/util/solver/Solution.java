package util.solver;

public class Solution {
	private Warning	warning;
	private String	description;
	
	public Solution(Warning warning, String description) {
		this.warning = warning;
		this.description = description;
	}
	
	public String getDescription() {
		return description;
	}
	public boolean use() {
		return warning.solve(this);
	}
}