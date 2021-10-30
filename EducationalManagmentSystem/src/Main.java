import java.util.*;

public class Main {
	// making ArrayLists for courses , Assignments (which include arrays of
	// solutions),Doctors , Students, TAs
	// the element id is his (index + 1)
	private static ArrayList<Course> courses = new ArrayList<Course>();
	private static ArrayList<Assignment> assignments = new ArrayList<Assignment>();
	private static ArrayList<Doctor> doctors = new ArrayList<Doctor>();
	private static ArrayList<TA> TAs = new ArrayList<TA>();
	private static ArrayList<Student> students = new ArrayList<Student>();

	// method of sign up
	public static void startSignMenu() {
		System.out.println("1:sign in\t 2:sign up");
		Scanner in = new Scanner(System.in);
		int input = in.nextInt();

		int input2;
		// when choose sign in
		if (input == 1) {
			// figure which role is or back to sign menu
			System.out.println("1:Student\t 2:Doctor\t 3:TA\t 0:Sign minue");
			input2 = in.nextInt();
			// the next line solving the issue of bad input of nextLine() method
			// by consuming the leftover new line

			in.nextLine();
			int id;
			String password;
			switch (input2) {
			case 1:
				System.out.println("Enter id:");
				 id = in.nextInt();
				
				in.nextLine();
				// index of the objects is its id - 1
				int studentIndex = id - 1;
				System.out.println("Enter Password");
				 password = in.nextLine();
				// searching ,actually we will not search because we have the id we will access
				// directly, in the arrayList of doctors by id and matching the password
				// if logined successfully make start the object customized menu
				String StudentPassword = students.get(studentIndex).getPassword();
				if(StudentPassword.equals(password ) ) {
					System.out.println("*********logined successfully***** Hello " + students.get(studentIndex).getName());
					
				}
				else {
					System.out.println("wrong id/password the password is real is "+ password + students.get(studentIndex).getPassword());
					startSignMenu();
				}
				break;
			case 2:
				System.out.println("Enter id:");
				 id = in.nextInt();
				
				in.nextLine();
				// index of the objects is its id - 1
				int doctorIndex = id - 1;
				System.out.println("Enter Password");
				 password = in.nextLine();
				// searching ,actually we will not search because we have the id we will access
				// directly, in the arrayList of doctors by id and matching the password
				// if logined successfully make start the object customized menu
				String doctorPassword = doctors.get(doctorIndex).getPassword();
				if(doctorPassword.equals(password ) ) {
					System.out.println("*********logined successfully***** Hello Dr," + doctors.get(doctorIndex).getName());
					
				}
				else {
					System.out.println("wrong id/password the password is real is "+ password + doctors.get(doctorIndex).getPassword());
					startSignMenu();
				}
				break;
			case 3:
				System.out.println("Enter id:");
				 id = in.nextInt();
				
				in.nextLine();
				// index of the objects is its id - 1
				int taIndex = id - 1;
				System.out.println("Enter Password");
				 password = in.nextLine();
				// searching ,actually we will not search because we have the id we will access
				// directly, in the arrayList of doctors by id and matching the password
				// if logined successfully make start the object customized menu
				String taPassword = TAs.get(taIndex).getPassword();
				if(taPassword.equals(password ) ) {
					System.out.println("*********logined successfully***** Hello " + TAs.get(taIndex).getName());
					
				}
				else {
					System.out.println("wrong id/password the password is real is "+ password + TAs.get(taIndex).getPassword());
					startSignMenu();
				}
				break;
				
			}

		} else if (input == 2) {
			System.out.println("who are you ?");
			System.out.println("1:Student\t 2:Doctor\t 3:TA\t 0:Sign minue");
			input = in.nextInt();
			// the next line solving the issue of bad input of nextLine() method
			// by consuming the leftover new line

			in.nextLine();

			// when user choose option create object of the chosen option and store it in
			// its arrayList
			int id;
			String name;
			String password;
			switch (input) {
			case 1:
				id = students.size() + 1;
				System.out.println("Please enter your name : ");
				name = in.nextLine();
				Student student = new Student(id, name);
				students.add(student);
				System.out.println("Enter password");
				password = in.nextLine();
				student.setPassword(password);
				System.out.println("Rigistered!!!! your id is :" + id);
				System.out.println("1:back\t 2:close");
				input2 = in.nextInt();
				if (input2 == 1)
					startSignMenu();
				break;
			case 2:
				id = doctors.size() + 1;
				System.out.println("Please enter your name : ");
				name = in.nextLine();
				Doctor doctor = new Doctor(id, name);
				doctors.add(doctor);
				System.out.println("Enter password");
				password = in.nextLine();
				doctor.setPassword(password);
				System.out.println("Rigistered!!!! your id is :" + id);
				System.out.println("1:back\t 2:close");
				input2 = in.nextInt();
				if (input2 == 1)
					startSignMenu();
				break;
			case 3:
				id = TAs.size() + 1;
				System.out.println("Please enter your name : ");
				name = in.nextLine();
				TA ta = new TA(id, name);
				TAs.add(ta);
				System.out.println("Enter password");
				password = in.nextLine();
				ta.setPassword(password);
				System.out.println("Rigistered!!!! your id is :" + id);
				System.out.println("1:back\t 2:close");
				input2 = in.nextInt();
				if (input2 == 1)
					startSignMenu();
				break;
			case 0:
				startSignMenu();
				break;
			default:
				System.out.println("invalid input !");
				startSignMenu();
			}

		}

		else {
			System.out.println("invalid input");
			startSignMenu();
		}
	}

	public static void main(String[] args) {

		// signing up
		startSignMenu();
	}

}
