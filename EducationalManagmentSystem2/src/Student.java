import java.util.ArrayList;
import java.util.Scanner;

public class Student extends Person {
	private static ArrayList<Student> studentsList = new ArrayList<Student>();
	private static int nextPosition = 0;
	private int id = nextPosition + 1;
	private int GPA = 0;
	private ArrayList<Integer> rCoursesList = new ArrayList<Integer>();
	private int numOfRCourses = rCoursesList.size();

	public Student(String name) {
		super(name);
		studentsList.add(this);
		nextPosition++;
	}

	public int getId() {
		return id;
	}

	public static Student getStudent(int id) {
		int index = id - 1;
		return studentsList.get(index);
	}

//	checking if the id is valid
//	return 1 if valid 0 otherwise
	public static boolean isVlidId(int id) {
		if (id > 0 && id <= studentsList.size())
			return true;
		else
			return false;
	}

	// return Student object or null if email not found
	public static Student getStudent(String email) {
		Student student = null;
		for (Student s : studentsList) {
			if (s.getEmail().equals(email))
				student = s;

		}
		return student;
	}

	public void registerCourse() {
		var in = new Scanner(System.in);
		System.out.println("available courses:");
		// if there is available courses continue the logic else print no available
		// courses
		boolean available = false;
		for (int courseId = 1; courseId <= Course.getNumOfCourses(); courseId++) {
			boolean registered = false;
			for (int j = 0; j < rCoursesList.size(); j++) {
				if (courseId == rCoursesList.get(j)) {
					registered = true;
					available = false;
					break;
				}
			}
			// if the course is not registered do the logic
			// print the course id and name
			if (!registered) {
				System.out.println(courseId + "\t" + Course.getCourse(courseId).getName());
				available = true;
			}

		}
		if (available) {
			System.out.println("enter the course id you want to register or -1 to go back");
			int courseId = in.nextInt();
			in.nextLine();
			if (courseId == -1) {
				System.out.println("Main Menu");
				startStudentMainMenu();
			} else if (Course.isValidCourseId(courseId)) {
				Course.getCourse(courseId).addStudentId(this.id);
				rCoursesList.add(courseId);
				System.out.println("course : " + courseId + "\t" + Course.getCourse(courseId).getName()
						+ "\nRegisterd Successfully!");
			}
			else {
				System.out.println("invalid course id");
				registerCourse();
			}
		} else {
			System.out.println("no avilable courses to register");

		}
		System.out.println("1: back 2:close");
		int option = in.nextInt();
		in.nextLine();
		if (option == 1)
			startStudentMainMenu();
		else if (option == 2)
			System.exit(0);
		else {
			System.out.println("invalid input system will back to student main menu");
			startStudentMainMenu();

		}

	}

	public void printListCourses() {
		if (rCoursesList.isEmpty())
			System.out.println("no registered courses");
		else {
			System.out.println("course ID\t course name");
			for (int i = 0; i < rCoursesList.size(); i++) {
				System.out.println(rCoursesList.get(i) + "\t" + Course.getCourse(rCoursesList.get(i)).getName());
			}

		}
	}

	public void listCourses() {
		printListCourses();
		System.out.println("1: back 2:close");
		Scanner in = new Scanner(System.in);
		int option = in.nextInt();
		in.nextLine();
		if (option == 1)
			startStudentMainMenu();
		else if (option == 2)
			System.exit(0);
		else {
			System.out.println("invalid input system will back to student main menu");
			startStudentMainMenu();
		}

	}

	public void submitSolution(String assName, String solutionText) {
		Assignment ass = null;
		for (int i = 0; i < Assignment.getNumOfAssignments(); i++) {
			if (assName.equals(Assignment.getAssignment(i).getAssName()))
				ass = Assignment.getAssignment(i);
		}
		new AssignmentSolution(this.id, ass.getAssId(), solutionText);
	}

	public void submitSolution(int assId, String solutionText) {
		new AssignmentSolution(this.id, assId, solutionText);
	}

	// print list of assignments of a specific course for this student object
	public void printAssignments(Course course) {
		System.out.println("assignment id \t assignment name");
		boolean availableAss = false;
		for (int i = 0; i < course.getNumOfAssignments(); i++) {
			availableAss = true;
			int assId = course.getAssId(i);
			Assignment ass = Assignment.getAssignment(assId);
			if (ass.isSubmitted(this.id)) {
				AssignmentSolution assSolution = AssignmentSolution.getAssignmentSoluiton(this.id, assId);

				System.out.println(ass.getAssId() + "\t" + ass.getAssName());
				System.out.println(
						"Assignment is submited grade is : " + assSolution.getGrade() + "/" + ass.getTotalGrade());
			} else
				System.out.println(ass.getAssId() + "\t" + ass.getAssName());

		}
		if (!availableAss) {
			System.out.println("no available assignments for this course");
		}
	}

	public void viewAssignment(Course course) {

		System.out.println("List of assignments");
		var in = new Scanner(System.in);
		if (course.hasAssignment()) {
			printAssignments(course);
			System.out.println("Enter the assignment id");
			int assId = in.nextInt();
			in.nextLine();
			Assignment ass = Assignment.getAssignment(assId);
			System.out.println("assignment (" + assId + ")\t" + ass.getAssName() + "\tgrade: " + ass.getTotalGrade());
			System.out.println("1:submit soluiton\t 2:back");
			int option = in.nextInt();
			in.nextLine();
			if (option == 1) {
				System.out.println("Assignment question:");
				System.out.println(ass.getAssQuestion());
				System.out.println("enter the answer:");
				String answer = in.nextLine();
				submitSolution(assId, answer);
				System.out.println("solution is submited!");
				System.out.println("1:Back\t 2:close");
				option = in.nextInt();
				in.nextLine();
				if (option == 1) {
					startStudentMainMenu();
				} else if (option == 2)
					System.exit(0);
				else {
					System.out.println("invalid input");
					startStudentMainMenu();
				}
			} else if (option == 2) {
				startStudentMainMenu();
			} else {
				System.out.println("invalid input");
				viewAssignment(course);

			}
		} else {
			System.out.println("no assignments for this course");
		}
		System.out.println("1:Back\t 2:close");
		int option = in.nextInt();
		in.nextLine();
		if (option == 1)
			startStudentMainMenu();
		else if (option == 2)
			System.exit(0);
		else {
			System.out.println("invalid input");
			startStudentMainMenu();
		}
	}

	public void viewCourse() {
		printListCourses();
		System.out.println("choose course id to view");
		var in = new Scanner(System.in);
		int courseId = in.nextInt();

		in.nextLine();
		if (Course.isValidCourseId(courseId)) {
			Course course = Course.getCourse(courseId);
			System.out.println("this course has " + course.getNumOfAssignments() + " assignments");
			System.out.println("you submitted " + numOfSubmitedAss(course));
			// loop to get total grade of submitted solutions
			int totalsubmitedGrade = 0;
			for (int i = 0; i < course.getNumOfAssignments(); i++) {
				int assId = course.getAssId(i);
				Assignment ass = Assignment.getAssignment(assId);
				if (ass.isSubmitted(this.id)) {
					totalsubmitedGrade += AssignmentSolution.getAssignmentSoluiton(this.id, assId).getGrade();
				}
			}
			System.out
					.println("Total grade of assignments is " + totalsubmitedGrade + "/ " + course.getTotlaAssGrade());
			System.out.println("1: view assignment\t 2:Back");
			int option = in.nextInt();
			in.nextLine();
			if (option == 1) {
				viewAssignment(course);
			} else {
				System.out.println("system will back");
				startStudentMainMenu();
			}
		} else {
			System.out.println("invalid course id");
			viewCourse();
		}
	}

	// return the number of submitted assignments for specific course
	public int numOfSubmitedAss(Course course) {
		int submited = 0;
		for (int i = 0; i < course.getNumOfAssignments(); i++) {
			int assId = course.getAssId(i);
			Assignment ass = Assignment.getAssignment(assId);
			if (ass.isSubmitted(this.id))
				submited++;
		}
		return submited;

	}

	// this method print a grade of specific ass if submitted and return the grade
	// method also checks if the solution is submitted and marked
	// if grade is not submitted or marked return -1
	public int printGrade(int assId) {
		int grade = -1;
		Assignment ass = Assignment.getAssignment(assId);
		String courseName = Course.getCourse(ass.getCourseId()).getName();
		String assName = ass.getAssName();
		if (ass.isSubmitted(this.id)) {
			AssignmentSolution assSolution = AssignmentSolution.getAssignmentSoluiton(this.id, assId);
			if (assSolution.isMarked()) {
				grade = assSolution.getGrade();
				System.out.println(courseName + "\t" + assName + "\t" + grade);
			} else {
				System.out.println(courseName + "\t" + assName + "\t Not marked yet");
			}
		} else {
			System.out.println(courseName + "\t" + assName + "\t Not submitted");
		}

		return grade;
	}

	public void printgradeReport() {
		if (rCoursesList.size() != 0) {
			System.out.println("course name-ass name-grade ");
			for (int i = 0; i < rCoursesList.size(); i++) {
				Course course = Course.getCourse(rCoursesList.get(i));
				for (int j = 0; j < course.getNumOfAssignments(); j++) {
					int assId = course.getAssId(j);
					printGrade(assId);
				}
			}
		} else
			System.out.println("you didnt register courses");
		System.out.println("1:Back\t 2:close");
		Scanner in = new Scanner(System.in);
		int option = in.nextInt();
		in.nextLine();
		if (option == 2)
			System.exit(0);
		else
			startStudentMainMenu();
	}

	public void startStudentMainMenu() {
		System.out.println("1:register course\t 2:my courses\t 3:view course\t 4:grades report\t 5:logout");
		var in = new Scanner(System.in);
		int option = in.nextInt();
		in.nextLine();
		switch (option) {
		case 1:
			registerCourse();
		case 2:
			listCourses();
		case 3:
			viewCourse();
		case 4:
			printgradeReport();
		case 5:
			Main.startSignMenu();
		default:
			System.out.println("invalid input");
			startStudentMainMenu();
		}
	}
}
