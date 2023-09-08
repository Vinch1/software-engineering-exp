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
import java.awt.event.ActionEvent;

public class Modify extends JFrame {

	private JPanel contentPane;
	private JTextField origpassword;
	private JTextField newpassword;
	private String originpw;
	private String newpw;
	private static DataProcessing process = new DataProcessing();
	private Login login = new Login();
	private  User user = new User();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Modify frame = new Modify();
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
	
	public Modify() {
		super("modify ur password");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 540, 298);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Original password:");
		lblNewLabel.setFont(new Font("宋体", Font.PLAIN, 20));
		lblNewLabel.setBounds(46, 10, 198, 49);
		contentPane.add(lblNewLabel);
		
		origpassword = new JTextField();
		origpassword.setBounds(101, 61, 284, 32);
		contentPane.add(origpassword);
		origpassword.setColumns(10);
		
		
		JLabel lblNewLabel_1 = new JLabel("New password:");
		lblNewLabel_1.setFont(new Font("宋体", Font.PLAIN, 20));
		lblNewLabel_1.setBounds(46, 103, 185, 32);
		contentPane.add(lblNewLabel_1);
		
		
		newpassword = new JTextField();
		newpassword.setBounds(101, 145, 284, 32);
		contentPane.add(newpassword);
		newpassword.setColumns(10);
		
		user = login.tran();
				
		JButton modibtn = new JButton("Confirm");
		modibtn.addActionListener(new modiListener());
		modibtn.setFont(new Font("宋体", Font.PLAIN, 20));
		modibtn.setBounds(190, 208, 115, 32);
		contentPane.add(modibtn);
	}

	
	private class modiListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			try {
				//获取输入的原始密码
				originpw = origpassword.getText();
				//获取新输入的密码
				newpw = newpassword.getText();
				//System.out.println("新密码：" + newpw);
				String passw = user.getPassword();
				String name = user.getName();
//				System.out.println(originpw);
//				System.out.println(passw);
				if (originpw.equals(passw)) {
					process.modifydata(name ,newpw);
					MainFrame frame = new MainFrame();
					frame.setVisible(true);
					JOptionPane.showMessageDialog(null, "You changed your password", "提示", JOptionPane.INFORMATION_MESSAGE);
					
				}else {
					MainFrame frame = new MainFrame();
					frame.setVisible(true);
					JOptionPane.showMessageDialog(null, "Wrong password...", "提示", JOptionPane.ERROR_MESSAGE);
				}
			}catch(Exception e1) {
				;
			}
			
		}
	}
	
	
}
