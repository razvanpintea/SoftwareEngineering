import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

public class Login extends JFrame { //the Login menu class

	private JPanel contentPane;
	private JTextField usernameField;
	private AdminApp adminApp;
	private ChooseAllSkillsApp chooseSkill;
	private HumanResourcesApp humanResourcesApp;
	private JPasswordField passwordField;
	public static Login frame;
	private InspectTechnicianApp inspectTechnicianApp;
	private TechnicianViewRepairableMotorsApp technicianViewRepairableMotorsApp;
	public static String user;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new Login(); //creates the login GUI
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
	public Login() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(5, 1, 0, 0));
		
		JLabel lblNewLabel_1 = new JLabel("Username");
		contentPane.add(lblNewLabel_1);
		
		usernameField = new JTextField();
		contentPane.add(usernameField);
		usernameField.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Password");
		contentPane.add(lblNewLabel);
		
		passwordField = new JPasswordField();
		contentPane.add(passwordField);
		
		JButton loginBtn = new JButton("Log in");
		loginBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent haide) {
				user= new String(usernameField.getText());
				String pass = new String(passwordField.getPassword());
				ArrayList<String> usernames=ProjectDatabase.getUsers(); //retrieves all the usernames in the database
				ArrayList<String> passwords=ProjectDatabase.getPasswords();//retrieves all the passwords in the database

				ProjectDatabase.getUsers();
					
			if(usernames.contains(user)) //if the input of the username is present in the database
				{
				
				

				int index=usernames.indexOf(user);
				if(pass.equals(passwords.get(index))) //if the password matches to the same row of the username in the database
					{
					if(user.equals("admin")) 
					{
					adminApp=new AdminApp(); //if the username is admin then admin GUI is displayed
					dispose();
					}
				
					if(user.equals("hr"))
					{
					humanResourcesApp=new HumanResourcesApp(); //if the username is hr then the HumanResources GUI is displayed
					dispose();		
					}
					
					if(user.equals("is"))
					{
					inspectTechnicianApp=new InspectTechnicianApp();//if the username is is then the Inspect Technician's GUI is displayed
					dispose();
					}
					
					if(!user.equals("is") && !user.equals("admin") && !user.equals("hr")) //if the username is none of the above but the password matches the input, it means it is a technician
					{
						technicianViewRepairableMotorsApp= new TechnicianViewRepairableMotorsApp();	//opens main windows for the logged in technician					
						dispose();
						
					}
				
				}
				}
	
				}
				
				
		});
		contentPane.add(loginBtn);
	}

}
