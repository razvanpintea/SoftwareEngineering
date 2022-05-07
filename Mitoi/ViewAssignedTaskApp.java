

public class ViewAssignedTaskApp{
	
	private ViewAssignedTaskGUI displayTable;	//this class is linked to the ViewAssignedTaskGUI class
	

	// This is just to run the program
	public static void main(String[] args) {
		ViewAssignedTaskApp assignedTaskApp = new ViewAssignedTaskApp(); 

	}
	
	
	
	public ViewAssignedTaskApp(){
		//the GUI is created using the ViewAssignedTaskGUI class
		displayTable = new ViewAssignedTaskGUI(this);
		displayTable.setVisible(true);
		
	displayTable.displayAssignedTasksTableData(ProjectDatabase.getAssignedTasks(TechnicianViewRepairableMotorsGUI.selectedTechnician.getFirstName(), TechnicianViewRepairableMotorsGUI.selectedTechnician.getLastName() ));
	} //this method fills the table with information from the database
	
	static void completeTask(String task, String serial, String customerName, String techFirstName,String techLastName) {
		ProjectDatabase.completeTask(task, serial, customerName, techFirstName, techLastName);
		//this method runs when the Complete Task in ran in the ViewAssignedTaskGUI class
	}
	

	
}