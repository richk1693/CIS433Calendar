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
	
	public ArrayList<Meeting> getMeetings(){
		return meetings;
	}
	
	public void setMeetings(ArrayList<Meeting> meetings){
		this.meetings = meetings;
	}
	
	public String toString(){
		return name;
	}

}
