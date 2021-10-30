import java.util.*;

public class Doctor extends Person {
	// arrayList of doctors
	private static ArrayList<Doctor> doctorsList = new ArrayList<Doctor>();
	private static int nextPosition = 0;
	private int id = nextPosition + 1;
	// array list of doctor courses
	private ArrayList<Integer> doctorCourseIdsList = new ArrayList<Integer>();

	public Doctor(String n) {
		super(n);
		doctorsList.add(this);
		nextPosition++;
	}

	// methods

	public int getId() {
		return id;
	}

	public static Doctor getDoctor(int id) {
		int index = id - 1;
		return doctorsList.get(index);
	}

//	checking if the id is valid
//	return 1 if valid 0 otherwise
	public static boolean isVlidId(int id) {
		if (id > 0 && id <= doctorsList.size())
			return true;
		else
			return false;
	}

	// return object or null if email not found
	public static Doctor getDoctor(String email) {
		Doctor doctor = null;
		for (Doctor d : doctorsList) {
			if (d.getEmail().equals(email))
				doctor = d;
		}
		return doctor;
	}

	/**
	 * this method add course to the doctor
	 * 
	 * @param {@code int } the student id
	 * 
	 */
	public void addCourse(int studentId) {
		doctorCourseIdsList.add(studentId);
	}

	public void createCourse(String cName) {
		Course course = new Course(cName, this.id);
		System.out.println("Course id is:" + course.getId());
		doctorCourseIdsList.add(course.getId());
	}

	/**
	 * displays list of doctor courses .Display the course ids and names
	 * 
	 */
	public void displayListCourses() {
		System.out.println("ID\tname");
		if (doctorCourseIdsList.isEmpty())
			System.out.println("no courses yet");
		else {
			for (int i = 0; i < doctorCourseIdsList.size(); i++) {
				int courseId = doctorCourseIdsList.get(i);
				System.out.println(courseId + "\t" + Course.getCourse(courseId).getName());
			}
		}
	}

	// take course id , assignment Name , question and the total grade of the
	// assignment
	public void createAss(int courseId, String assiName, String question, int grade) {
		new Assignment(courseId, assiName, question, grade);
	}

	public void viewCourseMenu(Course course) {
		System.out.println("1:List assignments\t 2:create assignment\t 3:Back");
		Scanner in = new Scanner(System.in);
		int option = in.nextInt();
		in.nextLine();
		int option2;
		if (option == 1) {
			course.listDoctorAssignments(this);
		} else if (option == 2) {
			System.out.println("Enter assignment name:");
			String name = in.nextLine();
			System.out.println("Enter question:");
			String question = in.nextLine();
			System.out.println("enter total grade to the assignment");
			int tGrade = in.nextInt();
			in.nextLine();
			new Assignment(course.getId(), name, question, tGrade);
			System.out.println("1:back\t 2:close");
			option2 = in.nextInt();
			in.nextLine();
			if (option2 == 1)
				viewCourseMenu(course);
			else if (option2 == 2)
				System.exit(0);
			else {
				System.out.println("invalid input system will back to view course menu");
				viewCourseMenu(course);
			}

		} else if (option == 3) {
			Main.startDoctorMainMenu(this);
		} else {
			System.out.println("invalid input");
			viewCourseMenu(course);
		}

	}

	public void viewAssignmentMenu(Assignment assi) {
		System.out.println("1:Assignment information\t 2:show grade report\t 3:solution list\t 4:Back");
		Scanner in = new Scanner(System.in);
		int option = in.nextInt();
		int option2;
		if (option == 1) {
			assi.printAssInfo();
			System.out.println("1:back\t 2:close");
			option2 = in.nextInt();
			in.nextLine();
			if (option2 == 1)
				viewAssignmentMenu(assi);
			else if (option2 == 2)
				System.exit(0);
			else {
				System.out.println("invalid input system will back to view assignment menu");
				viewAssignmentMenu(assi);
			}
		} else if (option == 2) {
			assi.printGradeReport();
			System.out.println("1:back\t 2:close");
			option2 = in.nextInt();
			in.nextLine();
			if (option2 == 1)
				viewAssignmentMenu(assi);
			else if (option2 == 2)
				System.exit(0);
			else {
				System.out.println("invalid input system will back to view assignment menu");
				viewAssignmentMenu(assi);
			}
		} else if (option == 3) {
			assi.listSolutionsMenu();
		} else if (option == 4) {
			int courseId = assi.getCourseId();
			Course course = Course.getCourse(courseId);
			viewCourseMenu(course);
		} else {
			System.out.println("invalid input");
			viewAssignmentMenu(assi);
		}

	}

	public void viewSolutionMenu(AssignmentSolution solution) {
		solution.viewSolutionMenu();
	}

	public boolean hasCourse() {
	if(doctorCourseIdsList.size() == 0)
		return false;
	else
		return true;
	}
}
