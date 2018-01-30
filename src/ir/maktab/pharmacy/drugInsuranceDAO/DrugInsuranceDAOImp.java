package ir.maktab.pharmacy.drugInsuranceDAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import ir.maktab.pharmacy.drugInsuranceModel.DrugInsurance;
import ir.maktab.pharmacy.drugModel.Drug;
import ir.maktab.pharmacy.insuranceModel.Insurance;

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


	@Override
	public void addInsuranceShare(String drugName, String insuranceName,int share) {

		
		int drugID = 0;
		int insuranceID = 0;
		try {
			drugID = getDrugID(drugName);
			 insuranceID = getInsuId(insuranceName);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		String selectSQL = "SELECT Drug_id,Insurance_id FROM drug_has_insurance WHERE Drug_id='" + drugID
				+ "' and Insurance_id= '" + insuranceID + "'";
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
	public ArrayList<DrugInsurance> getInsuranceShare(String drugName, String insuranceName) {

		ArrayList<DrugInsurance> diList = new ArrayList<>();
		
		String sql = "SELECT drug_has_insurance.Drug_id,drug_has_insurance.Insurance_id,drug_has_insurance.insurance_share from ((drug_has_insurance inner join drug on drug_has_insurance.Drug_id=drug.id) inner join insurance on drug_has_insurance.Insurance_id=insurance.id) where drug.name='"
				+ drugName + "' and insurance.name='" + insuranceName + "'";

		ResultSet rs;
		int drugId=0;
		int insurId=0;
		int share = 0;
		try {
			rs = stmt.executeQuery(sql);

			while (rs.next()) {

				drugId = rs.getInt("Drug_id");
				insurId = rs.getInt("Insurance_id");
				share = rs.getInt("insurance_share");
				
				String dName = getDrugName(drugId);
				String iName = getInsuranceName(insurId);
				DrugInsurance drugInsu = new DrugInsurance(dName,iName,share);
				diList.add(drugInsu);

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return diList;

	}
	
	public int getShare(int drugId, int insuID) throws SQLException {
		
		String selectSQL = "SELECT insurance_share FROM drug_has_insurance WHERE Drug_id='" + drugId
				+ "' and Insurance_id= '" + insuID + "'";
		ResultSet rs;
		rs = stmt.executeQuery(selectSQL);

		String sql = null;
		int dId = 0;
		while (rs.next()) {
			 dId = rs.getInt("insurance_share");
		}
		
		return dId;
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
	public ArrayList<DrugInsurance> getAll() {
		
		ArrayList<DrugInsurance> diList = new ArrayList<>();
		int drugId;
		int insuranceId;
		int insuranceShare;
		
		try {

			String sql = "SELECT * FROM drug_has_insurance ";

			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				System.out.println("g");
				drugId = rs.getInt("Drug_id");
				insuranceId = rs.getInt("Insurance_id");
				insuranceShare = rs.getInt("insurance_share");
				
				String dName = getDrugName(drugId);
				String iName = getInsuranceName(insuranceId);
				DrugInsurance drugInsu = new DrugInsurance(dName,iName,insuranceShare);
				diList.add(drugInsu);
			}

		} catch (Exception e) {
			
		}
		return diList;
	}
	
	public String getDrugName(int id) throws SQLException {
		System.out.println("d name");
		
		Connection conn2 = DriverManager.getConnection(DB_URL, USER, PASS);
		Statement stmt2 = conn2.createStatement();
		
		String sql = "SELECT * FROM drug WHERE drug.id='" + id + "'";

		ResultSet rs = stmt2.executeQuery(sql);

		String name=null;
		
		while (rs.next()) {

			int id1 = rs.getInt("id");
			name = rs.getString("name");
			int stock = rs.getInt("stock");
			String expireDate = rs.getString("expire_date");
			double price = rs.getDouble("price");
			
		}
		System.out.println(name);
		return name;
	}
	
	public String getInsuranceName(int id) throws SQLException {
		
		String sql = "SELECT * FROM insurance WHERE id='" + id + "'";

		Connection conn3 = DriverManager.getConnection(DB_URL, USER, PASS);
		Statement stmt3 = conn3.createStatement();
		
		ResultSet rs = stmt3.executeQuery(sql);

		String name=null;
		
		while (rs.next()) {

			int id1 = rs.getInt("id");
			name = rs.getString("name");
			int insureCode = rs.getInt("insurance_code");
					
		}
		System.out.println(name);
		return name;
	}
	
	public int getDrugID(String name) throws SQLException {
		String sql = "SELECT * FROM drug WHERE name='" + name + "'";

		
		ResultSet rs = stmt.executeQuery(sql);

		int id = 0;
		
		while (rs.next()) {

			id = rs.getInt("id");
			String	name2 = rs.getString("name");
			int stock = rs.getInt("stock");
			String expireDate = rs.getString("expire_date");
			double price = rs.getDouble("price");
					
		}
		
		return id;
	}
	
	public int getInsuId(String name) throws SQLException {
		String sql = "SELECT * FROM insurance WHERE name='" + name + "'";

		
		ResultSet rs = stmt.executeQuery(sql);

		int id=0;
		
		while (rs.next()) {

			id = rs.getInt("id");
			String name1 = rs.getString("name");
			int insureCode = rs.getInt("insurance_code");
					
		}
		System.out.println(name);
		return id;
	}


	@Override
	public void remove(String drugName, String insureName) {
		
		int drugID = 0;
		int insuranceID = 0;
		try {
			drugID = getDrugID(drugName);
			 insuranceID = getInsuId(insureName);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		String sql = "DELETE FROM drug_has_insurance " + "WHERE Drug_id = '" + drugID + "' and Insurance_id = '" + insuranceID + "'";
		try {
			stmt.executeUpdate(sql);
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		
	}


	@Override
	public void update(String drugName, String insuranceName, int share) {

		int drugID = 0;
		int insuranceID = 0;
		try {
			drugID = getDrugID(drugName);
			 insuranceID = getInsuId(insuranceName);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		try {

			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);

			stmt = conn.createStatement();

			String sql = "UPDATE drug_has_insurance " + "SET insurance_share = '" + share + "' WHERE Drug_id = '" + drugID + "' and Insurance_id = '" + insuranceID + "'";
					
			stmt.executeUpdate(sql);

			stmt.close();
			conn.close();

		} catch (Exception e) {

		}
		
	}
}
