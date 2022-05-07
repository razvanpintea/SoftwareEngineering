import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import javax.swing.DropMode;
import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JList;
import javax.swing.JToolBar;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import java.awt.Color;

public class HumanResourcesGUI extends JFrame {//this class is the GUI that represents the main window for the Human Resources user

    private JPanel contentPane;
    public static JTable humanResourcesTable;
    private HumanResourcesApp humanResourcesApp;
    public static String serialNumber;
    public static String customerName;
    private AddMotorGUI addMotorGUI;
    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                	HumanResourcesGUI frame = new HumanResourcesGUI(null); //creates the main window for the Human Resources user
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
    public HumanResourcesGUI(HumanResourcesApp that) {
    	humanResourcesApp=that; //linked to the functional app HumanResourcesApp
    	
    	
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 970, 521);
        
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        
        JMenu mnFileMenu = new JMenu("Account");
        mnFileMenu.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        menuBar.add(mnFileMenu);
        
        JButton logOutBtn = new JButton("Log Out"); //button to logout and display the log in menu
        logOutBtn.setFont(new Font("Tahoma", Font.PLAIN, 14));
        logOutBtn.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		that.setToInvisible();
        		Login loginFrame= new Login();
        		loginFrame.setVisible(true);
        	}
        });
        mnFileMenu.add(logOutBtn);
        
        JLabel lblNewLabel_3 = new JLabel(" ");
        mnFileMenu.add(lblNewLabel_3);
        
        JButton changePassBtn = new JButton("Change Password"); //button to change password
        changePassBtn.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		dispose();
				NewPasswordJDialogHR changePass= new NewPasswordJDialogHR();
				changePass.setVisible(true);
        	}
        });
        changePassBtn.setFont(new Font("Tahoma", Font.PLAIN, 14));
        mnFileMenu.add(changePassBtn);
        contentPane = new JPanel(); 
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout(0, 0));
        
        JPanel panel = new JPanel();
        panel.setBackground(Color.DARK_GRAY);
        contentPane.add(panel, BorderLayout.SOUTH);
        
        JButton addMotorBtn = new JButton("    Add Motor     "); //button to add a new motor
        panel.add(addMotorBtn);
        addMotorBtn.setFont(new Font("Tahoma", Font.PLAIN, 14));
        addMotorBtn.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        	AddMotorGUI another= new AddMotorGUI(that); //opens up the AddMotorGUI class in order to add key details about a motor
        	another.setVisible(true);
        	}
        });
        
        JButton btnNewButton_1 = new JButton("Delete Selected Motor"); //button to delete the selected motor
        panel.add(btnNewButton_1);
        btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
        btnNewButton_1.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		int selectedRow = humanResourcesTable.getSelectedRow();
        		if(selectedRow>=0)
        		{
        			serialNumber= (String) humanResourcesTable.getValueAt(selectedRow,0);
    				customerName= (String) humanResourcesTable.getValueAt(selectedRow,1);        			
        			that.deleteMotor(serialNumber, customerName);
        	}
        	}
        });
        
        JButton returnToCustomerBtn = new JButton("Return to customer"); //button to send the motor back to the customer
        returnToCustomerBtn.setFont(new Font("Tahoma", Font.PLAIN, 14));
        returnToCustomerBtn.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		int selectedRow = humanResourcesTable.getSelectedRow();
        		if(selectedRow>=0)
        		{
        			serialNumber= (String) humanResourcesTable.getValueAt(selectedRow,0);
        			customerName= (String) humanResourcesTable.getValueAt(selectedRow,1);
        			that.updateMotorStatus(serialNumber, customerName,"Sent back to customer");
        	}
        	}	
        });
        panel.add(returnToCustomerBtn);
        
        JButton btnNewButton_3 = new JButton("View All tasks of motor"); //button to view all the tasks of the selected motor
        btnNewButton_3.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		int selectedRow = humanResourcesTable.getSelectedRow();
        		if(selectedRow>=0)
        		{
        			serialNumber= (String) humanResourcesTable.getValueAt(selectedRow,0);
        			customerName= (String) humanResourcesTable.getValueAt(selectedRow,1);
        			ViewAllTasksOfMotorApp allTasksOfMotor=	new ViewAllTasksOfMotorApp();
                	dispose();        	}
        	
			}
        	
        });
        btnNewButton_3.setFont(new Font("Tahoma", Font.PLAIN, 14));
        panel.add(btnNewButton_3);
        
        JButton btnNewButton_4 = new JButton("View assigned tasks of motor"); //button to view the tasks of the selected motor that have been assigned to a technician
        btnNewButton_4.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		int selectedRow = humanResourcesTable.getSelectedRow();
        		if(selectedRow>=0)
        		{
        		serialNumber= (String) humanResourcesTable.getValueAt(selectedRow,0);
        		customerName= (String) humanResourcesTable.getValueAt(selectedRow,1);
        		ViewAssignedTasksOfMotorApp viewAllTasksApp= new ViewAssignedTasksOfMotorApp();
            	dispose();
        	}
        	}
        	
        });
        btnNewButton_4.setFont(new Font("Tahoma", Font.PLAIN, 14));
        panel.add(btnNewButton_4);
 
        
        JPanel panel_1 = new JPanel();
        panel_1.setBackground(Color.DARK_GRAY);
        contentPane.add(panel_1, BorderLayout.NORTH);
        
        JButton btnNewButton = new JButton("Send to inspection"); //button to send the selected motor to inspection
        panel_1.add(btnNewButton);
        btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
        btnNewButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		int selectedRow = humanResourcesTable.getSelectedRow();
        		if(selectedRow>=0)
        		{
        		serialNumber= (String) humanResourcesTable.getValueAt(selectedRow,0);
        		customerName= (String) humanResourcesTable.getValueAt(selectedRow,1);
        		that.updateMotorStatus(serialNumber, customerName, "Sent to inspection"); //updates the motor status to "sent to inspection"
        		}
        	}
        });
        
        JLabel lblNewLabel_1 = new JLabel("                     ");
        panel_1.add(lblNewLabel_1);
        
        JLabel lblNewLabel = new JLabel("Human Rescources Staff Logged in"); //title of the GUI
        lblNewLabel.setForeground(Color.WHITE);
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 17));
        panel_1.add(lblNewLabel);
        
        JLabel lblNewLabel_2 = new JLabel("                                    ");
        lblNewLabel_2.setToolTipText("                                                        ");
        panel_1.add(lblNewLabel_2);
        
        JButton btnNewButton_2 = new JButton("View & Edit Technicians");
        panel_1.add(btnNewButton_2);
        btnNewButton_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
        btnNewButton_2.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        			AllTechniciansApp app=new AllTechniciansApp(); 
        			dispose();
        	}
        });
        
        JScrollPane scrollPane = new JScrollPane();
        contentPane.add(scrollPane, BorderLayout.CENTER);
        
        humanResourcesTable = new JTable();
        humanResourcesTable.setFont(new Font("Tahoma", Font.PLAIN, 14));
        humanResourcesTable.setModel(new DefaultTableModel(
        	new Object[][] {
        	},
        	new String[] {
        		"Serial Number", "Customer Name", "Arrival Date", "Price", "Status" //the key details of the motors that are being displayed
        	}
        ));
        humanResourcesTable.getColumnModel().getColumn(0).setPreferredWidth(102);
        humanResourcesTable.getColumnModel().getColumn(1).setPreferredWidth(114);
        humanResourcesTable.getColumnModel().getColumn(2).setPreferredWidth(101);
        humanResourcesTable.getColumnModel().getColumn(3).setPreferredWidth(89);
        humanResourcesTable.getColumnModel().getColumn(4).setPreferredWidth(198);
        scrollPane.setViewportView(humanResourcesTable);
    }
    
    void displayAdminTableData(ArrayList<Motor> tableData){ //method to retrieve the data from above
    	
    	// Empty the existing data
    DefaultTableModel tableModel = (DefaultTableModel) 
    humanResourcesTable.getModel();
    	tableModel.setRowCount(0);
    	
    	// Some safety code missing here
    	for(Motor m: tableData) {
    		tableModel.addRow(new Object[] {m.getSerialNumber(), m.getCustomer(), m.getDate(), m.getPrice(), m.getStatus()});
    	}
    }



}