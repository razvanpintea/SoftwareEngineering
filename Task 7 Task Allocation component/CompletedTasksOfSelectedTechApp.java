
public class CompletedTasksOfSelectedTechApp { //functional app for the CompletedTasksOfSelectedTechGUI
private CompletedTasksOfSelectedTechGUI displayTable;
private String technicianFirst;
private String technicianLast;
public static void main(String[] args) {
	CompletedTasksOfSelectedTechApp completedJobsApp = new CompletedTasksOfSelectedTechApp(); //runs the class
}


public CompletedTasksOfSelectedTechApp(){
	displayTable = new CompletedTasksOfSelectedTechGUI(); //creates new CompletedTasksOfSelectedTechGUI
	displayTable.setVisible(true);//makes GUI visible
	technicianFirst=AllTechniciansGUI.technicianCalledFirstName;
	technicianLast=AllTechniciansGUI.technicianCalledLastName;
	displayTable.displayAllCompletedTasks(ProjectDatabase.getAllCompletedTasksOfTech(technicianFirst, technicianLast)); //method to retrieve data from database 
}

}
