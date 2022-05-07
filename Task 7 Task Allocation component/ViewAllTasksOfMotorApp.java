
public class ViewAllTasksOfMotorApp{//functional class for the ViewAllTasksOfMotorGUI class
	
	public static ViewAllTasksOfMotorGUI displayTable;			
	
	public static void main(String[] args) {
		ViewAllTasksOfMotorApp taskApp = new ViewAllTasksOfMotorApp();

	}
	
	public ViewAllTasksOfMotorApp(){
		
		displayTable = new ViewAllTasksOfMotorGUI(this);//creates GUI
		displayTable.setVisible(true);//makes it visible
		displayTable.displaySelectedTasksTableData(ProjectDatabase.getSelectedTasks(ViewAllTasksOfMotorGUI.serialNo, ViewAllTasksOfMotorGUI.customerName));//displays tasks needed to repair a certain motor(for the Human Resources user)
	}
	
}