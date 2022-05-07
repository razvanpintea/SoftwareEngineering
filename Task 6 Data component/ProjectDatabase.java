import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Locale;
import java.sql.ResultSet;
public class ProjectDatabase {

private static DBConnection database;
private Technician technician;
private static Login login;

public ProjectDatabase(){

}
public static void main(String[] args) {
	
	database = new DBConnection();		
	database.Connect("C:\\Users\\w20018875\\OneDrive - Northumbria University - Production Azure AD\\eclipse\\project\\database.db");
	login =new Login();
	login.setVisible(true);
}


public static ArrayList<String> getUsers() {
	
	String sqlString = new String("SELECT username FROM loginDetails;");
	ResultSet LoginDetails = database.RunSQLQuery(sqlString);
	ArrayList<String> answear = new ArrayList<String>();
	String UserName;
	try {
		while(LoginDetails.next()) {
           UserName=LoginDetails.getString(1);
           answear.add(UserName);
	   }}
	catch (SQLException e) {
	
		System.out.println("Failed to run query: "+sqlString);
	} 
	return answear;
	
}

public static ArrayList<String> getPasswords() {
	
	String sqlString = new String("SELECT password FROM loginDetails;");
	ResultSet LoginDetails = database.RunSQLQuery(sqlString);
	ArrayList<String> answear = new ArrayList<String>();
	String PassWord;
	try {
		while(LoginDetails.next()) {
           PassWord=LoginDetails.getString(1);
           answear.add(PassWord);
	   }}
	catch (SQLException e) {
	
		System.out.println("Failed to run query: "+sqlString);
	} 
	return answear;
	
}

public static ArrayList<Motor> getAllMotors() {
	
	String sqlString = new String("SELECT serial, customer, arrivalDate, price, repairable, time FROM Motor;");
	ResultSet motorDetails = database.RunSQLQuery(sqlString);
	ArrayList<Motor> answear = new ArrayList<Motor>();
	try {
		while(motorDetails.next()) {
			Motor newMotor= new Motor();
		    newMotor.setSerialNumber(motorDetails.getString(1));
		    newMotor.setCustomer(motorDetails.getString(2));
		    newMotor.setDate(motorDetails.getString(3));
		    newMotor.setPrice(Integer.valueOf(motorDetails.getString(4))); 
		    newMotor.setStatus(motorDetails.getString(5));
		    newMotor.setDuration(Integer.valueOf(motorDetails.getString(6))); 
		   answear.add(newMotor);
           
           
	   }}
	catch (SQLException e) {
	
		System.out.println("Failed to run query: "+sqlString);
	}
	return answear;
	
}


public static ArrayList<Motor> getRepairableMotors() {
	
	String sqlString = new String("SELECT serial,customer, arrivalDate, price, repairable, time FROM Motor WHERE repairable= '"+"In repair"+"';");
	ResultSet motorDetails = database.RunSQLQuery(sqlString);
	ArrayList<Motor> answear = new ArrayList<Motor>();
	try {
		while(motorDetails.next()) {
			Motor newMotor= new Motor();
			newMotor.setSerialNumber(motorDetails.getString(1));
		    newMotor.setCustomer(motorDetails.getString(2));
		    newMotor.setDate(motorDetails.getString(3));
		    newMotor.setPrice(Integer.valueOf(motorDetails.getString(4))); 
		    newMotor.setStatus(motorDetails.getString(5));
		    newMotor.setDuration(Integer.valueOf(motorDetails.getString(6)));
           answear.add(newMotor);
           
           
	   }}
	catch (SQLException e) {
	
		System.out.println("Failed to run query: "+sqlString);
	}
	return answear;
	
}

public static void updateMotorStatus(String serialNumber, String customerName,String text) {
	String sqlString = new String("UPDATE Motor SET repairable='"+ text +"' WHERE serial='"+serialNumber+"'AND customer='"+customerName+"';");

	boolean success = database.RunSQL(sqlString);
	
	if(!success) {
		System.out.println("Failed to run query: "+sqlString);
	}
}

public static void deleteMotor(String serialNumber, String customerName	) {

	String sqlString = new String("DELETE FROM Motor WHERE serial='"+serialNumber+"'AND customer='"+customerName+"';");
	String sqlString2 = new String("DELETE FROM TaskMotor WHERE serial='"+serialNumber+"'AND customer='"+customerName+"';");
	boolean success = database.RunSQL(sqlString);
	boolean success2 = database.RunSQL(sqlString2);
	if(!success) {
		System.out.println("Failed to run query: "+sqlString);
	}
	if(!success2) {
		System.out.println("Failed to run query: "+sqlString2);
	}
}


public static void addMotor(String serial, String customerName, String date, int price) {
		String sqlString = new String("INSERT INTO Motor (serial, customer, arrivalDate, price, repairable, time) VALUES('"+serial+"','"+customerName+"', '"+date+"', '"+price+"', '"+"Not inspected yet"+"', '"+"0"+"');");
	
	boolean success1 = database.RunSQL(sqlString);
	if(!success1) {
	System.out.println("Failed to run query"+sqlString);
	}
}


public static ArrayList<Technician> getAllTechnicians() {
	
	String sqlString = new String("SELECT firstName,lastName, available FROM Technician;");
	ResultSet technicianDetails = database.RunSQLQuery(sqlString);
	ArrayList<Technician> answear = new ArrayList<Technician>();
	ArrayList<Technician> duplicates = new ArrayList<Technician>();

	try {
		while(technicianDetails.next()) {
			Technician newTechnician= new Technician();
			newTechnician.setFirstName(technicianDetails.getString(1));
			newTechnician.setLastName(technicianDetails.getString(2));
			newTechnician.setAvailable(technicianDetails.getString(3));
            answear.add(newTechnician);
            duplicates.add(newTechnician);
	   }
		
		 Technician.allTechnicians=new ArrayList<Technician>();
		 for(Technician t: duplicates)
		 {
			 if(!Technician.allTechnicians.contains(t))
				 Technician.allTechnicians.add(t);
			 
		 }
	
	}
	catch (SQLException e) {
	
		System.out.println("Failed to run query: "+sqlString);
	}
	return answear;
}

public static void deleteTechnician(String technicianFirstName,String technicianLastName) {
	String sqlString = new String("DELETE FROM Technician WHERE firstName= '"+technicianFirstName+"' AND lastName='"+technicianLastName+"';");
	String sqlString2 = new String("DELETE FROM loginDetails WHERE username= '"+technicianFirstName+technicianLastName+"';");
	boolean success = database.RunSQL(sqlString);
	boolean success2 = database.RunSQL(sqlString2);
	if(!success || !success2) {
		System.out.println("Failed to run query: "+sqlString);
		System.out.println("Failed to run query: "+sqlString2);
	}
}

 public static void addTechnician(String technicianFirstName, String technicianLastName) {
	String sqlString = new String("INSERT INTO Technician (firstName, lastName, available) VALUES('"+technicianFirstName+"', '"+technicianLastName+"','"+"Yes"+"');");                        
	String sqlString2= new String("INSERT INTO loginDetails (username, password) VALUES ('"+technicianFirstName+technicianLastName+"', '"+"te"+"');");
	boolean success1 = database.RunSQL(sqlString);
    boolean success2 = database.RunSQL(sqlString2);    
    if(!success1)
	System.out.println("Failed to run query"+sqlString);
    if(!success2)
	System.out.println("Failed to run query"+sqlString2);
  	
	}
 
 public static ArrayList<String> getTechnicianLoginDetails(String user) {
		
		String sqlString = new String("SELECT firstName, lastName FROM technicianLogin WHERE user='"+user+"';");
		ResultSet users = database.RunSQLQuery(sqlString);
		ArrayList<String> answear = new ArrayList<String>();
		try {
			while(users.next()) {
	        answear.add(users.getString(1)); 
	        answear.add(users.getString(2));
		   }
		}
		catch (SQLException e) {
		
			System.out.println("Failed to run query: "+sqlString);
		}
		return answear;
		
	}
	

 
 
 public static ArrayList<Skill> getSkills(String technicianFirstName, String technicianLastName) {
		
		String sqlString = new String("SELECT DISTINCT skill FROM TechnicianSkill WHERE (technicianFirstName= '"+technicianFirstName+"' AND technicianLastName='"+technicianLastName+"');");
		ResultSet resultskills = database.RunSQLQuery(sqlString);
		ArrayList<Skill> answear = new ArrayList<Skill>();
		try {
			while(resultskills.next()) {
			Skill newSkill= new Skill();
			newSkill.setName(resultskills.getString(1));
	        answear.add(newSkill);
	           }
			Technician.skills=new ArrayList<String>();
			
			for(Technician t: Technician.allTechnicians)
				for(Skill s: answear)
				if(t.getFirstName().equals(technicianFirstName) && t.getLastName().equals(technicianLastName))
				t.skills.add(s.getName());
		}
		catch (SQLException e) {
		
			System.out.println("Failed to run query: "+sqlString);
		}
		return answear;
		
	}
 
 public static void deleteTechnicianSkill(String technicianFirstName, String technicianLastName, String skill) {
		String sqlString = new String("DELETE FROM TechnicianSkill WHERE (technicianFirstName= '"+technicianFirstName+"' AND technicianLastName='"+technicianLastName+"' AND skill='"+skill+"');");
		boolean success = database.RunSQL(sqlString);
		if(!success) {
			System.out.println("Failed to run query: "+sqlString);
		}
	}
 
 public static void deleteTechnicianFromSkillsTable(String technicianFirstName, String technicianLastName) {
		String sqlString = new String("DELETE FROM TechnicianSkill WHERE (technicianFirstName= '"+technicianFirstName+"' AND technicianLastName='"+technicianLastName+"');");
		boolean success = database.RunSQL(sqlString);
		if(!success) {
			System.out.println("Failed to run query: "+sqlString);
		}
	}
 

 public static void addSkill(String technicianFirstName, String technicianLastName, String skill) {
	String sqlString = new String("INSERT INTO TechnicianSkill VALUES('"+technicianFirstName+"', '"+technicianLastName+"', '"+skill+"');");
  boolean success1 = database.RunSQL(sqlString);
  
  for(Technician t: Technician.allTechnicians)
  {
	  
	  if(t.getFirstName().equals(technicianFirstName) && t.getLastName().equals(technicianLastName))
		  t.skills.add(skill);
  }
  
  if(!success1) {
	System.out.println("Failed to run query"+sqlString);
	}
	}
 
 

 
 public static  ArrayList<Skill> getAllSkills () {
		
		String sqlString = new String("SELECT DISTINCT skill FROM Skill;");
		ResultSet skills = database.RunSQLQuery(sqlString);
		ArrayList<Skill> answear = new ArrayList<Skill>();
		try {
			while(skills.next()) {
			Skill newSkill= new Skill();
			newSkill.setName(skills.getString(1));
	        answear.add(newSkill);
	           
		   }
		}
		catch (SQLException e) {
		
			System.out.println("Failed to run query: "+sqlString);
		}
		return answear;	
	}
 
 
 public static ArrayList<Motor> GetInspectorMotors() {
		
		String sqlString = new String("SELECT DISTINCT serial, customer, arrivalDate, price, repairable, time FROM Motor WHERE repairable= '"+"Sent to inspection"+"' OR repairable= '"+"Repaired(Waiting for final inspection)"+"' ;");
		ResultSet motorDetails = database.RunSQLQuery(sqlString);
		ArrayList<Motor> answear = new ArrayList<Motor>();
		try {
			while(motorDetails.next()) {
				Motor newMotor= new Motor();
			    newMotor.setSerialNumber(motorDetails.getString(1));
			    newMotor.setCustomer(motorDetails.getString(2));
			    newMotor.setDate(motorDetails.getString(3));
			    newMotor.setPrice(Integer.valueOf(motorDetails.getString(4))); 
			    newMotor.setStatus(motorDetails.getString(5));
			    newMotor.setDuration(Integer.valueOf(motorDetails.getString(6)));
			    answear.add(newMotor);
	           
	           
		   }}
		catch (SQLException e) {
		
			System.out.println("Failed to run query: "+sqlString);
		}
		return answear;
		
	}
 
 
 
 public static void setInspectTime(String serialNumber, String customerName,String text) {
		String sqlString = new String("UPDATE Motor SET time ='"+ text +"' WHERE customer='"+customerName+"' AND serial='"+serialNumber+"';");
		boolean success = database.RunSQL(sqlString);
		
		if(!success) {
			System.out.println("Failed to run query: "+sqlString);
		}
	}
 
 
 public static  ArrayList<Task> getAvailableTasks (String serial, String customer, ArrayList<String> skills, String techFirst, String techLast) {
		ArrayList<Task> availableTasks= new ArrayList<Task>();
	 	for(int i=0; i<skills.size();i++)
	 	{
		String sqlString = new String("SELECT DISTINCT task, skill, techniciansNeeded, urgency, duration  FROM TaskMotor WHERE serial='"+serial+"' AND skill='"+skills.get(i)+"' AND techniciansNeeded!=0 AND technicianAssigned  != '"+techFirst+techLast+"' ORDER BY urgency DESC;");
		ResultSet tasks = database.RunSQLQuery(sqlString);
	
		
		try {
			while(tasks.next()) {
			Task newTask= new Task();
			newTask.setTaskName(tasks.getString(1));
			newTask.setSkillRequired(tasks.getString(2));
			newTask.setTechniciansNeeded(Integer.valueOf(tasks.getString(3)));
			newTask.setUrgency(tasks.getString(4));
			newTask.setDuration(Integer.valueOf(tasks.getString(5)));
			availableTasks.add(newTask);
		   }
		}
	 	
		catch (SQLException e) {
		
			System.out.println("Failed to run query: "+sqlString);
		}
	 	}
		return availableTasks;	
	}

 
 
 public static  ArrayList<Task> getSelectedTasks (String serial, String customer) {
		
		String sqlString = new String("SELECT DISTINCT * FROM TaskMotor WHERE customer='"+customer+"' AND serial='"+serial+"';");
		ResultSet tasks = database.RunSQLQuery(sqlString);
		ArrayList<Task> answear = new ArrayList<Task>();
		try {
			while(tasks.next()) {
			Task newTask= new Task();
			newTask.setTaskName(tasks.getString(3));
			newTask.setSkillRequired(tasks.getString(4)); 
			newTask.setTechniciansNeeded(Integer.valueOf(tasks.getString(5)));
			newTask.setUrgency(tasks.getString(8)); 
			newTask.setDuration(Integer.valueOf(tasks.getString(9)));
	        answear.add(newTask);
	           
		   }
		}
		catch (SQLException e) {
		
			System.out.println("Failed to run query: "+sqlString);
		}
		return answear;	 
	}
 
 
 
 public static void addTaskToMotor(String serial,String customer, String task, String skill, int techniciansNeeded, String urgency, int duration) {
		String sqlString = new String("INSERT INTO TaskMotor (serial,customer, task , skill, techniciansNeeded, technicianAssigned, urgency, duration) VALUES('"+serial+"', '"+customer+"','"+task+"', '"+skill+"','"+techniciansNeeded+"', '"+""+"', '"+urgency+"', '"+duration+"');");
		String sqlString2 = new String("UPDATE Motor SET time=time+'"+duration+"' WHERE customer='"+customer+"' AND serial='"+serial+"';");
		boolean success1 = database.RunSQL(sqlString);
		boolean success2 = database.RunSQL(sqlString2);
	    if(!success1) {
		System.out.println("Failed to run query"+sqlString);
		}
	    if(!success2) {
			System.out.println("Failed to run query"+sqlString2);
			}
		}
 
 
 public static void deleteTaskFromMotor(String serial, String customer, String task, int duration) {
		String sqlString = new String("DELETE FROM TaskMotor WHERE (serial = '"+serial+"' AND customer ='"+customer+"' AND task='"+task+"');");
		String sqlString2 = new String("UPDATE Motor SET time=time-'"+duration+"' WHERE customer='"+customer+"' AND serial='"+serial+"';");
		String sqlString3 = new String("DELETE FROM AssignedTasks WHERE task = '"+task+"' AND serial ='"+serial+"';");
		boolean success = database.RunSQL(sqlString);
		boolean success2 = database.RunSQL(sqlString2);
		boolean success3 = database.RunSQL(sqlString3);

		if(!success) {
			System.out.println("Failed to run query: "+sqlString);
		}
		if(!success2) {
			System.out.println("Failed to run query"+sqlString2);
			}
		if(!success3) {
			System.out.println("Failed to run query"+sqlString3);
			}
	}
 
 
 
 public static void assignTaskToTechnician(String task ,String serial, String customerName, String techFirst, String techLast) {
	 	String urgent=null;
	 	int duration=0;
	 	int duration2=0;
	 	String date="";
	 	String geturgency = new String("SELECT urgency, duration FROM TaskMotor WHERE task='"+task+"' AND serial='"+serial+"' AND customer='"+customerName+"';");
	 	ResultSet urgency = database.RunSQLQuery(geturgency);
		try {
		while(urgency.next())
		{
		urgent=urgency.getString(1);
		duration=Integer.valueOf(urgency.getString(2));
		}
		}catch (SQLException e) {
		
		}
		
	
		String sqlStringDateAndDuration = new String("SELECT dateExpected, duration FROM AssignedTasks WHERE customer='"+customerName+"' AND serial='"+serial+"'ORDER BY rowid DESC LIMIT 1;");
		ResultSet tasks = database.RunSQLQuery(sqlStringDateAndDuration);
		try {
		if(tasks.next() == false){
			Calendar c = Calendar.getInstance();
			c.add(Calendar.DATE, duration);
			date= new SimpleDateFormat("dd/MM/yy").format(c.getTime());
			}
		else {
			date=tasks.getString(1);
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yy");
			LocalDate dateNew = LocalDate.parse(date, formatter);
			duration2=Integer.valueOf(tasks.getString(2));
			dateNew=dateNew.plusDays(duration);
			date = dateNew.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT));
		}
		}
		catch (SQLException e) {
			System.out.println("Failed to run query: "+sqlStringDateAndDuration);
		}
		
		
		String sqlString = new String("INSERT INTO AssignedTasks (task, serial, customer, technicianFirst , technicianLast, urgency, dateExpected, duration) VALUES ('"+task+"', '"+serial+"', '"+customerName+"', '"+techFirst+"', '"+techLast+"', '"+urgent+"','"+date+"', '"+duration+"');");
		String sqlString2 = new String("UPDATE Technician SET available='"+"No"+"' WHERE firstName = '"+techFirst+"' AND lastName ='"+techLast+"';");
		String sqlString3 = new String("UPDATE TaskMotor SET technicianAssigned= '"+techFirst+techLast+"' WHERE task='"+task+"' AND serial='"+serial+"' AND customer='"+customerName+"';");
		boolean success1 = database.RunSQL(sqlString);
		boolean success2 = database.RunSQL(sqlString2);
		boolean success3 = database.RunSQL(sqlString3);
	    if(!success1) {
		System.out.println("Failed to run query"+sqlString);
		 if(!success2) {
		System.out.println("Failed to run query"+sqlString2);
		 }
		 if(!success3) {
				System.out.println("Failed to run query"+sqlString2);
				 }
		for(Technician t: Technician.allTechnicians)
		{
			if(t.getFirstName().equals(techFirst) && t.getLastName().equals(techLast))
				t.assignedTasks.add(task);
		}
		}
		}
 
 
 public static void updateTechniciansNeededMinus(String task ,String serial, int techniciansNeeded) {
	 	techniciansNeeded--;
		String sqlString = new String("UPDATE TaskMotor SET techniciansNeeded='"+techniciansNeeded+"' WHERE task = '"+task+"' AND serial ='"+serial+"';");
		boolean success1 = database.RunSQL(sqlString);
	    if(!success1) {
		System.out.println("Failed to run query"+sqlString);
	    }
		}
 
 public static String checkIfAssignedToTask(String firstName ,String lastName) {
	 
	 String theTask="";
	 for(Technician t: Technician.allTechnicians)
			if(t.getFirstName().equals(firstName) && t.getLastName().equals(lastName))
				t.assignedTasks.add(theTask);
				return theTask;
 }
 

 
 public static  ArrayList<Task> getAssignedTasks (String techFirst, String techLast) {
		
		String sqlString = new String("SELECT DISTINCT * FROM AssignedTasks WHERE technicianFirst ='"+techFirst+"' AND technicianLast ='"+techLast+"';");
		ResultSet tasks = database.RunSQLQuery(sqlString);
		ArrayList<Task> answear = new ArrayList<Task>();
		try {
			while(tasks.next()) {
			Task newTask= new Task();
			newTask.setTaskName(tasks.getString(1));
			newTask.setSerial(tasks.getString(2));
			newTask.setCustomer(tasks.getString(3));
			newTask.setTechnicianFirst(tasks.getString(4));
			newTask.setTechnicianLast(tasks.getString(5));
			newTask.setUrgency(tasks.getString(6));
			String date=tasks.getString(7);
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yy");
			LocalDate dateNew = LocalDate.parse(date, formatter);
			LocalDate currentDate = LocalDate.now();
			if(dateNew.isBefore(currentDate))
			newTask.setOverdue("Yes");
			else
			newTask.setOverdue("No");
			newTask.setDate(tasks.getString(7));
		    newTask.setDuration(Integer.valueOf(tasks.getString(8))); 
	        answear.add(newTask);
	           
		   }
		}
		catch (SQLException e) {
		
			System.out.println("Failed to run query: "+sqlString);
		}
		return answear;	
	}
 
 
 public static  ArrayList<Task> getAllAssignedTasks (String serial, String customer) {
		
		String sqlString = new String("SELECT DISTINCT * FROM AssignedTasks WHERE serial ='"+serial+"' AND customer ='"+customer+"';");
		ResultSet tasks = database.RunSQLQuery(sqlString);
		ArrayList<Task> answear = new ArrayList<Task>();
		try {
			while(tasks.next()) {
			Task newTask= new Task();
			newTask.setTaskName(tasks.getString(1));
			newTask.setTechnicianFirst(tasks.getString(4));
			newTask.setTechnicianLast(tasks.getString(5));
	        answear.add(newTask);       
		   }
		}
		catch (SQLException e) {
		
			System.out.println("Failed to run query: "+sqlString);
		}
		return answear;	
	}
 
 
 
 public static void completeTask(String task ,String serial, String customer, String techFirst, String techLast) {
	 	
		String sqlString = new String("INSERT INTO  CompletedTasks ( firstName, lastName, task, motor) VALUES ('"+techFirst+"', '"+techLast+"', '"+task+"','"+serial+"');");
		String sqlString2 = new String("DELETE  FROM AssignedTasks WHERE technicianFirst ='"+techFirst+"' AND technicianLast ='"+techLast+"' AND task ='"+task+"' AND serial ='"+serial+"' AND customer ='"+customer+"' ;");
		String sqlString3 = new String("DELETE FROM TaskMotor WHERE task = '"+task+"' AND serial ='"+serial+"';");
		//String sqlString3 = new String("UPDATE TaskMotor SET techniciansNeeded= techniciansNeeded-1 WHERE task = '"+task+"' AND serial ='"+serial+"';");

		boolean success1 = database.RunSQL(sqlString);
		boolean success2 = database.RunSQL(sqlString2);
		boolean success3 = database.RunSQL(sqlString3);

	    if(!success1) {
		System.out.println("Failed to run query"+sqlString);
	    }
		 if(!success2) {
		System.out.println("Failed to run query"+sqlString2);
		 }
		 if(!success3) {
		System.out.println("Failed to run query"+sqlString2);
		 }
		 
		 
		}
 
 
 
 
 public static int checkIfAllTasksComplete (String serial, String customer) {
		
		String sqlString = new String("SELECT task FROM TaskMotor WHERE customer='"+customer+"' AND serial='"+serial+"';");
		ResultSet tasks = database.RunSQLQuery(sqlString);
		int result=9;
		try {
		if(tasks.next() == false){
			result =0;
			}
		}
		catch (SQLException e) {
			System.out.println("Failed to run query: "+sqlString);
		}
		
		return result;
		
	}
 
 
 
 public static ArrayList<Task> getAllCompletedTasks () {
		
		String sqlString = new String("SELECT DISTINCT * FROM CompletedTasks;");
		ResultSet tasks = database.RunSQLQuery(sqlString);
		ArrayList<Task> answear = new ArrayList<Task>();
		try {
			while(tasks.next()) {
			Task newTask= new Task();
			newTask.setTechnicianFirst(tasks.getString(1));
			newTask.setTechnicianLast(tasks.getString(2));
			newTask.setTaskName(tasks.getString(3));
			newTask.setSerial(tasks.getString(4));
			answear.add(newTask);
	           
		   }
		}
		catch (SQLException e) {
		
			System.out.println("Failed to run query: "+sqlString);
		}
		return answear;	
	}
 
 
 public static ArrayList<Task> getAllCompletedTasksOfTech (String techFirst,String techLast) {
		
		String sqlString = new String("SELECT DISTINCT * FROM CompletedTasks WHERE firstName='"+techFirst+"' AND lastName='"+techLast+"'");
		ResultSet tasks = database.RunSQLQuery(sqlString);
		ArrayList<Task> answear = new ArrayList<Task>();
		try {
			while(tasks.next()) {
			Task newTask= new Task();
			newTask.setTechnicianFirst(tasks.getString(1));
			newTask.setTechnicianLast(tasks.getString(2));
			newTask.setTaskName(tasks.getString(3));
			newTask.setSerial(tasks.getString(4));
			answear.add(newTask);
	           
		   }
		}
		catch (SQLException e) {
		
			System.out.println("Failed to run query: "+sqlString);
		}
		return answear;	
	}
 

 public static void  updateInspectPassword(String password, String username) {
		String sqlString = new String("UPDATE loginDetails SET password='"+password+"' WHERE username='"+username+"';");
		boolean success = database.RunSQL(sqlString);
		
		if(!success) {
			System.out.println("Failed to run query: "+sqlString);
		}
	}
}



	

