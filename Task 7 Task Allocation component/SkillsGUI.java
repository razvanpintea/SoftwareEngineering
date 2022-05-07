import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import java.awt.Font;
import java.util.ArrayList;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SkillsGUI extends JFrame { //the GUI for viewing and editing the skills of a certain technician

	private JPanel contentPane;
	private JTable skillsTable;
	public JLabel technicianName;
	private SkillsApp skillsApp;
	public ChooseAllSkillsApp chooseSkills;
	private String selectedSkill;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SkillsGUI frame = new SkillsGUI(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public SkillsGUI(SkillsApp that) { //linked to the functional SkillsApp class
		skillsApp=that;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 347, 271);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.NORTH);
		
		technicianName = new JLabel(" ");
		panel.add(technicianName);
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.SOUTH);
		
		JButton addSkillBtn = new JButton("Add Skill");
		addSkillBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 chooseSkills= new ChooseAllSkillsApp(); //when AddSkill button is pressed, the ChooseAllSkillsApp class opens a GUI to allow the Human Resources user to add a new skill to the technician selected
				 ChooseAllSkillsApp.displayTable.technicianName.setText(AllTechniciansGUI.technicianCalledFirstName+" "+AllTechniciansGUI.technicianCalledLastName);
				 dispose();
			}
		});
		panel_1.add(addSkillBtn);
		
		JButton deleteSkillBtn = new JButton("Delete Skill");//deletes the selected skill
		deleteSkillBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedRow = skillsTable.getSelectedRow();
        		if(selectedRow>=0)
        	     selectedSkill= (String) skillsTable.getValueAt(selectedRow,0);
				SkillsApp.deleteSkill(AllTechniciansGUI.technicianCalledFirstName, AllTechniciansGUI.technicianCalledLastName, selectedSkill);
			}
		});
		panel_1.add(deleteSkillBtn);
		
		JButton btnNewButton_1 = new JButton("Close");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		panel_1.add(btnNewButton_1);
		
		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);
		
		skillsTable = new JTable();
		skillsTable.setFont(new Font("Tahoma", Font.PLAIN, 12));
		skillsTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Skills"
			}
		));
		skillsTable.getColumnModel().getColumn(0).setPreferredWidth(124);
		scrollPane.setViewportView(skillsTable);
	}
	



void displaySkillsTableData(ArrayList<Skill> tableData){//method to retrieve and display available skills
	
DefaultTableModel tableModel = (DefaultTableModel) 
skillsTable.getModel();
	tableModel.setRowCount(0);
	
	for(Skill s: tableData) {
		tableModel.addRow(new Object[] {s.getName(),});
	}
}


}
