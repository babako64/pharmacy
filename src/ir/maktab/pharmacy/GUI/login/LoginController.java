package ir.maktab.pharmacy.GUI.login;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

public class LoginController implements ActionListener{

	LoginView jf;
	
	public LoginController(LoginView jf) {
		
		this.jf = jf;
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == jf.getLogin()) {
			
			try {
				jf.checkUser(jf.getUserNameTf().getText(), jf.getPassTf().getText());
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
	}
	
	
}
