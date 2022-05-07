public class AdminApp{
	
	AdminGUI displayTable;			// The GUI to communicate with user

	
	public static void main(String[] args) { //runs the program
		AdminApp ADAPP = new AdminApp(); //creates the functional App for the Administrator user

	}
	
	public AdminApp(){
		displayTable = new AdminGUI(this); //creates GUI for the administrator using the AdminGUI class
		displayTable.setVisible(true);
		displayTable.displayAdminTableData(ProjectDatabase.getAllMotors()); //displays all the motors and their status by retrieving data from database
	}

	
}
