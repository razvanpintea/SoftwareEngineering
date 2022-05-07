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

public class ViewAllTasksOfMotorGUI extends JFrame {//GUI for the Human Resources user to view all the tasks needed in order to repair a motor

	private JPanel contentPane;
	private JTable selectedTasks;
	public static String serialNo;
	public static String customerName;
	private ViewAllTasksOfMotorApp allTasksOfMotorApp;
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
	public ViewAllTasksOfMotorGUI(ViewAllTasksOfMotorApp that) {
		allTasksOfMotorApp=that;//linked to the functional ViewAllTasksOfMotorApp class
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 388, 574);
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
		motorSerialAndCustomer.setText("Tasks Needed to Repair Motor: " + serialNo +" - "+customerName);
		panel.add(motorSerialAndCustomer);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.DARK_GRAY);
		contentPane.add(panel_1, BorderLayout.SOUTH);
		
		JButton btnNewButton_2 = new JButton("Go back");
		btnNewButton_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				HumanResourcesApp HRapp= new HumanResourcesApp();
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
				"Task Name"
			}
		));
		selectedTasks.getColumnModel().getColumn(0).setPreferredWidth(137);
		scrollPane.setViewportView(selectedTasks);
	}
	
void displaySelectedTasksTableData(ArrayList<Task> tableData){//method to retrieve and display the names of the tasks
		
	DefaultTableModel tableModel = (DefaultTableModel) 
			selectedTasks.getModel();
		tableModel.setRowCount(0);
		
		for(Task t: tableData) {
			tableModel.addRow(new Object[] {t.getTaskName()});
		}
	}

}
