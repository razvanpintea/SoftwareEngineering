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

public class AllTechniciansGUI extends JFrame { //GUI for Human Resources to manage technicians

    private JPanel contentPane;
    private JTable techniciansTable;
    private AllTechniciansApp allTechniciansApp;
    public static String technicianCalledFirstName;
    public static String technicianCalledLastName;
    private SkillsApp skillApp;
    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                	AllTechniciansGUI frame = new AllTechniciansGUI(null); //creates GUI
                    frame.setVisible(true);//makes GUI visible
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public AllTechniciansGUI(AllTechniciansApp that) {
    	allTechniciansApp=that; //linked to functional AllTechniciansApp
    	
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 766, 585);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout(0, 0));
        
        JPanel panel = new JPanel();
        panel.setBackground(Color.DARK_GRAY);
        contentPane.add(panel, BorderLayout.SOUTH);
        
        JButton btnNewButton_2 = new JButton("View Progress");
        btnNewButton_2.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		int selectedRow = techniciansTable.getSelectedRow();
        		if(selectedRow>=0)
        		{
        		technicianCalledFirstName= (String) techniciansTable.getValueAt(selectedRow,0); //get first name of selected technician
            	technicianCalledLastName= (String) techniciansTable.getValueAt(selectedRow,1); //get last name of selected technician
        		dispose();
        		CompletedTasksOfSelectedTechApp completed= new CompletedTasksOfSelectedTechApp(); //when View Progress bttn is pressed, the CompletedTasksOfSelectedTechApp class displays a list with all of the completed tasks of the selected technicians
        	}
        }
        });
        btnNewButton_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
        panel.add(btnNewButton_2);
        
        JButton btnNewButton_3 = new JButton("View Skills"); 
        btnNewButton_3.setFont(new Font("Tahoma", Font.PLAIN, 16));
        btnNewButton_3.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		int selectedRow = techniciansTable.getSelectedRow();
        		if(selectedRow>=0)
        		{
        		technicianCalledFirstName= (String) techniciansTable.getValueAt(selectedRow,0);
        		technicianCalledLastName= (String) techniciansTable.getValueAt(selectedRow,1);
        		skillApp=new SkillsApp(); //opens display to view all the skills of the selected technician, also allowing for adding or deleting skills
        		skillApp.displayTable.technicianName.setText(technicianCalledFirstName+" "+ technicianCalledLastName);
        		}
        	}
        });
        panel.add(btnNewButton_3);
        
        JButton btnNewButton_6 = new JButton("Go Back");
        btnNewButton_6.setFont(new Font("Tahoma", Font.PLAIN, 16));
        btnNewButton_6.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        	dispose();
        	HumanResourcesApp newApp=new HumanResourcesApp();
        	}
        });
        panel.add(btnNewButton_6);
 
        
        JPanel panel_1 = new JPanel();
        panel_1.setBackground(Color.DARK_GRAY);
        contentPane.add(panel_1, BorderLayout.NORTH);
        
        JButton btnNewButton_4 = new JButton("Add New Technician");
        panel_1.add(btnNewButton_4);
        btnNewButton_4.setFont(new Font("Tahoma", Font.PLAIN, 14));
        btnNewButton_4.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		NewTechnicianGUI newTechGUI= new NewTechnicianGUI(that);// when "Add New Technician" btn is pressed, a new GUI to enter technician details is created
        		newTechGUI.setVisible(true); //makes GUI visible
        		}
        });
        
        JLabel lblNewLabel = new JLabel("Human Rescources -  View & Edit Technicians");
        lblNewLabel.setForeground(Color.WHITE);
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 17));
        panel_1.add(lblNewLabel);
        
        JButton btnNewButton_1 = new JButton("Remove Technician");
        panel_1.add(btnNewButton_1);
        btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
        btnNewButton_1.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		int selectedRow = techniciansTable.getSelectedRow();
        		if(selectedRow>=0)
        		{
        		technicianCalledFirstName= (String) techniciansTable.getValueAt(selectedRow,0);
        		technicianCalledLastName = (String) techniciansTable.getValueAt(selectedRow,1);
        		that.deleteTechnician(technicianCalledFirstName,technicianCalledLastName); //when remove technician btn is pressed, it runs this method, removing the technician from the database
        		that.deleteTechnicianFromSkillsTable(technicianCalledFirstName,technicianCalledLastName);//and removing him from the database table that links him to one or more skills
        		}
        	}
        });
        
        JScrollPane scrollPane = new JScrollPane();
        contentPane.add(scrollPane, BorderLayout.CENTER);
        
        techniciansTable = new JTable();
        techniciansTable.setFont(new Font("Tahoma", Font.PLAIN, 15));
        techniciansTable.setModel(new DefaultTableModel(
        	new Object[][] {
        	},
        	new String[] {
        		"First Name", "Last Name" //columns of the table in the GUI
        	}
        ));
        techniciansTable.getColumnModel().getColumn(0).setPreferredWidth(116);
        techniciansTable.getColumnModel().getColumn(1).setPreferredWidth(135);//the rows above specify the size of the columns a
        scrollPane.setViewportView(techniciansTable);
    }
    
    void displayTechnicianTableData(ArrayList<Technician> tableData){
    	
    	// Empty the existing data
    DefaultTableModel tableModel = (DefaultTableModel) 
    techniciansTable.getModel();
    	tableModel.setRowCount(0);
    	for(Technician t: tableData) { //fills the database with instances from the Technician class, with the variables that are returned from the methods below
    		tableModel.addRow(new Object[] {t.getFirstName(), t.getLastName(), t.getAvailable()});
    	}
    }
    
}