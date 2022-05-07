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
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.awt.event.ActionEvent;

public class AddMotorGUI extends JDialog { //the GUI for adding details of a new motor

	private final JPanel contentPanel = new JPanel();
	public JTextField customerNameField;
	public JTextField priceField;
	private HumanResourcesApp humanResourcesApp;
	private JTextField serialField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			AddMotorGUI dialog = new AddMotorGUI(null); //creates the GUI
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true); //displays GUI
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public AddMotorGUI(HumanResourcesApp that) { 
		humanResourcesApp=that;  
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new GridLayout(4, 2, 0, 0));
		{
			JLabel lblNewLabel_1 = new JLabel("Add Motor Details");
			lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
			contentPanel.add(lblNewLabel_1);
		}
		{
			JLabel lblNewLabel_3 = new JLabel("");
			contentPanel.add(lblNewLabel_3);
		}
		{
			JLabel serialLabel = new JLabel("Serial Number");
			contentPanel.add(serialLabel);
		}
		{
			serialField = new JTextField();
			contentPanel.add(serialField);
			serialField.setColumns(10);
		}
		{
			JLabel lblNewLabel_2 = new JLabel("Customer Name");
			contentPanel.add(lblNewLabel_2);
		}
		{
			customerNameField = new JTextField();
			contentPanel.add(customerNameField);
			customerNameField.setColumns(10);
		}
		{
			JLabel lblNewLabel_4 = new JLabel("Price");
			contentPanel.add(lblNewLabel_4);
		}
		{
			priceField = new JTextField();
			contentPanel.add(priceField);
			priceField.setColumns(10);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK"); 
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						 String customerName = customerNameField.getText();
				         String date= new SimpleDateFormat("dd/MM/yyyy").format(Calendar.getInstance().getTime()); //motor arrival date=current day of adding
				         String price=priceField.getText();
				         String serial=serialField.getText();
				         int priceInt= Integer.parseInt(price);
				         that.addMotor(serial, customerName, date, priceInt);//when OK button is pressed, a motor with the data above is added into the database and the software
						 setModal(false);
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
