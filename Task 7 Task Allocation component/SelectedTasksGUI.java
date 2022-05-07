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

public class SelectedTasksGUI extends JFrame { //GUI to allow the inspect technician to view or delete the tasks needed to repair a motor

	private JPanel contentPane;
	private JTable selectedTasks;
	public static String serialNo;
	public static String customerName;
	private SelectedTasksApp selectedTasksApp;
	private String selectedTask;
	private int duration;
	public JLabel motorSerialAndCustomer;
	public JButton deleteTaskBtn;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SelectedTasksGUI frame = new SelectedTasksGUI(null);
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
	public SelectedTasksGUI(SelectedTasksApp that) {
		selectedTasksApp=that; //linked to the functional SelectedTasksApp class
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 561, 557);
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
		serialNo=(String)InspectTechnicianGUI.inspectMotorsTable.getValueAt(InspectTechnicianGUI.inspectMotorsTable.getSelectedRow(),0);
		customerName=(String)InspectTechnicianGUI.inspectMotorsTable.getValueAt(InspectTechnicianGUI.inspectMotorsTable.getSelectedRow(),1);
		motorSerialAndCustomer.setText("Tasks Needed to Repair Motor: " + serialNo +" - "+customerName);
		panel.add(motorSerialAndCustomer);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.DARK_GRAY);
		contentPane.add(panel_1, BorderLayout.SOUTH);
		
		deleteTaskBtn = new JButton("Delete Task");
		deleteTaskBtn.setFont(new Font("Tahoma", Font.PLAIN, 14));
		deleteTaskBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { 
				int selectedRow = selectedTasks.getSelectedRow();
        		if(selectedRow>=0)
        	    selectedTask= (String) selectedTasks.getValueAt(selectedRow,0);
        		serialNo=(String)InspectTechnicianGUI.inspectMotorsTable.getValueAt(InspectTechnicianGUI.inspectMotorsTable.getSelectedRow(),0);
        		customerName=(String)InspectTechnicianGUI.inspectMotorsTable.getValueAt(InspectTechnicianGUI.inspectMotorsTable.getSelectedRow(),1);
        		duration=(Integer)selectedTasks.getValueAt(selectedRow,3);
        		that.deleteTask(serialNo, customerName, selectedTask, duration); //if the delete task button is pressed, this method deletes the selected task from the motor
				
			}
		});
		panel_1.add(deleteTaskBtn);
		
		JButton btnNewButton_2 = new JButton("Go back");
		btnNewButton_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				InspectTechnicianApp newInspectApp= new InspectTechnicianApp(); //the go back button re opens the main window of the Inspect Technician
			}
		});
		panel_1.add(btnNewButton_2);
		
		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);
		
		selectedTasks = new JTable();
		selectedTasks.setFont(new Font("Tahoma", Font.PLAIN, 14));
		selectedTasks.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Task", "Skill Required", "Technicians Needed", "Duration(days)", "Urgent" //details of the tasks displayed in the GUI
			}
		));
		selectedTasks.getColumnModel().getColumn(0).setPreferredWidth(137);
		selectedTasks.getColumnModel().getColumn(1).setPreferredWidth(114);
		selectedTasks.getColumnModel().getColumn(2).setPreferredWidth(168);
		selectedTasks.getColumnModel().getColumn(3).setPreferredWidth(122);
		scrollPane.setViewportView(selectedTasks);
	}
	
void displaySelectedTasksTableData(ArrayList<Task> tableData){ //method to display the details from above
		
		
	DefaultTableModel tableModel = (DefaultTableModel) 
			selectedTasks.getModel();
		tableModel.setRowCount(0);
		
		for(Task t: tableData) {
			tableModel.addRow(new Object[] {t.getTaskName(), t.getSkillRequired(),t.getTechniciansNeeded(), t.getDuration(), t.getUrgency()});
		}
	}

}
