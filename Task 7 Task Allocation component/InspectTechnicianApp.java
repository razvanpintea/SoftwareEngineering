
public class InspectTechnicianApp{ //this class is the functional app for the InspectTechnicianGUI class which is the main window for the inspect technician user
	
	public InspectTechnicianGUI displayTable;			
	
	public static void main(String[] args) {
		InspectTechnicianApp inspector= new InspectTechnicianApp(); //runs the program

	}
	
	public InspectTechnicianApp(){
		displayTable = new InspectTechnicianGUI(this);//creates the main window for the inspect technician user
		displayTable.setVisible(true); //makes it visible
		displayTable.displayInspectorTableData(ProjectDatabase.GetInspectorMotors());//retrieves all motor that have the status "sent to inspection"
		
	}
	
	void updateMotorStatus(String serialNumber, String customerName,String text) { //method to update the selected motor's status
		ProjectDatabase.updateMotorStatus(serialNumber, customerName, text);
		displayTable.displayInspectorTableData(ProjectDatabase.GetInspectorMotors());
}
	
	
	public void addTaskToMotor(String serial,String customer, String task, String skill, int techniciansNeeded, String urgency, int duration) { //method to add a task to a motor
		ProjectDatabase.addTaskToMotor( serial, customer,  task,  skill,  techniciansNeeded, urgency, duration );
	}
	
}