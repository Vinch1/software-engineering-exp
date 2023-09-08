import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainFrame extends JFrame {

	private JPanel contentPane;
	private static DataProcessing process = new DataProcessing();
	private static Login login = new Login();
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame();
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
	public MainFrame() {
		super("~Welcome~");
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Welcome");
		lblNewLabel.setForeground(SystemColor.activeCaption);
		lblNewLabel.setBackground(Color.WHITE);
		lblNewLabel.setFont(new Font("华文新魏", Font.PLAIN, 50));
		lblNewLabel.setBounds(112, 52, 218, 65);
		contentPane.add(lblNewLabel);
		
		JButton Modifybtn = new JButton("Modify Password");
		Modifybtn.addActionListener(new ModifyListener());
		Modifybtn.setFont(new Font("宋体", Font.PLAIN, 20));
		Modifybtn.setBounds(112, 220, 218, 43);
		contentPane.add(Modifybtn);
		
		JButton showbtn = new JButton("Show Others");
		showbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Personnel person = new Personnel();
				person.setVisible(true);
			}
		});
		showbtn.setFont(new Font("宋体", Font.PLAIN, 20));
		showbtn.setBounds(515, 222, 165, 41);
		contentPane.add(showbtn);
		
		JButton logoutbtn = new JButton("LogOut");
		logoutbtn.setForeground(SystemColor.desktop);
		logoutbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				login.setVisible(true);
				ReturnActionPerformed(e);
			}
		});
		logoutbtn.setFont(new Font("宋体", Font.PLAIN, 25));
		logoutbtn.setBounds(335, 341, 141, 56);
		contentPane.add(logoutbtn);
	}


	private class ModifyListener implements ActionListener {
		// 数据保存的事件处理方法
		public void actionPerformed(ActionEvent e) {
			Modify modiframe = new Modify();
			modiframe.setVisible(true);
		}
	}
	// 返回
		private void ReturnActionPerformed(ActionEvent evt) {
			this.dispose();
		}
}
