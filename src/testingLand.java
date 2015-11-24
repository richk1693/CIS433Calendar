import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class testingLand {

	
	
	//
	//
	//
	//
	//PLEASE MAKE A TEST COMMIT!! THROW SOME RANDOM COMMENT HERE!!
	//
	//RICHARD: Food tastes great!! new change
	//JOE: I hate lin.
	//MATT:
	//
	//
	//
	
	//This is just where i'm throwing random code to test various functions. 
	public static void main(String[] args) throws ParseException {
		
		Date d = new Date();
		SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy HH:MM");
		Date da = format.parse("24-11-2015 17:11");
		System.out.println((da));

	}
}
