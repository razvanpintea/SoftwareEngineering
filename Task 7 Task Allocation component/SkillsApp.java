
public class SkillsApp{ //functional class for the Skills GUI	
	public static SkillsGUI displayTable;	
	
	public static void main(String[] args) {
		SkillsApp skillApp = new SkillsApp();

	}
	
	
	public SkillsApp(){
		displayTable = new SkillsGUI(this); //creates Skills GUI
		displayTable.setVisible(true); //makes GUI visible
		displayTable.displaySkillsTableData(ProjectDatabase.getSkills(AllTechniciansGUI.technicianCalledFirstName, AllTechniciansGUI.technicianCalledLastName)); //displays the skills of a certain technician
	}
	
	
	public void setToInvisible() {
		displayTable.dispose();
}
	
	static void deleteSkill(String technicianFirstName,String technicianLastName, String skill) { //method to delete a certain skill from a technician
		ProjectDatabase.deleteTechnicianSkill(AllTechniciansGUI.technicianCalledFirstName,AllTechniciansGUI.technicianCalledLastName, skill);
		displayTable.displaySkillsTableData(ProjectDatabase.getSkills(AllTechniciansGUI.technicianCalledFirstName,AllTechniciansGUI.technicianCalledLastName));
	}
	
	public void addSkill(String technicianFirstName,String technicianLastName, String skill) {//method to add a new skill to a technician
		ProjectDatabase.addSkill(AllTechniciansGUI.technicianCalledFirstName, AllTechniciansGUI.technicianCalledLastName, skill);
		displayTable.displaySkillsTableData(ProjectDatabase.getSkills(AllTechniciansGUI.technicianCalledFirstName, AllTechniciansGUI.technicianCalledLastName));
		
	}

	
}