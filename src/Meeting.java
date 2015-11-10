import java.util.ArrayList;
import java.util.Date;

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
	
	
	//Returns true or fales for if the meeting times overlap. Can be cleaned up if feeling adventerous. 
	public boolean overlaps(Meeting m){
		
		//meeting is before
		if (m.getEndDate().before(startDate) && m.getStartDate().before(startDate))
			return false;
		//Meeting is after
		if (m.getEndDate().after(startDate) && m.getStartDate().after(startDate))
			return false;
		
		return true;
	}
	
}
