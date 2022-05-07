import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class NewTechnicianGUI extends JDialog { //the GUI for the Human Resources user to add details of a new technician

	private final JPanel contentPanel = new JPanel();
	public JTextField lastNameField;
	AllTechniciansApp techniciansApp;
	private JTextField firstNameField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			NewTechnicianGUI dialog = new NewTechnicianGUI(null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public NewTechnicianGUI(AllTechniciansApp that) {
		techniciansApp=that;
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new GridLayout(4, 2, 0, 0));
		{
			JLabel lblNewLabel_1 = new JLabel("Add Technician Details");
			lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
			contentPanel.add(lblNewLabel_1);
		}
		{
			JLabel lblNewLabel_3 = new JLabel("");
			contentPanel.add(lblNewLabel_3);
		}
		{
			JLabel lblNewLabel_4 = new JLabel("First Name");
			contentPanel.add(lblNewLabel_4);
		}
		{
			firstNameField = new JTextField();
			contentPanel.add(firstNameField);
			firstNameField.setColumns(10);
		}
		{
			JLabel lblNewLabel_2 = new JLabel("Last Name");
			contentPanel.add(lblNewLabel_2);
		}
		{
			lastNameField = new JTextField();
			contentPanel.add(lastNameField);
			lastNameField.setColumns(10);
		}
		{
			JLabel lblNewLabel_5 = new JLabel("");
			contentPanel.add(lblNewLabel_5);
		}
		{
			JLabel lblNewLabel_6 = new JLabel("");
			contentPanel.add(lblNewLabel_6);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						 String technicianFirstName = firstNameField.getText();
						 String technicianLastName = lastNameField.getText();
						 
						  String firstLetter = technicianFirstName.substring(0,1).toUpperCase();
					      String restLetters = technicianFirstName.substring(1).toLowerCase();
					      technicianFirstName=firstLetter+restLetters;
					      firstLetter = technicianLastName.substring(0,1).toUpperCase();
					      restLetters = technicianLastName.substring(1).toLowerCase();
					      technicianLastName=firstLetter+restLetters;
				          that.addTechnician(technicianFirstName, technicianLastName); //the rows from above have the purpose of formatting the technicians first and last name so both of its names begin with a capital letter and the rest are converted to small letters, as their username is FirstnameLastname
					      dispose();
					}

				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						 setModal(false);
					        dispose();
					}

				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

}
