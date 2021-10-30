
public class Person {

	// attributes

	private String name;
	private int id;
	private String email;
	private String phoneNumber;
	private String password;

	// constructor
	public Person(int id, String n) {
		name = n;
		this.id = id;
	}

	// methods
	/**
	 * @return id of the person
	 */
	public int getId() {
		return this.id;
	}

	public String getName() {
		return name;
	}

	public void setEmail(String e) {
		email = e;
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
