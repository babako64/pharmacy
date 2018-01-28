package ir.maktab.pharmacy.drugInsuranceDAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

import ir.maktab.pharmacy.drugModel.Drug;

public class DrugInsuranceDAOImp implements DrugInsuranceDAO {

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

	public DrugInsuranceDAOImp() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");
		conn = DriverManager.getConnection(DB_URL, USER, PASS);
		stmt = conn.createStatement();
	}

	public static void main(String[] args) throws ClassNotFoundException, SQLException {

		DrugInsuranceDAOImp dhi = new DrugInsuranceDAOImp();

		// dhi.addInsuranceShare(1,1,22);
		System.out.println(dhi.getInsuranceShare("jelphen", "mosalah"));

	}

	@Override
	public void addInsuranceShare(int drugID, int insuranceID, int share) {

		String selectSQL = "SELECT Drug_id,Insurance_id FROM drug_has_insurance WHERE Drug_id='" + drugID
				+ "' or Insurance_id= '" + insuranceID + "'";
		ResultSet rs;
		try {
			rs = stmt.executeQuery(selectSQL);

			String sql = null;

			while (rs.next()) {

				int dId = rs.getInt("Drug_id");
				int iId = rs.getInt("Insurance_id");
				if (drugID == dId || insuranceID == iId) {
					System.out.println("Exist this Drug");
					JOptionPane.showMessageDialog(null, "Exist this Drug \n" + "Drug ID : " + dId, "Warning",
							JOptionPane.WARNING_MESSAGE);
					System.out.println("ID: " + drugID);
					return;
				}
			}

			if (drugID == 0) {
				// sql = "INSERT INTO drug (name, stock,expire_date,price) " + "VALUES('" +
				// drug.getName() + "','"
				// + drug.getStock() + "','" + drug.getExpireDate() + "','" + drug.getPrice() +
				// "')";
			} else {

				sql = "INSERT INTO drug_has_insurance (Drug_id,Insurance_id,insurance_share) " + "VALUES('" + drugID
						+ "','" + insuranceID + "','" + share + "')";
			}
			stmt.executeUpdate(sql);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public int getInsuranceShare(String drugName, String insuranceName) {

		String sql = "SELECT drug_has_insurance.insurance_share from ((drug_has_insurance inner join drug on drug_has_insurance.Drug_id=drug.id) inner join insurance on drug_has_insurance.Insurance_id=insurance.id) where drug.name='"
				+ drugName + "' and insurance.name='" + insuranceName + "'";

		ResultSet rs;
		int share = 0;
		try {
			rs = stmt.executeQuery(sql);

			while (rs.next()) {

				share = rs.getInt("insurance_share");

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return share;

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
