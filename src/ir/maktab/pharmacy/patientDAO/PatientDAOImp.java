package ir.maktab.pharmacy.patientDAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

import ir.maktab.pharmacy.drugModel.Drug;
import ir.maktab.pharmacy.patientModel.Patient;

public class PatientDAOImp implements PatientDAO{

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
	
	public PatientDAOImp() throws ClassNotFoundException, SQLException {
		
		Class.forName("com.mysql.jdbc.Driver");
		conn = DriverManager.getConnection(DB_URL, USER, PASS);
		stmt = conn.createStatement();
	}
	
	@Override
	public void addPatient(Patient patient) {
		
		String selectSQL = "SELECT id,patient_name FROM patient WHERE id='" + patient.getId()
				+ "' or patient_name= '" + patient.getPationtName() + "'";
		ResultSet rs;
		try {
			rs = stmt.executeQuery(selectSQL);

			String sql = null;

			while (rs.next()) {

				int pId = rs.getInt("id");
				String name = rs.getString("patient_name");
				if (patient.getId() == pId || patient.getPationtName().equals(name)) {
					System.out.println("Exist this patient");
					JOptionPane.showMessageDialog(null, "Exist this patient \n" + "patient ID : " + patient.getId(), "Warning",
							JOptionPane.WARNING_MESSAGE);
					
					return;
				}
			}

			if (patient.getId() == 0) {
				// sql = "INSERT INTO drug (name, stock,expire_date,price) " + "VALUES('" +
				// drug.getName() + "','"
				// + drug.getStock() + "','" + drug.getExpireDate() + "','" + drug.getPrice() +
				// "')";
			} else {

				sql = "INSERT INTO patient (id,patient_name,insurance_number,Insurance_id) " + "VALUES('" + patient.getId()
						+ "','" + patient.getPationtName() + "','" + patient.getInsuranceNumber() + "','" + patient.getInsuranceID() + "')";
			}
			stmt.executeUpdate(sql);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public Patient getPatientByName(String pName) {
		
		int id;
		String name;
		int insuranceNumber;
		int insuranceId;

		Patient patient = null;
		
		String sql = "SELECT * FROM patient WHERE patient_name='" + pName + "'";

		ResultSet rs;
		try {
			rs = stmt.executeQuery(sql);
			
			while (rs.next()) {

				id = rs.getInt("id");
				name = rs.getString("name");
				insuranceNumber = rs.getInt("insurance_number");
				insuranceId = rs.getInt("Insurance_id");
				
				patient = new Patient(id, name, insuranceNumber, insuranceId);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		return patient;
	}
	
	public void close() {

		try {
			if (stmt != null)
				conn.close();
		} catch (SQLException se) {
		}
		try {
			if (conn != null)
				conn.close();
		} catch (SQLException se) {
			se.printStackTrace();
		}
	}

}
