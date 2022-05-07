import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Color;

public class ChooseAllSkillsGUI extends JFrame { //GUI for all the skills that can be added to technicians

	private JPanel contentPane;
	private static JTable allSkillsTable;
	private ChooseAllSkillsApp chooseSkillsApp;
	public JLabel technicianName;
	public static String selectedSkill;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ChooseAllSkillsGUI frame = new ChooseAllSkillsGUI(null);
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
	public ChooseAllSkillsGUI(ChooseAllSkillsApp that) {  //linked to the ChooseAllSkillsApp class
		
		chooseSkillsApp=that;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 567, 312);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.DARK_GRAY);
		contentPane.add(panel, BorderLayout.NORTH);
		
		technicianName = new JLabel("(selected technician name)");
		technicianName.setForeground(Color.WHITE);
		technicianName.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panel.add(technicianName);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.DARK_GRAY);
		contentPane.add(panel_1, BorderLayout.SOUTH);
		
		JButton btnNewButton = new JButton("Add");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int selectedRow = allSkillsTable.getSelectedRow();
        		if(selectedRow>=0)
        	    selectedSkill= (String) allSkillsTable.getValueAt(selectedRow,0);
        		that.addSkill(AllTechniciansGUI.technicianCalledFirstName, AllTechniciansGUI.technicianCalledLastName, selectedSkill);//when this button is pressed, the selected skill is added to the technician by running the method in the ChooseAllSkillsApp class 		
        		
			}
		});
		panel_1.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Close");
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			dispose();
			}
		});
		panel_1.add(btnNewButton_1);
		
		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);
		
		allSkillsTable = new JTable();
		allSkillsTable.setFont(new Font("Tahoma", Font.PLAIN, 16));
		allSkillsTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Choose From preset Skills"
			}
		));
		allSkillsTable.getColumnModel().getColumn(0).setPreferredWidth(126);
		scrollPane.setViewportView(allSkillsTable);
	}
	
	void displayAllSkillsTableData(ArrayList<Skill> tableData){
		
		// Empty the existing data
	DefaultTableModel tableModel = (DefaultTableModel) 
			allSkillsTable.getModel();
		tableModel.setRowCount(0);
		
		// Some safety code missing here
		for(Skill s: tableData) {
			tableModel.addRow(new Object[] {s.getName(),}); //the table is filled with skills from the database for the HR to add to a certain technician
		}
	}

public static String getSkillName() {
	int selectedRow = allSkillsTable.getSelectedRow();
	if(selectedRow>=0) {
    selectedSkill= (String) allSkillsTable.getValueAt(selectedRow,0);
	return selectedSkill;
	}
	else
	return "";
}
	
}
