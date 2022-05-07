import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class ViewAvailableTasksGUI extends JFrame { //GUI to allow technicians to view the available tasks to work on

	private JPanel contentPane;
	private ViewAvailableTasksApp availableTasksApp;
	private JTable availableTasksTable;
	public static JLabel motorLabel;
	private String selectedTask;
	private String requiredSkill;
	private int techniciansNeeded;
	public JButton chooseToWorkOnTaskButton;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewAvailableTasksGUI frame = new ViewAvailableTasksGUI(null);
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
	public ViewAvailableTasksGUI(ViewAvailableTasksApp that) { 
		
		availableTasksApp= that; //linked to the ViewAvailableTasksApp functional app
	
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);     
		setBounds(100, 100, 508, 465);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.DARK_GRAY);
		contentPane.add(panel, BorderLayout.NORTH);
		
		 motorLabel = new JLabel("New label");
		 motorLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		 motorLabel.setForeground(Color.WHITE);
		panel.add(motorLabel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.DARK_GRAY);
		contentPane.add(panel_1, BorderLayout.SOUTH);
		
		chooseToWorkOnTaskButton = new JButton("Choose to Work on Task");//button to work on a selected tasks
		chooseToWorkOnTaskButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedRow = availableTasksTable.getSelectedRow();
        		if(selectedRow>=0)
        		{
        			selectedTask=(String) availableTasksTable.getValueAt(selectedRow,0);
        			
        			String techFirstName=TechnicianViewRepairableMotorsGUI.selectedTechnician.getFirstName();
        			String techLastName=TechnicianViewRepairableMotorsGUI.selectedTechnician.getLastName();
        			String serialnumber=TechnicianViewRepairableMotorsGUI.serialNo;
        			String customerName=TechnicianViewRepairableMotorsGUI.customerName;
        			that.assignTask(selectedTask, serialnumber, customerName, techFirstName, techLastName ); //when button is pressed, technicians gets the task added into its daily tasks
        			techniciansNeeded=(Integer) availableTasksTable.getValueAt(selectedRow,2);
        			that.updateTechniciansNeededMinus(selectedTask, serialnumber, techniciansNeeded);//1 is subtracted from the from technicians needed to repair the chosen task 
        			dispose();
        			TechnicianViewRepairableMotorsApp viewMotors=new TechnicianViewRepairableMotorsApp();//the main window for the technician users is displayed again
        			
        			
        		}
			}
		});
		chooseToWorkOnTaskButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel_1.add(chooseToWorkOnTaskButton);
		
		JButton btnNewButton_1 = new JButton("Go back");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			dispose();
			TechnicianViewRepairableMotorsApp viewMotors=new TechnicianViewRepairableMotorsApp();//if back button is pressed, the main window for the technician users is displayed again
			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel_1.add(btnNewButton_1);
		
		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);
		
		availableTasksTable = new JTable();
		availableTasksTable.setFont(new Font("Tahoma", Font.PLAIN, 14));
		availableTasksTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Task", "Skill Required", "Technicians Needed", "Urgent", "Duration(days)"//details of the tasks that are displayed in the main window of the technicians
			}
		));
		availableTasksTable.getColumnModel().getColumn(0).setPreferredWidth(104);
		availableTasksTable.getColumnModel().getColumn(1).setPreferredWidth(89);
		availableTasksTable.getColumnModel().getColumn(2).setPreferredWidth(161);
		scrollPane.setViewportView(availableTasksTable);
	}
	
	
void displayAvailableTasksTableData(ArrayList<Task> tableData){//method to retrieve and display the details from above
		
		DefaultTableModel tableModel = (DefaultTableModel) 
				availableTasksTable.getModel();
		tableModel.setRowCount(0);
		
		for(Task t: tableData) {
			tableModel.addRow(new Object[] {t.getTaskName(), t.getSkillRequired(),t.getTechniciansNeeded(), t.getUrgency(), t.getDuration()});
		}
	}

}
