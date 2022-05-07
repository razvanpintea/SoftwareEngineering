import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;

public class ViewAvailableTasksApp{//functional class for the ViewAvailableTasksGUI class
	
	ViewAvailableTasksGUI displayTable;			

	
	public static void main(String[] args) {
		ViewAvailableTasksApp availableTasksApp = new ViewAvailableTasksApp();

	}
	
	public ViewAvailableTasksApp(){
		displayTable = new ViewAvailableTasksGUI(this);
		displayTable.setVisible(true);
		displayTable.displayAvailableTasksTableData(ProjectDatabase.getAvailableTasks(TechnicianViewRepairableMotorsGUI.serialNo, TechnicianViewRepairableMotorsGUI.customerName,TechnicianViewRepairableMotorsGUI.selectedTechnician.skills,TechnicianViewRepairableMotorsGUI.selectedTechnician.getFirstName(),TechnicianViewRepairableMotorsGUI.selectedTechnician.getLastName()));
	}
	
	void assignTask(String task, String serial, String customerName, String techFirstName,String techLastName) { //method for technicians to assign themselves to a task
		ProjectDatabase.assignTaskToTechnician(task, serial, customerName, techFirstName, techLastName);
		
	}
	
	
	void updateTechniciansNeededMinus(String task, String serial, int technicians) { //method to subtract one from technicians needed to repair a task when a technician assigns themselves to it
		ProjectDatabase.updateTechniciansNeededMinus(task, serial, technicians);
		
	}
	
}
 