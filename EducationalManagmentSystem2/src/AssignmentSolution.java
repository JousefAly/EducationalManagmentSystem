import java.util.*;

public class AssignmentSolution {
	private static ArrayList<AssignmentSolution> solutionsList = new ArrayList<AssignmentSolution>();
	// index of the solution in solutionList is solutionId - 1
	private static int nextSolutionId = 1;
	private int solutionId;
	private int studentId;
	private int assignmentId;
	private int courseId ;
	private int doctorId ;
	private String solutionText;
	private int grade = 0;
	private int totalGrade ;
	private boolean marked = false;
	private boolean passed = false;
	private String comment = "no comment";

	/**
	 * 
	 * @param the student id
	 * @param the assignment id
	 * @param the solution text which is a string
	 */
	public AssignmentSolution(int studentId, int assId, String text) {
		solutionId = nextSolutionId;
		nextSolutionId++;
		solutionText = text;
		this.studentId = studentId;
		this.assignmentId = assId;
		solutionsList.add(this);
		// add the solution id to the assignment also
		Assignment ass = Assignment.getAssignment(assId);
		courseId = ass.getCourseId();
		doctorId = Course.getCourse(courseId).getDoctorId();
		totalGrade = ass.getTotalGrade();
		ass.addSolution(solutionId);
	}

	public int getSolutionId() {
		return solutionId;
	}

	public int getStudentId() {
		return studentId;
	}

	public int getAssignmentId() {
		return assignmentId;
	}

	public String getSolutionText() {
		return solutionText;
	}

	// take the id
	public static AssignmentSolution getAssignmentSolution(int i) {
		i--;
		return solutionsList.get(i);
	}

	public void setGrade(int g) {
		grade = g;
		marked = true;
		// mark is >= half of the total grade then student succeed
		if (g >= (0.5 * totalGrade)) {
			passed = true;
			Assignment.getAssignment(assignmentId).increasePassedStudents();
		}

	}

	public boolean isPassed() {
		return passed;
	}

	public int getGrade() {
		return grade;
	}

	public boolean isMarked() {
		return marked;
	}

	public void setComment(String c) {
		comment = c;
	}

	public String getComment() {
		return comment;
	}

	public void viewSolutionMenu() {
		System.out.println("1:show answer\t 2:set grade\t 3:Comment\t 4:back");
		Scanner in = new Scanner(System.in);
		int option = in.nextInt();
		int option2;
		in.nextLine();
		if (option == 1) {
			System.out.println("Answer:\n" + getSolutionText());
			System.out.println("1:back\t 2:close");
			option2 = in.nextInt();
			in.nextLine();
			if (option2 == 1)
				viewSolutionMenu();
			else if (option == 2)
				System.exit(0);
			else {
				System.out.println("invalid Input program will back");
				viewSolutionMenu();
			}

		} else if (option == 2) {
			System.out.println("Enter grade:");
			int grade = in.nextInt();
			in.nextLine();
			setGrade(grade);
			System.out.println("grade: " + getGrade() + "is submited to student:" + getStudentId() + "  "
					+ Student.getStudent(getStudentId()).getName());
			System.out.println("1:back\t 2:close");
			option2 = in.nextInt();
			in.nextLine();
			if (option2 == 1)
				viewSolutionMenu();
			else if (option == 2)
				System.exit(0);
			else {
				System.out.println("invalid Input program will back");
				viewSolutionMenu();
			}

		} else if (option == 3) {
			System.out.println("Enter comment:");
			String comment = in.nextLine();
			setComment(comment);
			System.out.println("1:back\t 2:close");
			option2 = in.nextInt();
			in.nextLine();
			if (option2 == 1)
				viewSolutionMenu();
			else if (option == 2)
				System.exit(0);
			else {
				System.out.println("invalid Input program will back");
				viewSolutionMenu();
			}

		} else if (option == 4) {
			// this is bad implementation improve next time
			Assignment ass = Assignment.getAssignment(getAssignmentId());
			Doctor doc = Doctor.getDoctor(doctorId);
			doc.viewAssignmentMenu(ass);

		} else {
			System.out.println("invalid input!");
			viewSolutionMenu();
		}
	}

	// return assignment solution of a student for a specific assignment
	public static AssignmentSolution getAssignmentSoluiton(int studentId, int assId) {
		AssignmentSolution assSolution = null;
		for (int i = 0 ; i < solutionsList.size();i++) {
			if(solutionsList.get(i).getStudentId() == studentId && solutionsList.get(i).getAssignmentId() == assId)
				assSolution = solutionsList.get(i);	
		}
		return assSolution;
	}
}
