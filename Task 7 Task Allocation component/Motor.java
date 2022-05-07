import java.util.ArrayList;

public class Motor { //class for the motors and their details. It contains getters and setters for all of its variables
	private String customer;
	private String serialNumber;
	private String arrivalDate; 
	private int price;
	private ArrayList<Task> Tasks;
	private String repairable;
	private int repairDuration;



public Motor(){
	customer = "";
	arrivalDate ="";
	serialNumber="";
	price=0;
	Tasks=null;
	repairable="";
	repairDuration=0;
}

public void setCustomer(String customerName) {
	customer = customerName;
}

public void setDate(String date) {
	arrivalDate = date;
}

public void setSerialNumber(String serial) {
	serialNumber = serial;
}

public void setPrice(int cost) {
	price = cost;
}

public void setTasks(ArrayList<Task> NeededTasks) {
	Tasks = NeededTasks;
}

public void setStatus(String status) {
	repairable = status;
}

public void setDuration(int duration) {
	repairDuration = duration;
}



public String getCustomer() {
	return customer; 
}

public String getSerialNumber() {
	return serialNumber; 
}

public String getDate() {
	return arrivalDate;
}

public int getPrice() {
	return price;
}

public ArrayList<Task> getTasks() {
	return Tasks;
}

public String getStatus() {
	return repairable;
}

public int getDuration() {
	return repairDuration;
}



}