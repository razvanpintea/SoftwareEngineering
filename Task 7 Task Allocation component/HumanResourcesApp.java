
public class HumanResourcesApp{
	
	private HumanResourcesGUI displayTable;		//this functional class is linked to the HumanResourcesGUI class which is the main window for the Human Resources user
	

	
	public static void main(String[] args) {
		HumanResourcesApp HRAPP = new HumanResourcesApp();

	}
	
	public HumanResourcesApp(){
		displayTable = new HumanResourcesGUI(this); //creates the main GUI for the human resources user
		displayTable.setVisible(true);//makes GUI visible
		displayTable.displayAdminTableData(ProjectDatabase.getAllMotors()); //retrieves and displays all the existent motors 
	}
	
	void updateMotorStatus(String serialNumber, String customerName,String text) { //method used in order to update the status of a motor
		ProjectDatabase.updateMotorStatus(serialNumber, customerName, text); 
		displayTable.displayAdminTableData(ProjectDatabase.getAllMotors());
}
	public void setToInvisible() {
		displayTable.setVisible(false);
}
	
	void deleteMotor(String serialNumber, String customerName) { //method used in order to delete a motor
		ProjectDatabase.deleteMotor(serialNumber, customerName);
		displayTable.displayAdminTableData(ProjectDatabase.getAllMotors());
	}
	
	public void addMotor(String serial, String customerName, String date, int price) {  //method used in order to add a motor
		ProjectDatabase.addMotor(serial, customerName, date, price);
		displayTable.displayAdminTableData(ProjectDatabase.getAllMotors());
		
	}
	
	public void viewAllTasksOfMotor(String serial, String customerName) { //method used in order to view all tasks of a motor
		ProjectDatabase.getSelectedTasks(serial, customerName);
		displayTable.displayAdminTableData(ProjectDatabase.getAllMotors());
		
	} 

	
}