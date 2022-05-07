
public class AllCompletedJobsApp { //functional app for the AllCompletedJobsGUI
private AllCompletedJobsGUI displayTable;

public static void main(String[] args) {
	AllCompletedJobsApp completedJobsApp = new AllCompletedJobsApp();
}


public AllCompletedJobsApp(){
	
	displayTable = new AllCompletedJobsGUI(this); //creates GUI for all the completed jobs
	displayTable.setVisible(true);//makes GUI visible
	displayTable.displayAllCompletedTasks(ProjectDatabase.getAllCompletedTasks());//retrives all the completed tasks, each technician that completed them and the motor that had the task
}

}
