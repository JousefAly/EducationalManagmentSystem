import java.util.ArrayList;

public class TA extends Person {
	private static ArrayList<TA> tAsList = new ArrayList<TA>(); 
	private static int nextPosition = 0;
	private int id = nextPosition + 1; 
	int TACourseId ;
	public TA( String name) {
		super(name);
		tAsList.add(this);
		nextPosition++;
	}
	public int getId()
	{
		return id;
	}
	
	public static TA getTA(int id)
	{
		int index = id-1;
		return tAsList.get(index);
	}
	
	public static boolean isVlidId(int id) {
		if(id <= tAsList.size())
			return true;
		else 
			return false;
	}
	// return  object or null if email not found
	public static TA getTA(String email) {
		TA tA = null;
		for(TA t : tAsList) {
			if (t.getEmail().equals(email))
				tA = t;
			
			
		}
		return tA;
		
			
	}
}
