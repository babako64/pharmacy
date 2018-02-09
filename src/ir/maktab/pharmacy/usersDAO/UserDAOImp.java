package ir.maktab.pharmacy.usersDAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

import ir.maktab.pharmacy.prescriptionModel.Prescription;
import ir.maktab.pharmacy.userModel.User;

public class UserDAOImp implements UserDAO {

	private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	private static final String DB_URL = "jdbc:mysql://localhost/pharmacy";

	private static final String USER = "root";
	private static final String PASS = "";

	Connection conn = null;
	Statement stmt = null;

	private final String SELECT = "SELECT";
	private final String FROM = "FROM";
	private final String WHERE = "WHERE";
	private final String INSERT = "INSERT";

	public UserDAOImp() throws ClassNotFoundException, SQLException {

		Class.forName("com.mysql.jdbc.Driver");
		conn = DriverManager.getConnection(DB_URL, USER, PASS);
		stmt = conn.createStatement();
	}

	@Override
	public void addUser(User user) {

		String selectSQL = "SELECT id,first_name,last_name FROM user WHERE first_name='" + user.getfName()
				+ "' and last_name= '" + user.getlName() + "'";
		ResultSet rs;
		try {
			rs = stmt.executeQuery(selectSQL);

			String sql = null;
			int id = 0;
			String fName = null;
			String lName = null;

			while (rs.next()) {

				id = rs.getInt("id");
				fName = rs.getString("first_name");
				lName = rs.getString("last_name");

				// String name = rs.getString("patient_name");
				if (user.getfName() == fName || user.getlName() == lName) {
					System.out.println("Exist this patient");
					JOptionPane.showMessageDialog(null, "Exist this patient \n" + "patient ID : " + fName, "Warning",
							JOptionPane.WARNING_MESSAGE);

					return;
				}
			}

			if (id == 0) {
				sql = "INSERT INTO user (first_name,last_name,position,username,password) " + "VALUES('"
						+ user.getfName() + "','" + user.getlName() + "','" + user.getPosition() + "','"
						+ user.getPassword() + "','" + user.getUsername() + "')";
			} else {

				sql = "INSERT INTO user (first_name,last_name,position,username,password) " + "VALUES('"
						+ user.getfName() + "','" + user.getlName() + "','" + user.getPosition() + "','"
						+ user.getPassword() + "','" + user.getUsername() + "')";
			}
			stmt.executeUpdate(sql);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public User getUserById(int id) {

		return null;
	}

	@Override
	public User checkPassword(String username, String password) {

		int id;
		String firstName;
		String lastName;
		String position;
		String pass;
		String uname;

		String sql = "SELECT * FROM user WHERE username='" + username + "' and password='" + password + "'";

		ResultSet rs;
		try {
			rs = stmt.executeQuery(sql);

			while (rs.next()) {

				id = rs.getInt("id");
				firstName = rs.getString("first_name");
				lastName = rs.getString("last_name");
				position = rs.getString("position");
				uname = rs.getString("username");
				pass = rs.getString("password");

				User user = new User(id, lastName, lastName, position, uname, pass);

				if (username.equals(uname) && password.equals(pass)) {

					return user;
				}

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

}
