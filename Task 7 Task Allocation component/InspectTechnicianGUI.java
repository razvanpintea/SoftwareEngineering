import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class InspectTechnicianGUI extends JFrame {//the main window for the inspect technician user

	private JPanel contentPane;
	public static JTable inspectMotorsTable;
	private InspectTechnicianApp inspectTechnicianApp;
	private Login login;
	public static String serial;
	public static String customer;
	public static String taskName;
	public static String skillNeeded;
	public static String techniciansNeeded;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InspectTechnicianGUI frame = new InspectTechnicianGUI(null);//creates the main window for the inspect technician user
					frame.setVisible(true); //makes it visible
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public InspectTechnicianGUI(InspectTechnicianApp that) {
		
		inspectTechnicianApp=that; //linked to the functional class InspectTechnicianApp
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 896, 550);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnFileMenu = new JMenu("Account");
		mnFileMenu.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		menuBar.add(mnFileMenu);
		
		JButton logOutBtn = new JButton("Log Out"); //button to logout and display the log in menu
		logOutBtn.setFont(new Font("Tahoma", Font.PLAIN, 14));
		logOutBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			login = new Login();
			dispose();
			login.setVisible(true);
		
			
			}
		});
		mnFileMenu.add(logOutBtn);
		
		JLabel lblNewLabel_3 = new JLabel(" ");
		mnFileMenu.add(lblNewLabel_3);
		
		JButton changePassBtn = new JButton("Change Password"); //button to change password
		changePassBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				NewPasswordJDialogInspect changePass= new NewPasswordJDialogInspect(); //opens up GUI for password changing
				changePass.setVisible(true);
			}
		});
		changePassBtn.setFont(new Font("Tahoma", Font.PLAIN, 14));
		mnFileMenu.add(changePassBtn);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.DARK_GRAY);
		contentPane.add(panel, BorderLayout.NORTH);
		
		JButton btnNewButton_3 = new JButton("Send Selected Motor To Repair"); //button to send the selected motor to repair(set its status to "sent to repair")
		btnNewButton_3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel.add(btnNewButton_3);
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedRow = inspectMotorsTable.getSelectedRow();
        		if(selectedRow>=0)
        		{
        		serial=(String)inspectMotorsTable.getValueAt(selectedRow,0);
        		customer=(String)inspectMotorsTable.getValueAt(selectedRow,1);
				that.updateMotorStatus(serial,customer,"In repair");
        		}
        		}
		});
		
		JLabel lblNewLabel_1 = new JLabel("           ");
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel = new JLabel("Inspect Technician Logged In"); //title of the GUI
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 17));
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_2 = new JLabel("              ");
		panel.add(lblNewLabel_2);
		
		JButton btnNewButton_5 = new JButton("Set status to Repaired Succesffully"); //button to set the motor status to Repaired "Successfully"
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedRow = inspectMotorsTable.getSelectedRow();
        		if(selectedRow>=0)
        		{
        		serial=(String)inspectMotorsTable.getValueAt(selectedRow,0);
        		customer=(String)inspectMotorsTable.getValueAt(selectedRow,1);
				that.updateMotorStatus(serial,customer,"Repaired Succesfully");
        		}
			}
		});
		panel.add(btnNewButton_5);
		btnNewButton_5.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.DARK_GRAY);
		contentPane.add(panel_1, BorderLayout.SOUTH);
		
		JButton btnNewButton_4 = new JButton("View Current Tasks"); //button to view current tasks of the selected motor
		panel_1.add(btnNewButton_4);
		btnNewButton_4.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedRow = inspectMotorsTable.getSelectedRow();
        		if(selectedRow>=0)
        		{
        		taskName= (String) inspectMotorsTable.getValueAt(selectedRow,0);
        		skillNeeded= (String) inspectMotorsTable.getValueAt(selectedRow,1);
        		techniciansNeeded= (String) inspectMotorsTable.getValueAt(selectedRow,2);
        		dispose();
        		SelectedTasksApp selectedTasksApp=new SelectedTasksApp();
        		}
			}
		});
		
		JButton btnNewButton_1 = new JButton("Add Task to Motor"); //button to add a task to a motor
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			int selectedRow = inspectMotorsTable.getSelectedRow();
        	if(selectedRow>=0)	
        	{
        		AddOwnTaskDialog addTaskToMotor= new AddOwnTaskDialog(that);//opens the GUI to set the details of the task
			addTaskToMotor.setVisible(true);
			dispose();
			}
			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel_1.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Set status to Unrepairable"); //button to set the motor's status to Unrepairable
		panel_1.add(btnNewButton_2);
		btnNewButton_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedRow = inspectMotorsTable.getSelectedRow();
        		if(selectedRow>=0)
        		{
        		serial=(String)inspectMotorsTable.getValueAt(selectedRow,0);
        		customer=(String)inspectMotorsTable.getValueAt(selectedRow,1);
				that.updateMotorStatus(serial,customer,"Unrepairable");
        		}
        		}
		});
		
		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);
		
		inspectMotorsTable = new JTable();
		inspectMotorsTable.setFont(new Font("Tahoma", Font.PLAIN, 14));
		inspectMotorsTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Serial Number", "Customer name", "Arrival Date", "Price", "Status", "Expected Repair Days" //the key details of the motors in the Inspect technician's window
			}
		));
		inspectMotorsTable.getColumnModel().getColumn(0).setPreferredWidth(88);
		inspectMotorsTable.getColumnModel().getColumn(1).setPreferredWidth(101);
		inspectMotorsTable.getColumnModel().getColumn(2).setPreferredWidth(78);
		inspectMotorsTable.getColumnModel().getColumn(3).setPreferredWidth(56);
		inspectMotorsTable.getColumnModel().getColumn(4).setPreferredWidth(185);
		inspectMotorsTable.getColumnModel().getColumn(5).setPreferredWidth(116);
		scrollPane.setViewportView(inspectMotorsTable);
	}
	
	
	
 void displayInspectorTableData(ArrayList<Motor> tableData){ //method to retrieve and display the details from above
    	
    	// Empty the existing data
    DefaultTableModel tableModel = (DefaultTableModel) 
    		inspectMotorsTable.getModel();
    	tableModel.setRowCount(0);
    	
    	// Some safety code missing here
    	for(Motor m: tableData) {
    		tableModel.addRow(new Object[] {m.getSerialNumber(), m.getCustomer(), m.getDate(), m.getPrice(), m.getStatus(), m.getDuration()});
    	}
    }

}
