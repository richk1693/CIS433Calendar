import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class login extends JFrame {
	public static void main(String[] args) {
		login FrameTable = new login();
	}

	JButton blogin = new JButton("Login");
	JPanel panel = new JPanel();
	JTextField txuser = new JTextField(15);
	JPasswordField pass = new JPasswordField(15);

	login() {
		super("Login Autentification");
		setSize(300, 200);
		setLocation(500, 280);
		panel.setLayout(null);

		txuser.setBounds(70, 30, 150, 20);
		pass.setBounds(70, 65, 150, 20);
		blogin.setBounds(110, 100, 80, 20);

		panel.add(blogin);
		panel.add(txuser);
		panel.add(pass);

		getContentPane().add(panel);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		actionlogin();
	}

	public void actionlogin() {
		blogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				String puname = txuser.getText();
				String ppaswd = pass.getText();
				
				//TODO: Update this logic to use a DB / File instead of hard coded login
				if (puname.equals("test") && ppaswd.equals("12345")) {
					CalendarFrame regFace = new CalendarFrame();
					regFace.setVisible(true);
					dispose();
				} else {

					JOptionPane.showMessageDialog(null,"Wrong Password / Username");
					txuser.setText("login");
					pass.setText("password");
					txuser.requestFocus();
				}

			}
		});
	}

}
