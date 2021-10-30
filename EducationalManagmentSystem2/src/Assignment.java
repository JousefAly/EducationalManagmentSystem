import java.util.*;

public class Assignment {
	// fields
	private static ArrayList<Assignment> assignmentsList = new ArrayList<Assignment>();
	private static int nextAssId = 1;
	private int assId;
	private int courseId;
	private String assName;
	private String assQuestion;

	private int numOfStudents = 0;
	private ArrayList<Integer> solutionIds = new ArrayList<Integer>();

	private int grade;
	// when set a grade to a solution if passed increase passedStudents by 1
	private int passedStudents;
	// failed Students = total students - passed students
	

	// constructor
	public Assignment(int courseId, String assName, String question, int grade) {
		assId = nextAssId;
		nextAssId++;
		this.assName = assName;
		this.courseId = courseId;
		this.assQuestion = question;
		this.grade = grade;
		assignmentsList.add(this);
		Course.getCourse(courseId).addAssId(assId);
		numOfStudents = Course.getCourse(courseId).getNumOfStudents();
	}

	public int getCourseId() {
		return courseId;
	}

	// return the total grade of the assignment
	public int getTotalGrade() {
		return grade;
	}

	public int getAssId() {
		return assId;
	}

	public String getAssQuestion() {
		return assQuestion;
	}

	public String getAssName() {
		return assName;
	}

	// add solution to the assignment by id.
	public void addSolution(int solutionId) {
		solutionIds.add(solutionId);

	}

	public static Assignment getAssignment(int id) {
		id--;
		return assignmentsList.get(id);

	}

	public void increasePassedStudents() {
		passedStudents++;
	}

	public void printAssInfo() {
		int submitedSolutions = solutionIds.size();
		if (submitedSolutions != 0) {
			System.out.println("Assignment Question:\n" + assQuestion + "\nAssignment id: " + assId
					+ "\nAssignment name :" + assName + "\nNumber of submited solutions:" + submitedSolutions
					+ "\nPass rate : " + (passedStudents / submitedSolutions) * 100 + "%");
		} else
			System.out.println("Assignment Question:\n" + assQuestion + "\nAssignment id: " + assId
					+ "\nAssignment name :" + assName + "\nNumber of submited solutions:" + submitedSolutions
					+ "\nPass rate : \"no submited solutins yet\"");

	}

	public void printGradeReport() {
		System.out.println(
				"Number of passed students :" + passedStudents + "\nNumber of failed students :" + (solutionIds.size() - passedStudents)
						+ "\nTotal of submited solutions is : (" + solutionIds.size() + " / " + Course.getCourse(courseId).getNumOfStudents() + ")");
	}

	public void listSolutionsMenu() {
		System.out.println("List of solutions ");
		if (solutionIds.size() != 0) {
			System.out.println("oprion)Student ID:\t StudentName");
			for (int i = 0; i < solutionIds.size(); i++) {
				AssignmentSolution solution = AssignmentSolution.getAssignmentSolution(solutionIds.get(i));
				if (solution.isMarked()) {
					System.out.print(
							i + ")" +solution.getStudentId() + "\t " + Student.getStudent(solution.getStudentId()).getName()
									+ "\tSolution is marekd grade is : " + solution.getGrade() + "\n");

				} else {
					System.out.println(
							 i + ")" + solution.getStudentId() + "\t " + Student.getStudent(solution.getStudentId()).getName());
				}
				System.out.println("choose what option you want to view its solution");
				Scanner in = new Scanner(System.in);
				int option = in.nextInt();
				in.nextLine();
				if (option >= 0 && option < solutionIds.size()) {
					solution = AssignmentSolution.getAssignmentSolution(solutionIds.get(option));
					solution.viewSolutionMenu();
				} else {
					System.out.println("invalid input");
					listSolutionsMenu();
				}
			}
		}
		else {
			System.out.println("no solutions yet");
			int docId = Course.getCourse(courseId).getDoctorId();
			Doctor doc = Doctor.getDoctor(docId);
			doc.viewAssignmentMenu(this);
		}
	}

	public static int getNumOfAssignments() {
		return assignmentsList.size();
	}

	public boolean isSubmitted(int studentId) {
		boolean submited = false;
		for (int i = 0; i < solutionIds.size(); i++) {
			AssignmentSolution assSolution = AssignmentSolution.getAssignmentSolution(i + 1);
			if (assSolution.getStudentId() == studentId) {
				submited = true;
				break;
			}

		}
		return submited;
	}
}
