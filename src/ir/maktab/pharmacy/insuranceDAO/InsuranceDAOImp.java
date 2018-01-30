package ir.maktab.pharmacy.insuranceDAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import ir.maktab.pharmacy.drugModel.Drug;
import ir.maktab.pharmacy.insuranceModel.Insurance;

public class InsuranceDAOImp implements InsuranceDAO{

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
	
	@Override
	public void addInsuranse(Insurance insur) {
		
		String sql=null;
		try {
			Class.forName(JDBC_DRIVER);

			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			stmt = conn.createStatement();
			String selectSQL = "SELECT id,name FROM insurance WHERE name='" + insur.getName() + "' or id= '" + insur.getId() + "'";
			ResultSet rs = stmt.executeQuery(selectSQL);
			while (rs.next()) {

				int id = rs.getInt("id");
				String name = rs.getString("name");
				if (insur.getName().equals(name) || insur.getId()==id) {
					System.out.println("Exist this Drug");
					JOptionPane.showMessageDialog(null,"Exist this insure \n" + "Drug ID : " + id,"Warning",JOptionPane.WARNING_MESSAGE);    
					System.out.println("ID: " + insur.getId());
					return;
				}
			}
			
			
			if(insur.getId()==0) {
			 sql = "INSERT INTO insurance (name,insurance_code) " + "VALUES('" + insur.getName() + "','"
					+ insur.getInsuranceCode() + "')";
			}else {
				
				sql = "INSERT INTO insurance (id,name,insurance_code) " + "VALUES('" + insur.getId() + "','" + insur.getName() + "','"
						+ insur.getInsuranceCode() + "')";
			}
			stmt.executeUpdate(sql);

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
	}

	@Override
	public Insurance getInsurance(int insureId) {
		
		Insurance insure = null;
		int id;
		String name = null;
		int insureCode;
		try {
			Class.forName(JDBC_DRIVER);

			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			stmt = conn.createStatement();

			String sql = "SELECT * FROM insurance WHERE id='" + insureId + "'";

			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {

				id = rs.getInt("id");
				name = rs.getString("name");
				insureCode = rs.getInt("insurance_code");
				
				insure = new Insurance(id, name, insureCode);
				
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

		return insure;
	}

	@Override
	public ArrayList<Insurance> getall() {
		ArrayList<Insurance> insList = new ArrayList<>();
		int id;
		String name = null;
		int insuranceCode;
		
		try {
			Class.forName(JDBC_DRIVER);

			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			stmt = conn.createStatement();

			String sql = "SELECT * FROM insurance ";

			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {

				id = rs.getInt("id");
				name = rs.getString("name");
				insuranceCode = rs.getInt("insurance_code");
				Insurance insurance = new Insurance(id, name, insuranceCode);
				insList.add(insurance);
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

		return insList;
	}

	@Override
	public void update(Insurance insurance) {
		try {

			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);

			stmt = conn.createStatement();

			String sql = "UPDATE insurance " + "SET name = '" + insurance.getName() + "',insurance_code = '" + insurance.getInsuranceCode() + "' WHERE id = '" + insurance.getId() + "'";
					
			stmt.executeUpdate(sql);

			stmt.close();
			conn.close();

		} catch (Exception e) {

		}
		
	}

	@Override
	public void remove(int id) {
		Connection conn = null;
		Statement stmt = null;
		System.out.println("re imp" + id);
		try {

			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);

			stmt = conn.createStatement();

			String sql = "DELETE FROM drug_has_insurance " + "WHERE Insurance_id = '" + id + "'";
			stmt.executeUpdate(sql);
			
			String sql1 = "DELETE FROM insurance " + "WHERE id = '" + id + "'";
			stmt.executeUpdate(sql1);

			stmt.close();
			conn.close();

		} catch (Exception e) {

		}
		
	}

}
