
public class SelectedTasksApp{ //this is the functional class for the SelectedTasksGUI class, which allow the inspect technician to view or delete the tasks needed to repair a motor
	
	public static SelectedTasksGUI displayTable;			
	public static void main(String[] args) {
		SelectedTasksApp taskApp = new SelectedTasksApp();

	}
	
	
	public SelectedTasksApp(){
		
		displayTable = new SelectedTasksGUI(this); //creates the SelectedTasksGUI
		displayTable.setVisible(true); //makes GUI visible
		
		displayTable.displaySelectedTasksTableData(ProjectDatabase.getSelectedTasks(SelectedTasksGUI.serialNo, SelectedTasksGUI.customerName)); //displays tasks of the selected motor
	}
	
	 
	
	static void deleteTask(String serialNo, String customerName, String task, int duration) { //method for deleting a task
		ProjectDatabase.deleteTaskFromMotor(serialNo, customerName, task, duration);
		displayTable.displaySelectedTasksTableData(ProjectDatabase.getSelectedTasks(SelectedTasksGUI.serialNo, SelectedTasksGUI.customerName));
	}
	
}