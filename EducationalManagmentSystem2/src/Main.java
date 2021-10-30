import java.util.*;
/**
 * 
 * @author yousef aly
 * @version 1.0.0
 * general comments
 * known bugs:
 * 1. System does not check for input type.So you need to be careful when it asks  for an integer type.
 * 
 * possible features:
 * 1.Teaching Assisstants does not have a role to play in the system.
 *
 */

public class Main {
	public static void signUp() {
		System.out.println("1:Student\t 2:Doctor\t 3:Teaching Assisstant\t 0:Back");
		Scanner in2 = new Scanner(System.in);
		int option = in2.nextInt();
		in2.nextLine();
		String name;
		String password;
		String email;
		String phoneNumber;
		switch (option) {

		case 1:
			System.out.println("Enter name:");
			name = in2.nextLine();
			var student = new Student(name);
			System.out.println("Enter password");
			password = in2.nextLine();
			student.setPassword(password);
			System.out.println("Enter Email");
			email = in2.nextLine();
			student.setEmail(email);
			System.out.println("Enter phone number");
			phoneNumber = in2.nextLine();
			student.setPhoneNumber(phoneNumber);
			System.out.println("Registered !\t Hello," + student.getName()
					+ "\n Please memorise your id you will login with it :  (ID :" + student.getId() + ")");
			System.out.println("1:Sign Menu\t  2:close");
			option = in2.nextInt();
			in2.nextLine();
			if (option == 1)
				startSignMenu();
			else if (option == 2)
				break;
			else {
				System.out.println("invalid input program go back to start menu");
				startSignMenu();
			}
			break;
		case 2:
			System.out.println("Enter name:");
			name = in2.nextLine();
			var doctor = new Doctor(name);
			System.out.println("Enter password");
			password = in2.nextLine();
			doctor.setPassword(password);
			System.out.println("Enter Email");
			email = in2.nextLine();
			doctor.setEmail(email);
			System.out.println("Enter phone number");
			phoneNumber = in2.nextLine();
			doctor.setPhoneNumber(phoneNumber);
			System.out.println("Registered !\t Hello," + doctor.getName()
					+ "\n Please memorise your id you will login with it :  (ID :" + doctor.getId() + ")");
			System.out.println("1:Sign Menu\t  2:close");
			option = in2.nextInt();
			in2.nextLine();
			if (option == 1)
				startSignMenu();
			else if (option == 2)
				break;
			else {
				System.out.println("invalid input program go back to start menu");
				startSignMenu();
			}
			break;
		case 3:
			System.out.println("Enter name:");
			name = in2.nextLine();
			var tA = new TA(name);
			System.out.println("Enter password");
			password = in2.nextLine();
			tA.setPassword(password);
			System.out.println("Enter Email");
			email = in2.nextLine();
			tA.setEmail(email);
			System.out.println("Enter phone number");
			phoneNumber = in2.nextLine();
			tA.setPhoneNumber(phoneNumber);
			System.out.println("Registered ! \nHello," + tA.getName()
					+ "\n Please memorise your id you will login with it :  (ID :" + tA.getId() + ")");
			System.out.println("1:Sign Menu\t  2:close");
			option = in2.nextInt();
			in2.nextLine();
			if (option == 1)
				startSignMenu();
			else if (option == 2)
				break;
			else {
				System.out.println("invalid input program go back to start menu");
				startSignMenu();
			}

			break;
		case 0:
			startSignMenu();
			break;
		default:
			System.out.println("Please Enter a valid input from 1 -> 4 or 0 to back sign menu.");
			signUp();

		}
	}

	// sign in menu
	public static void signIn() {
		boolean signedIn = false;
		var in2 = new Scanner(System.in);
		System.out.println("1:Student\t 2:Doctor\t 3:TA\t 0:back");
		int role = in2.nextInt();
		switch (role) {
		case 1:
			Student student = null;
			System.out.println("1:sign in using id\t 2:sign in using email");
			int signInOption = in2.nextInt();
			in2.nextLine();

			if (signInOption == 1) {
				System.out.println("Enter Id");
				int id = 0;
				if (in2.hasNextInt())
					id = in2.nextInt();
				else {
					System.out.println("enter integer number");
					signIn();
				}

				in2.nextLine();
				// checking if the id is valid first
				if (Student.isVlidId(id)) {
					System.out.println("Enter password:");
					String password = in2.nextLine();
					// check password matching
					student = Student.getStudent(id);
					if (student == null) {
						System.out.println("id not found");
						signIn();
					}
					if (student.getPassword().equals(password)) {
						signedIn = true;

					}
				} else {
					System.out.println("id is not valid");
					signIn();
				}

			} else if (signInOption == 2) {
				System.out.println("Enter Email:");
				String email = in2.nextLine();
				System.out.println("Enter Password: ");
				String password = in2.nextLine();
				student = Student.getStudent(email);
				// if null back to sign in menu
				if (student == null) {
					System.out.println("student not found (student = null)");
					signIn();
				}
				if (student.getPassword().equals(password))
					signedIn = true;
			} else {
				System.out.println("invalid input");
				signIn();
			}
			if (signedIn) {
				System.out.println("signed in successfully! hello," + student.getName());
				// do some code
				student.startStudentMainMenu();
			} else {
				System.out.println("didnt sign in may be wrong id or password");
				startSignMenu();
			}
			break;
		// sign in option 2 doctor
		case 2:
			// some code

			Doctor doctor = null;
			System.out.println("1:sign in using id\t 2:sign in using email");
			signInOption = in2.nextInt();
			in2.nextLine();

			if (signInOption == 1) {
				System.out.println("Enter Id");
				int id = in2.nextInt();
				in2.nextLine();
				// checking if the id is valid first
				if (Doctor.isVlidId(id)) {
					System.out.println("Enter password:");
					String password = in2.nextLine();
					// check password matching
					doctor = Doctor.getDoctor(id);
					if (doctor == null) {
						System.out.println("id not found");
						signIn();
					}
					if (doctor.getPassword().equals(password)) {
						signedIn = true;

					}
				} else {
					System.out.println("id is not valid");
					signIn();
				}

			} else if (signInOption == 2) {
				System.out.println("Enter Email:");
				String email = in2.nextLine();
				System.out.println("Enter Password: ");
				String password = in2.nextLine();
				doctor = Doctor.getDoctor(email);
				// if null back to sign in menu
				if (doctor == null) {
					System.out.println("person not found (object = null)");
					signIn();
				}
				if (doctor.getPassword().equals(password))
					signedIn = true;
			} else {
				System.out.println("invalid input");
				signIn();
			}
			if (signedIn) {
				System.out.println("signed in successfully! hello," + doctor.getName());
				// do some code
				startDoctorMainMenu(doctor);

			} else {
				System.out.println("didnt sign in may be wrong id or password");
				startSignMenu();
			}
			break;
		case 3:
			// some code

			TA ta = null;
			System.out.println("1:sign in using id\t 2:sign in using email");
			signInOption = in2.nextInt();
			in2.nextLine();

			if (signInOption == 1) {
				System.out.println("Enter Id");
				int id = in2.nextInt();
				in2.nextLine();
				// checking if the id is valid first
				if (TA.isVlidId(id)) {
					System.out.println("Enter password:");
					String password = in2.nextLine();
					// check password matching
					ta = TA.getTA(id);
					if (ta == null) {
						System.out.println("id not found");
						signIn();
					}
					if (ta.getPassword().equals(password)) {
						signedIn = true;

					}
				} else {
					System.out.println("id is not valid");
					signIn();
				}

			} else if (signInOption == 2) {
				System.out.println("Enter Email:");
				String email = in2.nextLine();
				System.out.println("Enter Password: ");
				String password = in2.nextLine();
				ta = TA.getTA(email);
				// if null back to sign in menu
				if (ta == null) {
					System.out.println("person not found (object = null)");
					signIn();
				}
				if (ta.getPassword().equals(password))
					signedIn = true;
			} else {
				System.out.println("invalid input");
				signIn();
			}
			if (signedIn) {
				System.out.println("signed in successfully! hello," + ta.getName());
				// do some code
				System.out.println("Back to sign Menu");
				startSignMenu();
			} else {
				System.out.println("didnt sign in may be wrong id or password");
				startSignMenu();
			}
			break;
		case 0:
			startSignMenu();
		default:
			System.out.println("invalid input back to sign in menu");
			signIn();
			break;
		}

	}

	public static void startSignMenu() {
		Scanner in = new Scanner(System.in);
		System.out.println("1:Sign in\t 2:Sign up");
		int option = in.nextInt();
		switch (option) {
		case 1:
			signIn();
			break;
		case 2:
			signUp();
			break;
		default:
			System.out.println("invalid input ");
			startSignMenu();
		}
	}

	public static void main(String[] args) {
		startSignMenu();
//		Doctor d= new Doctor("yousef"); 
//		Course course = new Course("maths",1);
//		Assignment ass = new Assignment(1,"sheet1","qustiion",10);
//		System.out.println(course.getDoctorId());
//		Student s = new Student("yousef");
//		s.registerCourse();

	}

	// methods
	// start main menu of specific doctor
	public static void startDoctorMainMenu(Doctor doctor) {
		System.out.println("1:List Courses\t 2:view course\t 3:Create Course\t 4:logout");
		Scanner in = new Scanner(System.in);
		int option2;
		int option = in.nextInt();

		in.nextLine();
		switch (option) {
		case 1:
			doctor.displayListCourses();
			System.out.println("1:Back\t 2:logout");
			option2 = in.nextInt();
			in.nextLine();
			if (option2 == 1)
				startDoctorMainMenu(doctor);
			else
				startSignMenu();
			break;
		case 2:
			if(doctor.hasCourse()) {
			doctor.displayListCourses();
			
			System.out.println("enter course id");
			int cId = in.nextInt();
			in.nextLine();
			if (Course.isValidCourseId(cId)) {
				Course course = Course.getCourse(cId);
				doctor.viewCourseMenu(course);
			} else {
				System.out.println("invalid id back to main menu");
				startDoctorMainMenu(doctor);
			}
			}
			else {
				System.out.println("you dont have courses \nMain Menu");
				startDoctorMainMenu(doctor);
			}
			break;
		case 3:
			System.out.println("Enter course Name:");
			String courseName = in.nextLine();
			doctor.createCourse(courseName);
			System.out.println("1:back 2:logout");
			option2 = in.nextInt();
			in.nextLine();
			if (option2 == 1)
				startDoctorMainMenu(doctor);
			else
				startSignMenu();
			break;
		case 4:
			startSignMenu();
			break;
		default:
			System.out.println("invalid input");
			startDoctorMainMenu(doctor);
			break;
		}
	}

}
