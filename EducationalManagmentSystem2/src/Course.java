import java.util.*;

public class Course {
	// arrayList of all the courses
	private static ArrayList<Course> coursesList = new ArrayList<Course>();
	private static int nextCourseId = 1;

	private int courseId;
	private String courseName = "no name detected";
	private int doctorId;
	private String doctorName = "no name detected";

	private ArrayList<Integer> courseStudentIds = new ArrayList<Integer>();
	private ArrayList<Integer> assignmentIdsList = new ArrayList<Integer>();

	// constructor
//	take the name of the course and the doctor id
	public Course(String n, int doctorId) {
		// adding the course to the list
		coursesList.add(this);
		courseId = nextCourseId;
		nextCourseId++;
		courseName = n;
		this.doctorId = doctorId;

	}

	// methods
	public static int getNumOfCourses() {
		return coursesList.size();
	}

	public static boolean isValidCourseId(int id) {
		if (id > 0 && id <= coursesList.size())
			return true;
		else
			return false;
	}

	public int getNumOfAssignments() {
		return assignmentIdsList.size();
	}

	public void addAssId(int id) {
		assignmentIdsList.add(id);
	}

	public int getAssId(int i) {

		return assignmentIdsList.get(i);
	}

	// get course by id
	public int getDoctorId() {
		return doctorId;
	}

	public static Course getCourse(int id) {

		return coursesList.get(id - 1);
	}

	public int getId() {
		return courseId;
	}

	public int getNumOfStudents() {
		return courseStudentIds.size();
	}

	// set course name
	public void setCourseName(String n) {
		courseName = n;
	}

	public String getName() {
		return courseName;
	}

	public void setCourseDoctorName(String dname) {
		doctorName = dname;
	}

	public String getCourseDoctorName() {
		return doctorName;
	}

	public void addStudentId(int studentId) {
		courseStudentIds.add(studentId);
	}

	public void listDoctorAssignments(Doctor doc) {
		if (hasAssignment()) {
			System.out.println("Assignments");
			for (int i = 0; i < assignmentIdsList.size(); i++) {
				System.out.println(i + ") " + Assignment.getAssignment(assignmentIdsList.get(i)).getAssName());
			}
			System.out.println("choose what assignment to display or -1 to go back");

			Scanner in = new Scanner(System.in);
			int option = in.nextInt();
			in.nextLine();
			if (option == -1) {
				doc.viewCourseMenu(this);
			} else {
				if (option >= 0 && option < assignmentIdsList.size()) {
					Assignment ass = Assignment.getAssignment(assignmentIdsList.get(option));
					doc.viewAssignmentMenu(ass);
				} else {
					System.out.println("invalid input");
					listDoctorAssignments(doc);
				}
			}
		}
		else {
			System.out.println("no assignments yet");
			System.out.println("Course Menu");
			doc.viewCourseMenu(this);
			
		}

	}

	public boolean hasAssignment() {
		if (assignmentIdsList.size() != 0)
			return true;
		else
			return false;
	}

	// return total grade of the course assignment
	public int getTotlaAssGrade() {
		int total = 0;
		for (int i = 0; i < assignmentIdsList.size(); i++)
			total += Assignment.getAssignment(assignmentIdsList.get(i)).getTotalGrade();
		return total;
	}
}
