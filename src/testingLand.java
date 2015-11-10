import java.util.ArrayList;
import java.util.Date;

public class testingLand {

	public static void main(String[] args) {

		//Testing "email sending"
		ArrayList<Person> people = new ArrayList<Person>();
		people.add(new Person("bob", "bob@bob.com", "manager"));
		people.add(new Person("bob2", "bob@bob.com", "manager"));
		people.add(new Person("bob3", "bob@bob.com", "manager"));
		
		Date startDate = new Date();
		Date endDate = new Date();
		int oneDay = 24*60*60*1000;
		
		Date yesterday = new Date(System.currentTimeMillis()-oneDay);
		Date tomorrow = new Date(System.currentTimeMillis()+oneDay);
		
		Meeting m = new Meeting(people, startDate ,endDate);
		Meeting m2 = new Meeting(people, yesterday, yesterday);
		
		System.out.println(m.overlaps(m2));

	}
}
