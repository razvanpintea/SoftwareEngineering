import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import java.awt.Font;
import javax.swing.ButtonGroup;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JRadioButtonMenuItem;
import java.awt.Choice;
import javax.swing.JMenuItem;

public class AddOwnTaskDialog extends JDialog { // this class represent the GUI for the inspect technician to add new task to a motor

	private final JPanel contentPanel = new JPanel();
	private JTextField taskNameTextField;
	private Choice skillChoice;
	private Choice urgencyChoice;
	private Choice durationChoice;
	private Choice techniciansChoice;
    private InspectTechnicianApp inspectApp;
    private String serial;
    private String customer;
    private int selectedRow;
    private int techniciansNeeded;
    private String skillRequired;
    private String urgency;
    private int duration;
	private String taskName; 
    /**
	 * Launch the application.
	 */
	public static void main(String[] args) { //runs the program and creates the JDialog
		try {
			AddOwnTaskDialog dialog = new AddOwnTaskDialog(null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public AddOwnTaskDialog(InspectTechnicianApp that) { //this class is linked to the InspectTechnicianApp since this class is triggered by the inspect technician
		inspectApp=that; 
		selectedRow=that.displayTable.inspectMotorsTable.getSelectedRow(); //retrieves motor selected in the inspector GUI
		serial= (String) that.displayTable.inspectMotorsTable.getValueAt(selectedRow,0);//retrieves serial of motor selected
		customer=(String) that.displayTable.inspectMotorsTable.getValueAt(selectedRow,1);//customer name of the motor selected
		setBounds(100, 100, 496, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new GridLayout(7, 2, 0, 0));
		{
			JLabel lblNewLabel_2 = new JLabel("Add task to motor :");
			lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 15));
			contentPanel.add(lblNewLabel_2);
			lblNewLabel_2.setText("Add task to motor "); //this is the title of the JDialog
		}
		{
			JLabel lblNewLabel_3 = new JLabel("");
			lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 15));
			contentPanel.add(lblNewLabel_3);
			lblNewLabel_3.setText(serial+"- "+customer); //this label shows which motor is selected
		}
		{
			JLabel lblNewLabel_1 = new JLabel("Task Name");
			lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
			contentPanel.add(lblNewLabel_1);
		}
		{
			taskNameTextField = new JTextField();
			taskNameTextField.setFont(new Font("Tahoma", Font.PLAIN, 14));
			contentPanel.add(taskNameTextField);
			taskNameTextField.setColumns(10);
		}
		{
			JLabel lblNewLabel_4 = new JLabel("");
			contentPanel.add(lblNewLabel_4);
		}
		{
			JLabel lblNewLabel_5 = new JLabel("");
			contentPanel.add(lblNewLabel_5);
		}
		{
			JLabel lblNewLabel = new JLabel("Technicians Needed");
			lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
			contentPanel.add(lblNewLabel);
		}
		{
			techniciansChoice = new Choice(); //choice between technicians needed for the completion of task(1 or 2)
			contentPanel.add(techniciansChoice);
			 techniciansChoice.add("1");   
			 techniciansChoice.add("2");   
		}
		{
			JLabel lblNewLabel_10 = new JLabel("Skill Required");
			lblNewLabel_10.setFont(new Font("Tahoma", Font.PLAIN, 14));
			contentPanel.add(lblNewLabel_10);
		}
		{
			skillChoice = new Choice(); //choice between skill needed for the completion of task
			contentPanel.add(skillChoice);
			skillChoice.add("Accuracy");
			skillChoice.add("Patience");
			skillChoice.add("Strength");
		}
		{
			JLabel lblNewLabel_6 = new JLabel("Urgency"); 
			lblNewLabel_6.setFont(new Font("Tahoma", Font.PLAIN, 14));
			contentPanel.add(lblNewLabel_6);
		}
		{
			urgencyChoice = new Choice(); //choice if the task is urgent or not
			contentPanel.add(urgencyChoice);
			urgencyChoice.add("Yes");
			urgencyChoice.add("No");
		}
		{
			JLabel lblNewLabel_6 = new JLabel("Duration  Expected(days)"); 
			lblNewLabel_6.setFont(new Font("Tahoma", Font.PLAIN, 14));
			contentPanel.add(lblNewLabel_6);
		}
		{
			durationChoice = new Choice(); //choice between the days needed for the completion of task
			contentPanel.add(durationChoice);
			durationChoice.add("1");
			durationChoice.add("2");
			durationChoice.add("3");
			durationChoice.add("4");
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Add task");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
					taskName=taskNameTextField.getText();
					skillRequired=skillChoice.getItem(skillChoice.getSelectedIndex());
					techniciansNeeded=Integer.parseInt(techniciansChoice.getItem(techniciansChoice.getSelectedIndex()));
					urgency=urgencyChoice.getItem(urgencyChoice.getSelectedIndex());
					duration=Integer.parseInt(durationChoice.getItem(durationChoice.getSelectedIndex())); //the rows above take value from the user's input and gives them to variables
					
					if(!taskName.equals("")) //if the task name was not left empty
					{
					that.addTaskToMotor(serial, customer, taskName, skillRequired,techniciansNeeded, urgency, duration );//adds the task needed to repair the motor
					//System.out.println(skillChoice.getItem(skillChoice.getSelectedIndex()));
					dispose();
					InspectTechnicianApp inspApp= new InspectTechnicianApp(); //reopens inspect technician's main GUI
					}
					}
				});
				okButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
					InspectTechnicianApp insApp= new InspectTechnicianApp(); //if cancel button is pressed, it reopens the inspect technician's main GUI
					dispose();
					}
				});
				cancelButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

}
