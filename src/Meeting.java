import java.util.ArrayList;
import java.util.Date;

public class Meeting {
	
	private ArrayList<Person> attendees;
	private Date startDate;
	private Date endDate;

	
	public Meeting(ArrayList<Person> attendeesList, Date sDate, Date eDate){

		attendees = attendeesList;
		startDate = sDate;
		endDate = eDate;
	}
	
	public void NotifyAll(String msg){
		Person recipient;
		for(int i = 0; i< attendees.size(); i++){
			recipient = attendees.get(i);
			recipient.sendEmail(msg);
		}
	}
	
	public Date getStartDate(){
		return startDate;
	}
	
	public Date getEndDate(){
		return endDate;
	}
	
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
