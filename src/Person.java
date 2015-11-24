
public class Person {
	private String name;
	private String email;
	private String role;
	
	public Person(String name, String email, String role){
		this.name = name;
		this.email = email;
		this.role = role;
	}
	
	//Fake send email method for "contacting" people. 
	public void sendEmail(String message){
		System.out.println("Email sent to " + name);
	}
	
	public String toString(){
		return name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

}
