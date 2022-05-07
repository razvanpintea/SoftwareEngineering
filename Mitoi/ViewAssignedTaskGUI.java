import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import java.awt.Font;
import javax.swing.table.DefaultTableModel;

public class ViewAssignedTaskGUI extends JFrame { //this class represents the GUI for technicians to view their assigned tasks 

	private JPanel contentPane;
	private JTable assignedTaskTable;
	private ViewAssignedTaskApp assignedTaskApp;
	private String selectedTask;
	private String motor;
	private String serialMotor;
	private String customerName;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) { //the GUI is created and is made visible
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewAssignedTaskGUI frame = new ViewAssignedTaskGUI(null);
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
	public ViewAssignedTaskGUI(ViewAssignedTaskApp that) { //this class is linked to the ViewAssignedTaskApp 
		
		assignedTaskApp=that;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 479, 487);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
	
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.NORTH);
		
		JLabel lblNewLabel = new JLabel("Your assigned tasks"); //title of the GUI
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		panel.add(lblNewLabel);
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.SOUTH);
		
		JButton btnNewButton = new JButton("Mark Task Completed"); //button to mark the completion of a task
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int selectedRow = assignedTaskTable.getSelectedRow();
        		if(selectedRow>=0)
        		{
        		selectedTask = (String) assignedTaskTable.getValueAt(selectedRow,0);
        		motor= (String) assignedTaskTable.getValueAt(selectedRow,1);
        		String[] split=motor.split(" - ");
        		serialMotor=split[0];
        		customerName=split[1];
        		//when the Mark task completed button is pressed, this method is ran on the database and marks the task completed
				ViewAssignedTaskApp.completeTask(selectedTask, serialMotor, customerName,TechnicianViewRepairableMotorsGUI.selectedTechnician.getFirstName(), TechnicianViewRepairableMotorsGUI.selectedTechnician.getLastName());
				int check=ProjectDatabase.checkIfAllTasksComplete(serialMotor,customerName); 
				if(check==0)
				{
					TechnicianViewRepairableMotorsApp.updateMotorStatus(serialMotor,customerName, "Repaired(Waiting for final inspection)");
				}
				TechnicianViewRepairableMotorsApp viewMotors=new TechnicianViewRepairableMotorsApp();
				dispose();
        		}	
				
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel_1.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Go back");
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				TechnicianViewRepairableMotorsApp viewMotors=new TechnicianViewRepairableMotorsApp(); //if the back button is pressed, the technician logged in is displayed with the main GUI
				dispose();
			}
		});
		panel_1.add(btnNewButton_1);
		
		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);
		
		assignedTaskTable = new JTable();
		assignedTaskTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Task", "Motor", "Expected Finish Date", "Overdue", "Urgent", "Duration(days)" //these are all the details of the tasks of the logged in technician
			} 
		));
		assignedTaskTable.getColumnModel().getColumn(0).setPreferredWidth(126);
		assignedTaskTable.getColumnModel().getColumn(1).setPreferredWidth(129);
		assignedTaskTable.getColumnModel().getColumn(2).setPreferredWidth(130);
		assignedTaskTable.getColumnModel().getColumn(3).setPreferredWidth(93);
		assignedTaskTable.getColumnModel().getColumn(3).setPreferredWidth(80);
		assignedTaskTable.setFont(new Font("Tahoma", Font.PLAIN, 15));
		scrollPane.setViewportView(assignedTaskTable);
	}
	

void displayAssignedTasksTableData(ArrayList<Task> tableData){ //this is a method to retrieve the details from above and fill the GUI with data
		
		String overdue="No";
	DefaultTableModel tableModel = (DefaultTableModel) 
			assignedTaskTable.getModel();
		tableModel.setRowCount(0);
		for(Task t: tableData) {
			tableModel.addRow(new Object[] {t.getTaskName(),t.getSerial()+" - "+t.getCustomer(), t.getDate(), t.getOverdue(), t.getUrgency(), t.getDuration()});
			
	}		
}
}
