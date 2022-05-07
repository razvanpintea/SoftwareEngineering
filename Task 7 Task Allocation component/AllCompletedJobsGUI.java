import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.Font;
import java.util.ArrayList;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class AllCompletedJobsGUI extends JFrame { //GUI for all the completed tasks, their motor and their technician

	private JPanel contentPane;
	private JTable completedJobsTable;
	private AllCompletedJobsApp completedJobsApp;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AllCompletedJobsGUI frame = new AllCompletedJobsGUI(null);
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
	public AllCompletedJobsGUI(AllCompletedJobsApp that) {
		
		completedJobsApp= that; //linked to the functional AllCompletedJobsApp
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 605, 577);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.DARK_GRAY);
		contentPane.add(panel, BorderLayout.NORTH);
		
		JLabel lblNewLabel = new JLabel("Progress of All Technicians (Completed Tasks)");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		panel.add(lblNewLabel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.DARK_GRAY);
		contentPane.add(panel_1, BorderLayout.SOUTH);
		
		JButton btnNewButton = new JButton("Go back");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AdminApp adminApp= new AdminApp();
				dispose();
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel_1.add(btnNewButton);
		
		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);
		
		completedJobsTable = new JTable();
		completedJobsTable.setFont(new Font("Tahoma", Font.PLAIN, 14));
		completedJobsTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Technician First Name", "Technician Last Name", "Task", "Motor" //specifies columns of the table
			}
		));
		completedJobsTable.getColumnModel().getColumn(0).setPreferredWidth(133);
		completedJobsTable.getColumnModel().getColumn(1).setPreferredWidth(134);
		completedJobsTable.getColumnModel().getColumn(2).setPreferredWidth(112);
		completedJobsTable.getColumnModel().getColumn(3).setPreferredWidth(104); //the rows above specify the size of the columns
		scrollPane.setViewportView(completedJobsTable);
	}
void displayAllCompletedTasks(ArrayList<Task> tableData){
		
		DefaultTableModel tableModel = (DefaultTableModel) 
				completedJobsTable.getModel();
		tableModel.setRowCount(0);
		
		for(Task t: tableData) { //fills the table with names of the technicians (from the technician class/table in the database) that completed tasks, the task completed, and the serial number of the motor that had the task
			tableModel.addRow(new Object[] {t.getTechFirst(), t.getTechLast(),t.getTaskName(), t.getSerial()});
		}
	}
	
}
