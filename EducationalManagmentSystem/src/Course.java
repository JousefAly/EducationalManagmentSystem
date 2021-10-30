
public class Course {
	private int courseId;
	private String courseName;
	private String doctorName;
	private int numOfStudents = 0 ;
	private int[] courseStudentsIds = new int[500];
	
	//constructor
	public Course (int id , String n ,String docName) {
		courseId = id;
		courseName = n;
		doctorName = docName;
	}
	
	//methods
	public int getNumOfStudents()
	{
		return numOfStudents;
	}
}
