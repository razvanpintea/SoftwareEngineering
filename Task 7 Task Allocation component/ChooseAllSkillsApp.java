
public class ChooseAllSkillsApp{
	
	public static ChooseAllSkillsGUI displayTable;			// linked to the ChooseAllSkillsGUI class

	public static void main(String[] args) {
		ChooseAllSkillsApp chooseSkills = new ChooseAllSkillsApp(); //runs the program

	}
	

	public ChooseAllSkillsApp(){
		displayTable = new ChooseAllSkillsGUI(this); //creates the visible GUI
		displayTable.setVisible(true); //makes the GUI visible
		displayTable.displayAllSkillsTableData(ProjectDatabase.getAllSkills()); //loads GUI with data from the database
	}
	
	
	public void addSkill(String technicianFirstName,String technicianLastName, String skill ) { //when this method is ran, it adds the selected skill to the selected technician
		ProjectDatabase.addSkill(technicianFirstName, technicianLastName, skill);
		displayTable.displayAllSkillsTableData(ProjectDatabase.getAllSkills());
	}
	
}