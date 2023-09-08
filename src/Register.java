import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class Register extends JFrame {

	private JPanel contentPane;
	private JTextField accountfield;
	private JTextField passwordfield;
	private static DataProcessing process = new DataProcessing();
	private String account;
	private String password;
	private MainFrame frame = new MainFrame();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Register frame = new Register();
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
	public Register() {
		super("Check in");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 676, 443);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Account:");
		lblNewLabel.setFont(new Font("宋体", Font.PLAIN, 25));
		lblNewLabel.setBounds(94, 48, 117, 39);
		contentPane.add(lblNewLabel);
		
		accountfield = new JTextField();
		accountfield.setBounds(142, 108, 250, 39);
		contentPane.add(accountfield);
		accountfield.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Password:");
		lblNewLabel_1.setFont(new Font("宋体", Font.PLAIN, 25));
		lblNewLabel_1.setBounds(94, 192, 117, 39);
		contentPane.add(lblNewLabel_1);
		
		passwordfield = new JTextField();
		passwordfield.setBounds(142, 249, 250, 39);
		contentPane.add(passwordfield);
		passwordfield.setColumns(10);
		
		JButton confirmbtn = new JButton("Confirm");
		confirmbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					CheckinListener(e);
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (InstantiationException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IllegalAccessException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		confirmbtn.setFont(new Font("宋体", Font.PLAIN, 25));
		confirmbtn.setBounds(227, 330, 132, 55);
		contentPane.add(confirmbtn);
	}
	
	
	private void CheckinListener(ActionEvent evt) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
		String pwd = passwordfield.getText();
		String acc = accountfield.getText();
		System.out.println(acc);
		System.out.println(pwd);
		ArrayList<String> data = new ArrayList<>();
		data = process.checkin(acc, pwd);
		if(data.size()!=0) {
			account = data.get(0);
			password = data.get(1);
			System.out.println(account);
			System.out.println(password);
			if(password.equals(pwd)&&account.equals(acc)) {
				frame.setVisible(true);
				JOptionPane.showMessageDialog(null, "Checked in!", "提示", JOptionPane.INFORMATION_MESSAGE);
			}else {
				JOptionPane.showMessageDialog(null, "pwd doesn't match account", "提示", JOptionPane.ERROR_MESSAGE);
			}
			
		}else {
			JOptionPane.showMessageDialog(null, "Wrong pwd or account", "提示", JOptionPane.ERROR_MESSAGE);
			this.dispose();
		}
	}
}
