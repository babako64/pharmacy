package ir.maktab.pharmacy.prescriptionDAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

import ir.maktab.pharmacy.patientModel.Patient;
import ir.maktab.pharmacy.prescriptionModel.Prescription;


public class PrescriptionDAOImp implements prescriptionDAO{

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
	
	public PrescriptionDAOImp() throws ClassNotFoundException, SQLException {
		
		Class.forName("com.mysql.jdbc.Driver");
		conn = DriverManager.getConnection(DB_URL, USER, PASS);
		stmt = conn.createStatement();
	}
	
	@Override
	public int addPrescription(Prescription prescription) {
		
	//	String selectSQL = "SELECT id FROM prescription WHERE id='" + prescription.getId();
	//	ResultSet rs;
		int pId = 0;
try {
//	rs = stmt.executeQuery(selectSQL);

	String sql = null;
	
//	while (rs.next()) {
//
//		int pId = rs.getInt("id");
//		if (prescription.getId() == pId ) {
//			System.out.println("Exist this patient");
//			JOptionPane.showMessageDialog(null, "Exist this patient \n" + "patient ID : " + prescription.getId(), "Warning",
//					JOptionPane.WARNING_MESSAGE);
//			
//			return;
//		}
//	}

	if (prescription.getId() == 0) {
		sql = "INSERT INTO prescription (doctor_name,referral_date,Patient_id) " + "VALUES('" + prescription.getDoctorName()
		+ "','" + prescription.getReferralDate() + "','" + prescription.getPatientId() + "')";
	} else {

		sql = "INSERT INTO prescription (doctor_name,referral_date,Patient_id) " + "VALUES('" + prescription.getId()
				+ "','" + prescription.getDoctorName() + "','" + prescription.getReferralDate() + "','" + prescription.getPatientId() + "')";
	}
	stmt.executeUpdate(sql);
	
	String selectSQL2 = "SELECT id FROM prescription WHERE referral_date='" + prescription.getReferralDate() + "' and Patient_id= '" + prescription.getPatientId() + "'";
	ResultSet rs2;
	
	
	rs2 = stmt.executeQuery(selectSQL2);
	while(rs2.next()){
		pId = rs2.getInt("id");
	}

} catch (SQLException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}

	return pId;
	}

	@Override
	public Prescription getPrescription(int pId) {
		int id;
		String doctor_name;
		String referralDate;
		int patientId;

		Prescription prescription = null;
		
		String sql = "SELECT * FROM prescription WHERE id='" + pId + "'";

		ResultSet rs;
		try {
			rs = stmt.executeQuery(sql);
			
			while (rs.next()) {

				id = rs.getInt("id");
				doctor_name = rs.getString("doctor_name");
				referralDate = rs.getString("referral_date");
				patientId = rs.getInt("Patient_id");
				
				prescription = new Prescription(id, doctor_name, referralDate, patientId);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		return null;
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
