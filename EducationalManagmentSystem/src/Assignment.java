
public class Assignment {
	//fields
	private int assId;
	private String courseId;
	private String assName;
	private String assQuestion;
	private AssignmentSolution[] solutions = new AssignmentSolution[500];
	//constructor
	public Assignment(int id, String assName) {
		assId = id ; 
		this.assName = assName;
	}
	public void setAssignmentQuestion(String question) {
		assQuestion =question;
	}
	
}
