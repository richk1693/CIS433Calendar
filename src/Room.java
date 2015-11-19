import java.util.ArrayList;


public class Room {
	String name;
	ArrayList<Meeting> meetings = new ArrayList<Meeting>();
	int capacity;
	
	public Room(String name, ArrayList<Meeting> meetings, int capacity){
		this.name = name;
		this.meetings = meetings;
		this.capacity = capacity;
	}
	
	public String toString(){
		return name;
	}

}
