
public class Person {
	private String name;
	private String email;
	private String role;
	
	public Person(String name, String email, String role){
		this.name = name;
		this.email = email;
		this.role = role;
	}
	
	public void sendEmail(String message){
		System.out.println("Email sent to " + name);
	}

}
