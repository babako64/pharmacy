package ir.maktab.pharmacy.userModel;

public class User {

	private int id;
	private String fName;
	private String lName;
	private String position;
	private String password;
	private String username;
	
	public User(int id, String fName, String lName, String position, String password, String username) {
		super();
		this.id = id;
		this.fName = fName;
		this.lName = lName;
		this.position = position;
		this.password = password;
		this.username = username;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getfName() {
		return fName;
	}
	public void setfName(String fName) {
		this.fName = fName;
	}
	public String getlName() {
		return lName;
	}
	public void setlName(String lName) {
		this.lName = lName;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	
}
