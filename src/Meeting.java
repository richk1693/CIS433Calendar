import java.util.ArrayList;
import java.util.Date;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Meeting {
	
	private String title;
	private ArrayList<Person> attendeeList;
	private Date startDate;
	private Date endDate;

	//
	public Meeting(String title, ArrayList<Person> attendeeListList, Date sDate, Date eDate){
		this.title = title;
		this.attendeeList = attendeeListList;
		startDate = sDate;
		endDate = eDate;
	}
	
	public Meeting(ArrayList<Person> attendeeListList, Date sDate, Date eDate){
		title = "meeting";
		this.attendeeList = attendeeListList;
		startDate = sDate;
		endDate = eDate;
	}
	
	//"send" an email to each person in the meeting.
	public void NotifyAll(String msg){
		Person recipient;
		for(int i = 0; i< attendeeList.size(); i++){
			recipient = attendeeList.get(i);
			recipient.sendEmail(msg);
		}
	}
	
	
	//getters
	public Date getStartDate(){
		return startDate;
	}
	public Date getEndDate(){
		return endDate;
	}
	public String getTitle(){
		return title;
	}
	public ArrayList<Person> getPeople(){
		return attendeeList;
	}
	public ObservableList<String> getPeopleObservable(){
		ObservableList<String> list = FXCollections.observableArrayList();
		for(int i = 0; i < attendeeList.size(); i++){
			list.add(attendeeList.get(i).toString());
		}
		
		
		return list;
		
	}
	
	
	
	//setters
	public void setTitle(String title){
		this.title = title;
	}
	
	public void setPeople(ArrayList<Person> people){
		this.attendeeList = people;
	}
	
	public void setStartDate(Date d){
		this.startDate = d;
	}
	
	public void setEndDate(Date d){
		this.endDate = d;
	}
	
	//Returns true or false for if the meeting times overlap. Can be cleaned up if feeling brave. 
	public boolean overlaps(Meeting m){
		
		//meeting is before
		if (m.getEndDate().before(startDate) && m.getStartDate().before(startDate))
			return false;
		//Meeting is after
		if (m.getEndDate().after(startDate) && m.getStartDate().after(startDate))
			return false;
		
		return true;
	}
	
	public String toString(){
		
		return title;
		
	}
	
}
