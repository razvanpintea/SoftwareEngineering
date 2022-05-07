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

public class AdminGUI extends JFrame { //GUI for the Administrator

    private JPanel contentPane;
    private JTable adminMotorTable;
    private AdminApp adminApp;
    //private static Login loginFrame;
    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                	AdminGUI frame = new AdminGUI(null); //creates GUI
                    frame.setVisible(true); //makes GUI visible
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public AdminGUI(AdminApp that) {
    	
    	adminApp=that; //linked to the functional Administrator App
    	
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 749, 521);
        
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        
        JMenu accountBtn = new JMenu("Account");
        accountBtn.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        menuBar.add(accountBtn);
        
        JButton changePassBtn = new JButton("Change Password");
        changePassBtn.setFont(new Font("Tahoma", Font.PLAIN, 14));
        changePassBtn.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
        		
        		dispose();
				NewPasswordJDialogAdmin changePass= new NewPasswordJDialogAdmin(); //when Change Password btn is pressed, creates NewPasswordJDialogAdmin 
				changePass.setVisible(true); //makes NewPasswordJDialogAdmin visible
        	}
        });
        
        JButton logOutBtn_1 = new JButton("Log Out");
        logOutBtn_1.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		dispose();
        		Login loginFrame= new Login();
        		loginFrame.setVisible(true);
        	}
        });
        logOutBtn_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
        accountBtn.add(logOutBtn_1);
        
        JLabel lblNewLabel_1 = new JLabel("   ");
        accountBtn.add(lblNewLabel_1);
        accountBtn.add(changePassBtn);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout(0, 0));
        
        JPanel panel = new JPanel();
        panel.setBackground(Color.DARK_GRAY);
        contentPane.add(panel, BorderLayout.SOUTH);
        
        JButton btnNewButton = new JButton("View Progress of Technicians");
        btnNewButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		AllCompletedJobsApp progressApp= new AllCompletedJobsApp(); //opens list with all the completed jobs of technicians and their name
        		dispose();
        	}
        });
        btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
        panel.add(btnNewButton);
 
        
        JPanel panel_1 = new JPanel();
        panel_1.setBackground(Color.DARK_GRAY);
        contentPane.add(panel_1, BorderLayout.NORTH);
        
        JLabel lblNewLabel = new JLabel("Administrator Logged in");
        lblNewLabel.setForeground(Color.WHITE);
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 17));
        panel_1.add(lblNewLabel);
        
        JScrollPane scrollPane = new JScrollPane();
        contentPane.add(scrollPane, BorderLayout.CENTER);
        
        adminMotorTable = new JTable();
        adminMotorTable.setFont(new Font("Tahoma", Font.PLAIN, 14));
        adminMotorTable.setModel(new DefaultTableModel( //specifies default model of the table created
        	new Object[][] {
        	},
        	new String[] {
        		"Serial #", "Customer Name", "Arrival Date", "Price", "Status", "Expected Repair Days" //specifies columns of the table
        	}
        ));
        adminMotorTable.getColumnModel().getColumn(0).setPreferredWidth(62);
        adminMotorTable.getColumnModel().getColumn(1).setPreferredWidth(102);
        adminMotorTable.getColumnModel().getColumn(2).setPreferredWidth(96);
        adminMotorTable.getColumnModel().getColumn(3).setPreferredWidth(63);
        adminMotorTable.getColumnModel().getColumn(4).setPreferredWidth(181);
        adminMotorTable.getColumnModel().getColumn(5).setPreferredWidth(121); //the rows above specify the size of the columns
        scrollPane.setViewportView(adminMotorTable);
    }
    
    void displayAdminTableData(ArrayList<Motor> tableData){
    	
    	// Empty the existing data
    DefaultTableModel tableModel = (DefaultTableModel) 
    adminMotorTable.getModel();
    	tableModel.setRowCount(0);
    	
    	// Some safety code missing here
    	for(Motor m: tableData) { //fills in the table with all the instances in the Motor class/table and its variables from below
    		tableModel.addRow(new Object[] {m.getSerialNumber(),m.getCustomer(), m.getDate(), m.getPrice(), m.getStatus(), m.getDuration()});
    	}
    }



}