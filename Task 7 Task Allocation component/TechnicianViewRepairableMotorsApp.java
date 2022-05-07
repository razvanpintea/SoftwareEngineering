public class TechnicianViewRepairableMotorsApp{ //functional class for the TechnicianViewRepairableMotorsGUI class
	
	public static TechnicianViewRepairableMotorsGUI displayTable;
	
	public static void main(String[] args) {
		TechnicianViewRepairableMotorsApp AllTApp = new TechnicianViewRepairableMotorsApp();

	}
	
	
	public TechnicianViewRepairableMotorsApp(){
		
		displayTable = new TechnicianViewRepairableMotorsGUI(this);
		displayTable.setVisible(true);
	
		displayTable.displayRepairableMotorsTableData(ProjectDatabase.getRepairableMotors()); //displays all of the motors that are sent to repair in the main windows of the technicians
	}
	
	
	public static void updateMotorStatus(String serialNumber, String customerName,String text) { //method to update a motor status after it gets repaired 
		ProjectDatabase.updateMotorStatus(serialNumber, customerName, text);
		displayTable.displayRepairableMotorsTableData(ProjectDatabase.getRepairableMotors());
}
}
