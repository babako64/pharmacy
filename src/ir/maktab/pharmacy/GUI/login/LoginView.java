package ir.maktab.pharmacy.GUI.login;

import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;

import ir.maktab.pharmacy.GUI.mainFrame.MainFrameView;
import ir.maktab.pharmacy.resource.HintTextField;
import ir.maktab.pharmacy.userModel.User;
import ir.maktab.pharmacy.usersDAO.UserDAOImp;

public class LoginView extends JFrame {

	JTextField username;
	JPasswordField password;

	JButton login;
	LoginView jf;

	public LoginView() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		jf = this;
		username = new HintTextField("  UserName");
		password = new JPasswordField();

		login = new JButton("Login");

		username.setBounds(130, 39, 150, 35);

		password.setBounds(130, 85, 150, 35);

		login.setBounds(130, 164, 150, 40);
		login.addActionListener(new LoginController(this));
		login.addAncestorListener(new RequestFocusListener(false));

		getContentPane().add(username);
		getContentPane().add(password);
		getContentPane().add(login);

		setSize(400, 400);
		getContentPane().setLayout(null);
		setVisible(true);
	}

	public void checkUser(String username, String password) throws ClassNotFoundException, SQLException {

		UserDAOImp userDAO = new UserDAOImp();
		User user = userDAO.checkPassword(username, password);
		if (user != null) {
			if (user.getPosition().equals("admin")) {
				MainFrameView mf = new MainFrameView();
				mf.userCheck = true;
				jf.dispose();
			} else {
				MainFrameView mf = new MainFrameView();
				mf.userCheck = false;
				jf.dispose();
			}
		} else {
			JOptionPane.showMessageDialog(null, "Username or password wrong \n" + "", "Warning",
					JOptionPane.WARNING_MESSAGE);
		}
	}

	public JTextField getUserNameTf() {
		return username;
	}

	public JPasswordField getPassTf() {
		return password;
	}

	public JButton getLogin() {
		return login;
	}

	public static void main(String[] args) {

		new LoginView();
	}
}

class RequestFocusListener implements AncestorListener {
	private boolean removeListener;

	public RequestFocusListener() {
		this(true);
	}

	public RequestFocusListener(boolean removeListener) {
		this.removeListener = removeListener;
	}

	@Override
	public void ancestorAdded(AncestorEvent e) {
		JComponent component = e.getComponent();
		component.requestFocusInWindow();

		if (removeListener)
			component.removeAncestorListener(this);
	}

	@Override
	public void ancestorMoved(AncestorEvent e) {
	}

	@Override
	public void ancestorRemoved(AncestorEvent e) {
	}
}
