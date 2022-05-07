public class AllTechniciansApp{ //functional app for the AllTechniciansGUI class
	
	AllTechniciansGUI displayTable;	

	// This is just to run the program
	public static void main(String[] args) {
		AllTechniciansApp AllTApp = new AllTechniciansApp(); //creates functional AllTechniciansApp for the AllTechniciansGUI

	}
	

	public AllTechniciansApp(){
		displayTable = new AllTechniciansGUI(this);
		displayTable.setVisible(true);
		displayTable.displayTechnicianTableData(ProjectDatabase.getAllTechnicians());//tells GUI to retrieve all technicians from the database
	}
	
	public void setToInvisible() {
		displayTable.setVisible(false);
}
		
	
	public void deleteTechnician(String technicianFirstName, String technicianLastName) { //method to delete technician when delete button is pressed on the AllTechniciansGUI
		ProjectDatabase.deleteTechnician(technicianFirstName,technicianLastName); //runs method on the database
		displayTable.displayTechnicianTableData(ProjectDatabase.getAllTechnicians()); //refills GUI with updated information
	}
	
	public void deleteTechnicianFromSkillsTable(String technicianFirstName, String technicianLastName) { //when delete button is pressed on AllTechniciansGUI, the technician is also deleted from the database table that links a skill to him/her
		ProjectDatabase.deleteTechnicianFromSkillsTable(technicianFirstName, technicianLastName);
		displayTable.displayTechnicianTableData(ProjectDatabase.getAllTechnicians()); //refills GUI with updated information
	}
	
	public void addTechnician(String technicianFirstName, String technicianLastName) {  //method to add technician when add button is pressed on the AllTechniciansGUI
		ProjectDatabase.addTechnician(technicianFirstName,technicianLastName);//runs method on the database
		displayTable.displayTechnicianTableData(ProjectDatabase.getAllTechnicians()); //refills GUI with updated information
		
	}

	
}
