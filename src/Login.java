import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;
import java.awt.Color;

public class Login extends JFrame {

	private JPanel contentPane;
	private JTextField Account_textField;
	public static User user = new User();
	private static DataProcessing process = new DataProcessing();
	private JTextField passwordfield;
	private static Login frame = new Login();
	private static Register regist = new Register();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					//Login frame = new Login();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Login() {
		super("Login");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 500);
		contentPane = new JPanel();
		
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Please Log in");
		lblNewLabel.setForeground(new Color(210, 180, 140));
		lblNewLabel.setFont(new Font("Dialog", Font.PLAIN, 30));
		lblNewLabel.setBounds(90, 24, 183, 63);
		contentPane.add(lblNewLabel);
		
		JLabel account_Label = new JLabel("Account:");
		account_Label.setFont(new Font("宋体", Font.PLAIN, 25));
		account_Label.setBounds(90, 111, 109, 41);
		contentPane.add(account_Label);
		
		Account_textField = new JTextField();
		Account_textField.setBounds(90, 170, 157, 29);
		contentPane.add(Account_textField);
		Account_textField.setColumns(10);
		
		JLabel password_Label = new JLabel("Password:");
		password_Label.setFont(new Font("宋体", Font.PLAIN, 25));
		password_Label.setBounds(90, 236, 126, 29);
		contentPane.add(password_Label);
		
		
		JButton ConfirmButton = new JButton("Confirm");
		ConfirmButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				MainFrame Frame = new MainFrame();
				Frame.setVisible(true);
			}
		});
		ConfirmButton.setFont(new Font("宋体", Font.PLAIN, 20));
		ConfirmButton.setBounds(90, 361, 109, 41);
		contentPane.add(ConfirmButton);
		
		JButton adminButton = new JButton("Sign up as admin");
		adminButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String acc = Account_textField.getText().trim();
				String pas = passwordfield.getText();
				int status = 1;
				user = new User(acc,pas,status);
				try {
					process.saveDataToDB(user);
				} catch(Exception e1) {
					JOptionPane.showMessageDialog(null, "账号重复，请重写注册！", "提示", 
							JOptionPane.ERROR_MESSAGE);
				}
				JOptionPane.showMessageDialog(null, "注册成功！", "提示", 
						JOptionPane.INFORMATION_MESSAGE);
			}
		});
		adminButton.setFont(new Font("宋体", Font.PLAIN, 15));
		adminButton.setBounds(261, 364, 225, 39);
		contentPane.add(adminButton);
		
		JButton btnNewButton = new JButton("Sign up as a guest");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String acc = Account_textField.getText().trim();
				String pas = passwordfield.getText().trim();
				int status = 0;
				user = new User(acc,pas,status);
				try {
					process.saveDataToDB(user);
					JOptionPane.showMessageDialog(null, "注册成功", "提示", 
							JOptionPane.INFORMATION_MESSAGE);
				} catch(Exception e1) {
					JOptionPane.showMessageDialog(null, "注册失败！", "提示", 
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnNewButton.setFont(new Font("宋体", Font.PLAIN, 15));
		btnNewButton.setBounds(535, 363, 183, 39);
		contentPane.add(btnNewButton);
		
		passwordfield = new JTextField();
		passwordfield.setBounds(90, 293, 157, 29);
		contentPane.add(passwordfield);
		passwordfield.setColumns(10);
		
		JButton registbtn = new JButton("Regist");
		registbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CheckinActionPerformed(e);
			}
		});
		registbtn.setFont(new Font("宋体", Font.PLAIN, 20));
		registbtn.setBounds(535, 293, 183, 35);
		contentPane.add(registbtn);
	}
	public User tran () {
		return user;
	}
	private void CheckinActionPerformed(ActionEvent evt) {
		regist.setVisible(true);
		this.dispose();
	}
}
