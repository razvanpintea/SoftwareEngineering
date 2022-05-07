import java.util.ArrayList;

public class Task {//class for the tasks and their details. It contains getters and setters for all of its variables

	
	private int techniciansNeeded;
	private String name;
	private String skill;
	public static ArrayList<Task> allTasks= new ArrayList<Task>();
	private String serialOfMotorAssigned;
	private String technicianAssignedFirstName;
	private String technicianAssignedLastName;
	private String customerOfMotorAssigned;
	private int duration;
	private String urgency;
	private String date;
	private String overdue;
	
	public Task(){
		techniciansNeeded = 0;
		name ="";
		skill="";
		urgency="";
	}
	
	
	public void setTaskName(String taskName) {
		name = taskName;
	}
	
	public void setSkillRequired(String required) {
		skill = required;
	}
	
	public void setTechniciansNeeded(int number) {
		techniciansNeeded = number;
	}
	
	public void setSerial(String serialNo)
	{
		serialOfMotorAssigned =serialNo;
	}
	
	public void setTechnicianFirst(String first)
	{
		technicianAssignedFirstName =first;
	}
	public void setTechnicianLast(String last)
	{
		technicianAssignedLastName =last;
	}
	
	public void setCustomer(String customer)
	{
		customerOfMotorAssigned =customer;
	}
	
	public void setDuration(int dur)
	{
		duration =dur;
	}

	public void setUrgency(String urg)
	{
	urgency=urg;
	}
	
	public void setDate(String data)
	{
	date=data;
	}
	
	public void setOverdue(String due)
	{
	overdue=due;
	}
	
	public int getDuration() {
		return duration; 
	}
	public String getTaskName() {
		return name; 
	}
	
	public String getSerial() {
	return serialOfMotorAssigned;
	}
	
	public String getSkillRequired() {
		return skill; 
	}
	
	public int getTechniciansNeeded() {
		return techniciansNeeded; 
	}
	
	public String getCustomer() {
		return customerOfMotorAssigned; 
	}

	public String getTechFirst() {
		return technicianAssignedFirstName; 
	}
	
	public String getTechLast() {
		return technicianAssignedLastName; 
	}
	
	public String getUrgency() {
		return urgency; 
	}
	public String getDate() {
		return date; 
	}
	
	public String getOverdue() {
		return overdue; 
	}
}

