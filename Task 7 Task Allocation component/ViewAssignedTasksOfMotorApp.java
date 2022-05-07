
public class ViewAssignedTasksOfMotorApp{//functional class that is linked to the ViewAssignedTasksOfMotorGUI class
	
	public static ViewAssignedTasksOfMotorGUI displayTable;			
	
	public static void main(String[] args) {
		ViewAssignedTasksOfMotorApp assignedTasksApp = new ViewAssignedTasksOfMotorApp();

	}
	
	public ViewAssignedTasksOfMotorApp(){

		displayTable = new ViewAssignedTasksOfMotorGUI(this);
		displayTable.setVisible(true);
		displayTable.displayAssignedTasksTableData(ProjectDatabase.getAllAssignedTasks(ViewAssignedTasksOfMotorGUI.serialNo, ViewAssignedTasksOfMotorGUI.customerName));//displays all the tasks of a selected motor that have been assigned to a technician

	}
	
	
}