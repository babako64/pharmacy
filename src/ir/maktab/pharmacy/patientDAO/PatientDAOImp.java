package ir.maktab.pharmacy.patientDAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

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
	public int addPatient(Patient patient) {
		
		String sql = null;
		int id = 0;
		
			if (patient.getId() == 0) {
				sql = "INSERT INTO patient (patient_name,insuranse_number,Insurance_id) " + "VALUES('" + patient.getPationtName()
				+ "','" + patient.getInsuranceNumber() + "','" + patient.getInsuranceID() + "')";
			} else {

				sql = "INSERT INTO patient (id,patient_name,insuranse_number,Insurance_id) " + "VALUES('" + patient.getId()
						+ "','" + patient.getPationtName() + "','" + patient.getInsuranceNumber() + "','" + patient.getInsuranceID() + "')";
			}
			try {
				stmt.executeUpdate(sql);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			String selectSQL2 = "SELECT id,patient_name FROM patient WHERE patient_name='" + patient.getPationtName() + "' and insuranse_number= '" + patient.getInsuranceNumber() + "'";
			ResultSet rs2;
			
			
		try {
			rs2 = stmt.executeQuery(selectSQL2);
			while(rs2.next()){
				 id = rs2.getInt("id");
				}
				
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return id;
	}

	public boolean checkPatient(Patient patient) throws SQLException {
		
		String selectSQL = "SELECT patient_name,insuranse_number FROM patient WHERE patient_name='" + patient.getPationtName()
		+ "' and insuranse_number= '" + patient.getInsuranceNumber() + "'";
		ResultSet rs;
		

	rs = stmt.executeQuery(selectSQL);

	

	while (rs.next()) {

		 String name = rs.getString("patient_name");
		 int insuNumber = rs.getInt("insuranse_number");
		if (patient.getPationtName().equals(name) && patient.getInsuranceNumber() == insuNumber) {
//			System.out.println("Exist this patient");
//			JOptionPane.showMessageDialog(null, "Exist this patient \n" + "patient ID : " + patient.getId(), "Warning",
//					JOptionPane.WARNING_MESSAGE);
			
			return true;
		}
	}
	return false;
	}
	
	public int getPatientId(String name, int insuNumber) {
		
		int id = 0;
		int insuranceNumber;
		
		String sql = "SELECT * FROM patient WHERE patient_name='" + name + "' and insuranse_number ='" + insuNumber + "'";

		ResultSet rs;
		try {
			rs = stmt.executeQuery(sql);
			
			while (rs.next()) {

				id = rs.getInt("id");
	
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		return id;
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
				name = rs.getString("patient_name");
				insuranceNumber = rs.getInt("insuranse_number");
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

	@Override
	public ArrayList<Patient> getAll() {
		ArrayList<Patient> patientList = new ArrayList<>();
		int id;
		String name = null;
		int insuranceNumber;
		int insuranceId;
		try {
			Class.forName(JDBC_DRIVER);

			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			stmt = conn.createStatement();

			String sql = "SELECT * FROM patient ";

			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {

				id = rs.getInt("id");
				name = rs.getString("patient_name");
				insuranceNumber = rs.getInt("insuranse_number");
				insuranceId = rs.getInt("Insurance_id");
				Patient patient = new Patient(id, name, insuranceNumber, insuranceId);
				patientList.add(patient);
			}

		} catch (Exception e) {

		} finally {

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

		return patientList;
	}
	
	public void remove(String name) {
		
		

		try {

			Patient p = getPatientByName(name);
			
			String sql1 = "SELECT * FROM prescription WHERE Patient_id='" + p.getId() + "'";

			ResultSet rs;
				rs = stmt.executeQuery(sql1);
				
				while (rs.next()) {
					
					int id = rs.getInt("id");
					
					String sql = "DELETE FROM drug_has_prescription " + "WHERE Prescription_id = '" + id + "'";
					stmt.executeUpdate(sql);
				}
			

		} catch (Exception e) {

		}
		
	}
	
	public void remove2(String name) throws SQLException {
		Patient p = getPatientByName(name);
		String sql2 = "DELETE FROM prescription " + "WHERE Patient_id = '" + p.getId() + "'";
		stmt.executeUpdate(sql2);

		Statement stmt3 = conn.createStatement();
		String sql3 = "DELETE FROM patient " + "WHERE id = '" + p.getId() + "'";
		stmt3.executeUpdate(sql3);
		stmt3.close();
	}

}
