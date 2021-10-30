
public class AssignmentSolution {
	private int solutionId;
	private int assignmentId;
	char[] solution = new char[1000];
	
	public AssignmentSolution(int solutionId) {
		this.assignmentId  =solutionId;
	}
	public void submitSolution(char[] solution)
	{
		this.solution = solution;
	}
}
