import java.util.ArrayList;


public class Room {
	ArrayList<Meeting> meetings = new ArrayList<Meeting>();
	int capacity;
	
	public Room(ArrayList<Meeting> meetings, int capacity){
		this.meetings = meetings;
		this.capacity = capacity;
	}

}
