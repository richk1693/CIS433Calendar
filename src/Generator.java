import java.util.ArrayList;
import java.util.Date;

public class Generator {

	public ArrayList<Room> makeRooms(int amount){
		ArrayList<Room> result = new ArrayList<Room>();
		
		for(int i = 0; i<amount; i++){
			result.add(new Room("Room 10"+i, makeMeetings(5), 30));
		}
		result.add(new Room("Empty Room", makeMeetings(0), 50));
		
		
		return result;
	}
	
	
	public ArrayList<Meeting> makeMeetings(int amount){
		ArrayList<Meeting> result = new ArrayList<Meeting>();
		Date today = new Date();
		int oneDay = 24*60*60*1000;
		
		result.add(new Meeting("Yesterday Meeting", makePeople(5), new Date(System.currentTimeMillis()-(oneDay)), new Date(System.currentTimeMillis()-(oneDay))));
		for(int i = 0; i<amount; i++){
			
			Date start = new Date(System.currentTimeMillis()+(oneDay*i));
			Date end = new Date(System.currentTimeMillis()+(oneDay*i)+oneDay);
			result.add(new Meeting("Meeting"+i, makePeople(5), start, end));
		}
		
		return result;
	}
	
	public ArrayList<Person> makePeople(int amount){
		ArrayList<Person> result = new ArrayList<Person>();
		
		for(int i = 0; i<amount; i++){
			result.add(new Person("Person"+i,"Person"+i+"@job.com","Worker"));
		}
		
		return result;
	}
}