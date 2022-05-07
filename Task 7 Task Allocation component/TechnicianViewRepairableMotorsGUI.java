import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import java.awt.Color;

public class TechnicianViewRepairableMotorsGUI extends JFrame { //GUI to allow technicians to view motors that are sent to repair
	private TechnicianViewRepairableMotorsApp technicianViewRepairableMotorsApp;

	private JPanel contentPane;
	private JTable repairableMotors;
	private String[] loginSplit;
	public static String serialNo;
	public static String customerName;
	public static Technician selectedTechnician;
	public static JButton viewTasksButton;
	private String assigned;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					TechnicianViewRepairableMotorsGUI frame = new TechnicianViewRepairableMotorsGUI(null); //creates the GUI
					frame.setVisible(true);//makes it visible
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	} 

	/**
	 * Create the frame.
	 */
	public TechnicianViewRepairableMotorsGUI(TechnicianViewRepairableMotorsApp that) { //linked to the functional TechnicianViewRepairableMotorsApp class
		
		loginSplit=Login.user.split("(?<=.)(?=\\p{Lu})");//username is split before the beggining of the second capital letter, the last name
		
		ProjectDatabase.getAllTechnicians();
		ProjectDatabase.getSkills(loginSplit[0],loginSplit[1]); //gets the skills of the selected technician
		technicianViewRepairableMotorsApp=that;
		for(Technician t: Technician.allTechnicians )  
			if(t.getFirstName().equals(loginSplit[0]))
				if(t.getLastName().equals(loginSplit[1]))
					selectedTechnician=t; 
		
		assigned=ProjectDatabase.checkIfAssignedToTask(selectedTechnician.getFirstName(), selectedTechnician.getLastName());			
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 706, 543);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("Account");
		mnNewMenu.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		menuBar.add(mnNewMenu);
		
		JButton btnNewButton_2 = new JButton("Log out"); //button for logging out
		btnNewButton_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			dispose();
			Login login=new Login();
			login.setVisible(true);
			}
		});
		mnNewMenu.add(btnNewButton_2);
		
		JLabel lblNewLabel_1 = new JLabel(" ");
		mnNewMenu.add(lblNewLabel_1);
		
		JButton changePassBtn = new JButton("Change Password"); //button for changing password
		changePassBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				NewPasswordJDialogTechnician changePass= new NewPasswordJDialogTechnician();
				changePass.setVisible(true);
			}
		});
		changePassBtn.setFont(new Font("Tahoma", Font.PLAIN, 14));
		mnNewMenu.add(changePassBtn);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.DARK_GRAY);
		contentPane.add(panel, BorderLayout.NORTH);
		
		JLabel lblNewLabel = new JLabel("\" \" logged in- view repairable motors"); 
		lblNewLabel.setForeground(Color.WHITE);
		
		lblNewLabel.setText(loginSplit[0]+" "+loginSplit[1]+" (Technician) Logged in - View Repairable Motors");//title of the GUI
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		panel.add(lblNewLabel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.DARK_GRAY);
		contentPane.add(panel_1, BorderLayout.SOUTH);
		
		
		viewTasksButton = new JButton("View Tasks Of Selected Motor"); //button to view the tasks needed to repair the selected motor
		viewTasksButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int selectedRow = repairableMotors.getSelectedRow();
        		if(selectedRow>=0)	
        		{
        		dispose();
        		serialNo= (String) repairableMotors.getValueAt(selectedRow,0);
        		customerName= (String) repairableMotors.getValueAt(selectedRow,1);
        		ViewAvailableTasksApp viewAvailableTasks=new ViewAvailableTasksApp();
        		viewAvailableTasks.displayTable.motorLabel.setText("View Tasks that you have the skills to work on from motor: " +serialNo +" - "+ customerName);//only displays tasks that the technician has the skill to work on
			}
        		}
		});
		viewTasksButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel_1.add(viewTasksButton);
		
		JButton viewCurrentTaskButton = new JButton("View Your Tasks"); //button to view the current daily tasks of the technician
		viewCurrentTaskButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			ViewAssignedTaskApp viewTask= new ViewAssignedTaskApp(); //opens the ViewAssignedTaskGUI 
			dispose();
			}
		});
		viewCurrentTaskButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel_1.add(viewCurrentTaskButton);
		
		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);
		
		repairableMotors = new JTable();
		repairableMotors.setFont(new Font("Tahoma", Font.PLAIN, 13));
		repairableMotors.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Serial Number", "Customer Name", "Arrival Date", "Status" //key details of the motors that are repairable
			}
		));
		repairableMotors.getColumnModel().getColumn(0).setPreferredWidth(112);
		repairableMotors.getColumnModel().getColumn(1).setPreferredWidth(124);
		repairableMotors.getColumnModel().getColumn(2).setPreferredWidth(125);
		repairableMotors.getColumnModel().getColumn(3).setPreferredWidth(167);
		scrollPane.setViewportView(repairableMotors);
	}
	
	
	void displayRepairableMotorsTableData(ArrayList<Motor> tableData){//method to retrieve and display details from above
    	
    DefaultTableModel tableModel = (DefaultTableModel) 
    repairableMotors.getModel();
    	tableModel.setRowCount(0);
    	
    	
    	for(Motor m: tableData) {
    		tableModel.addRow(new Object[] {m.getSerialNumber(),m.getCustomer(), m.getDate(), m.getStatus()});
    	}
    }

}
