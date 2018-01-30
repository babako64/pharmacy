package ir.maktab.pharmacy.GUI.mainFrame.patient;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class patientController implements ActionListener{

	patientView jf;
	
	public patientController(patientView jf) {
		this.jf = jf;
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		
		if(e.getSource() == jf.getLoadbtn()) {
			
			//jf.getDrug(jf.getNameTf().getText());
			
		}
		
		if(e.getSource() == jf.getRemovebtn()) {
			
			try {
				jf.remove(jf.NameSelectedRow);
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
		if(e.getSource() == jf.getAddbtn()) {
			
			try {
				jf.addPatient(jf.getNameTf().getText(), Integer.parseInt(jf.getInsureNumberTf().getText()), jf.insuranceName,
						jf.getDoctorNameTf().getText(), jf.getReferralDateTf().getText());
			} catch (NumberFormatException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
		if(e.getSource() == jf.getButtonUpdate()) {
			
//			if (jf.getNameTf().getText().equals("") || jf.getStockTf().getText().equals("")
//					|| jf.getExpireTf().getText().equals("")) {
//				System.out.println("upd");
//				jf.update(jf.IdSelectedRow, jf.NameSelectedRow, jf.StocktSelectedRow, jf.ExpireDateSelectedRow, jf.PricaSelectedRow);
//
//			} else {
//				jf.update(Integer.parseInt(jf.getIdTf().getText()), jf.getNameTf().getText(), Integer.parseInt(jf.getStockTf().getText()),
//						jf.getExpireTf().getText(), Double.parseDouble(jf.getpriceTf().getText()));
//						
////				textField.setText("");
////				textField_1.setText("");
////				textField_2.setText("");
////				textField_3.setText("");
//			}
		}
		
	}

}
