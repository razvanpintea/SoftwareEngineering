import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

public class NewPasswordJDialogTechnician extends JDialog { //the GUI for changing a technician's password

	private final JPanel contentPanel = new JPanel();
	private JPasswordField passwordField;
	private JPasswordField passwordField2;
	private String techFirst;
	private String techLast;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			NewPasswordJDialogTechnician dialog = new NewPasswordJDialogTechnician();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Create the dialog.
	 */
	public NewPasswordJDialogTechnician() {
		techFirst=TechnicianViewRepairableMotorsGUI.selectedTechnician.getFirstName();
		techLast=TechnicianViewRepairableMotorsGUI.selectedTechnician.getLastName();
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new GridLayout(3, 2, 0, 0));
		{
			JLabel lblNewLabel = new JLabel("Technician");
			lblNewLabel.setText(techFirst+" "+techLast);
			lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
			contentPanel.add(lblNewLabel);
		}
		{
			JLabel lblNewLabel_1 = new JLabel("Change Your Password");
			lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 16));
			contentPanel.add(lblNewLabel_1);
		}
		{
			JLabel lblNewLabel_3 = new JLabel("New Password");
			lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 13));
			contentPanel.add(lblNewLabel_3);
		}
		{
			passwordField = new JPasswordField();
			contentPanel.add(passwordField);
		}
		{
			JLabel lblNewLabel_2 = new JLabel("Repeat Password (must match)");
			lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 13));
			contentPanel.add(lblNewLabel_2);
		}
		{
			passwordField2 = new JPasswordField();
			contentPanel.add(passwordField2);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						String pass = new String(passwordField.getPassword());
						String pass2 = new String(passwordField2.getPassword());
						String username=techFirst+techLast;

						if(!pass.equals("") && pass.equals(pass2)) //if the new password is written correctly in both of the text boxes
						{
							ProjectDatabase.updateInspectPassword(pass,username); //the password of the current technician is updated
							dispose();
							Login login= new Login();
							login.setVisible(true);
						}
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
					dispose();
					AdminApp adminApp=new AdminApp();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

}
