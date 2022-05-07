import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Technician { //class for the technicians and their details. It contains getters and setters for all of its variables

	private String available;
	public static ArrayList<String> skills;
	public static  ArrayList<String> assignedTasks;
	public static ArrayList<Task> completedTasks ;
	private String firstName;
	private String lastName;
	public static ArrayList<Technician> allTechnicians;
	private static ArrayList<String> assignedMotors;

	
	public Technician(){
		available = "";
		assignedTasks =new ArrayList<String>();
		assignedMotors=new ArrayList<String>();
		firstName="";
		lastName="";
		skills=null;
		completedTasks=null;
	}

	

	public void setAvailable(String availability) {
		available = availability;
	}


	public String getFirstName() {
		return firstName; 
	}
	
	public String getAssignedMotor(int i) {
		return assignedMotors.get(i); 
	}
	
	
	public String getAssignedTask(int i) {
		return assignedTasks.get(i); 
	}
	
	
	public String getLastName() {
		return lastName; 
	}

	public String getAvailable() {
		return available;
	}
	
	public ArrayList<String> getSkills() {
		return skills;
	}

	public void setFirstName(String string) {
		firstName = string;
		
	}
	
	public void setLastName(String string) {
		lastName = string;
		
	}
	
	public static void setAssignedTask(String task) {
		assignedTasks.add(task);
		
	}
	
	public static void setAssignedMotor(String motor) {
		assignedMotors.add(motor) ;
		
	}
	public void addSkill(String skill) {
		skills.add(skill);
	}
	
}
