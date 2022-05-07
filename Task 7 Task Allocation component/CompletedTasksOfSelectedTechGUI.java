
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.table.DefaultTableModel;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class CompletedTasksOfSelectedTechGUI extends JFrame { //GUI to view all the completed tasks of a specific Technician

	private JPanel contentPane;
	private JTable completedTasksOfTechTable;
	private JLabel techNameLabel;
	private JButton btnNewButton;
	public static String technicianCalledFirstName;
    public static String technicianCalledLastName;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CompletedTasksOfSelectedTechGUI frame = new CompletedTasksOfSelectedTechGUI(); //creates the GUI
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
	public CompletedTasksOfSelectedTechGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 433, 367);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.DARK_GRAY);
		contentPane.add(panel, BorderLayout.NORTH);
		technicianCalledFirstName=AllTechniciansGUI.technicianCalledFirstName;
		technicianCalledLastName=AllTechniciansGUI.technicianCalledLastName;
		techNameLabel= new JLabel(technicianCalledFirstName+" "+technicianCalledLastName); //title of the GUI is the technician's first name and last name
		techNameLabel.setForeground(Color.WHITE);
		techNameLabel.setFont(new Font("Tahoma", Font.BOLD, 17));
		panel.add(techNameLabel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.DARK_GRAY);
		contentPane.add(panel_1, BorderLayout.SOUTH);
		
		btnNewButton = new JButton("Go back"); 
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			dispose();
			AllTechniciansApp allTechApp =new AllTechniciansApp(); //if the back button is pressed, the Human Resources user gets displayed the window with all the technicians which is used to open this class
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel_1.add(btnNewButton);
		
		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);
		
		completedTasksOfTechTable = new JTable();
		completedTasksOfTechTable.setFont(new Font("Tahoma", Font.PLAIN, 14));
		completedTasksOfTechTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Task", "Motor"
			}
		));
		scrollPane.setViewportView(completedTasksOfTechTable);
	}
void displayAllCompletedTasks(ArrayList<Task> tableData){
		
		DefaultTableModel tableModel = (DefaultTableModel) 
				completedTasksOfTechTable.getModel();
		tableModel.setRowCount(0);
		
		for(Task t: tableData) {
			tableModel.addRow(new Object[] {t.getTaskName(), t.getSerial()}); //method ran in CompletedTasksOfSelectedTechApp, used to display all the tasks completed by the technician and the serial number of the motor that had the task
		}
	}
}
