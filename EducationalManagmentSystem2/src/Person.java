
public class Person {

	// attributes

	private String name;
	
	private String email;
	private String phoneNumber;
	private String password;

	// constructor
	public Person(String n) {
		name = n;
		
	}

	// methods
	

	public String getName() {
		return name;
	}

	public void setEmail(String e) {
		email = e;
	}
	public String getEmail() {
		return email;
	}

	public void setPhoneNumber(String p) {
		phoneNumber = p;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String p) {
		password = p;
	}
}
