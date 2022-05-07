import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.Font;
import java.awt.Color;

public class ViewAssignedTasksOfMotorGUI extends JFrame {//GUI for Human Resources users to display all the tasks of a selected motor that have been assigned to a technician

	private JPanel contentPane;
	private JTable selectedTasks;
	public static String serialNo;
	public static String customerName;
	private ViewAssignedTasksOfMotorApp assignedTasksOfMotorApp;
	private String selectedTask;
	private int duration;
	public JLabel motorSerialAndCustomer;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewAssignedTasksOfMotorGUI frame = new ViewAssignedTasksOfMotorGUI(null);
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
	public ViewAssignedTasksOfMotorGUI(ViewAssignedTasksOfMotorApp that) {
		assignedTasksOfMotorApp=that;//linked to the functional ViewAssignedTasksOfMotorApp class 
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 636, 574);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.DARK_GRAY);
		contentPane.add(panel, BorderLayout.NORTH);
		
		motorSerialAndCustomer = new JLabel("New label");
		motorSerialAndCustomer.setForeground(Color.WHITE);
		motorSerialAndCustomer.setFont(new Font("Tahoma", Font.PLAIN, 15));
		serialNo=(String)HumanResourcesGUI.humanResourcesTable.getValueAt(HumanResourcesGUI.humanResourcesTable.getSelectedRow(),0);
		customerName=(String)HumanResourcesGUI.humanResourcesTable.getValueAt(HumanResourcesGUI.humanResourcesTable.getSelectedRow(),1);
		motorSerialAndCustomer.setText("Tasks of Motor: " + serialNo +" - "+customerName+" that have been assigned to a technician");
		panel.add(motorSerialAndCustomer);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.DARK_GRAY);
		contentPane.add(panel_1, BorderLayout.SOUTH);
		
		JButton btnNewButton_2 = new JButton("Go back");
		btnNewButton_2.setFont(new Font("Tahoma", Font.PLAIN, 15)); 
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				HumanResourcesApp HRapp= new HumanResourcesApp(); //button to go back to the main Human Resources window
			}
		});
		panel_1.add(btnNewButton_2);
		
		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);
		
		selectedTasks = new JTable();
		selectedTasks.setFont(new Font("Tahoma", Font.PLAIN, 16));
		selectedTasks.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Task Name", "First Name of Assigned Technician ", "Last Name of Assigned Technician" //details that are displayed in the GUI
			}
		));
		selectedTasks.getColumnModel().getColumn(0).setPreferredWidth(137);
		selectedTasks.getColumnModel().getColumn(1).setPreferredWidth(196);
		selectedTasks.getColumnModel().getColumn(2).setPreferredWidth(213);
		scrollPane.setViewportView(selectedTasks);
	}
	
void displayAssignedTasksTableData(ArrayList<Task> tableData){
		
		// Empty the existing data
	DefaultTableModel tableModel = (DefaultTableModel) 
			selectedTasks.getModel();
		tableModel.setRowCount(0);
		
		// Some safety code missing here
		for(Task t: tableData) {
			tableModel.addRow(new Object[] {t.getTaskName(), t.getTechFirst(), t.getTechLast()});
		}
	}

}
