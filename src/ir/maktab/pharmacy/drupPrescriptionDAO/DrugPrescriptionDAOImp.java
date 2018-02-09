package ir.maktab.pharmacy.drupPrescriptionDAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import ir.maktab.pharmacy.drugDAO.DrugDAOImp;
import ir.maktab.pharmacy.drugInsuranceModel.DrugInsurance;
import ir.maktab.pharmacy.drugPrescriptionModel.DrugPrescription;
import ir.maktab.pharmacy.prescriptionModel.Prescription;

public class DrugPrescriptionDAOImp implements DrugPerscriptionDAO {

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

	public DrugPrescriptionDAOImp() throws ClassNotFoundException, SQLException {

		Class.forName("com.mysql.jdbc.Driver");
		conn = DriverManager.getConnection(DB_URL, USER, PASS);
		stmt = conn.createStatement();
	}

	@Override
	public void addDrupPrescription(int drugId, int prescriptionID, int count, double payment) {

		new DrugDAOImp().updateStock(drugId, count);

		String selectSQL = "SELECT Drug_id,Prescription_id FROM drug_has_prescription WHERE Drug_id='" + drugId
				+ "' and Prescription_id= '" + prescriptionID + "'";
		ResultSet rs;
		try {
			rs = stmt.executeQuery(selectSQL);

			String sql = null;

			while (rs.next()) {

				int dId = rs.getInt("Drug_id");
				int pId = rs.getInt("Prescription_id");

				// String name = rs.getString("patient_name");
				if (drugId == dId || prescriptionID == pId) {
					System.out.println("Exist this drug");
					JOptionPane.showMessageDialog(null, "Exist this drug \n" + "patient ID : " + drugId, "Warning",
							JOptionPane.WARNING_MESSAGE);

					return;
				}
			}

			if (drugId == 0) {
				sql = "INSERT INTO drug_has_prescription (Prescription_id,count,payment) " + "VALUES('" + prescriptionID
						+ "','" + count + "','" + payment + "')";
			} else {

				sql = "INSERT INTO drug_has_prescription (Drug_id,Prescription_id,count,payment) " + "VALUES('" + drugId
						+ "','" + prescriptionID + "','" + count + "','" + payment + "')";
			}
			stmt.executeUpdate(sql);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public double getPayment(int drugId, int prescriptionID) {

		return 0;
	}

	public ArrayList<DrugPrescription> getDrug(int dID, int pID) throws SQLException {

		String selectSQL = "SELECT Drug_id,Prescription_id,count,payment FROM drug_has_prescription WHERE Prescription_id='"
				+ pID + "'";
		ResultSet rs;
		ArrayList<DrugPrescription> dpList = new ArrayList<>();
		rs = stmt.executeQuery(selectSQL);
		int dId = 0;
		while (rs.next()) {
			int drugId = rs.getInt("Drug_id");
			int prescID = rs.getInt("Prescription_id");
			int count = rs.getInt("count");
			double payment = rs.getDouble("payment");
			DrugPrescription dPres = new DrugPrescription(drugId, prescID, count, payment);
			dpList.add(dPres);
		}
		return dpList;
	}

	public void removePerscription(int drugId) {

		try {

			String sql = "DELETE FROM drug_has_prescription " + "WHERE Drug_id = '" + drugId + "'";
			stmt.executeUpdate(sql);

			stmt.close();
			conn.close();

		} catch (Exception e) {

		}
	}

}
