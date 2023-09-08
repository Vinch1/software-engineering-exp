import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.FlowLayout;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.Font;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.SystemColor;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class Personnel extends JFrame {

	private JPanel contentPane;
	private JTable usertable;
	private DataProcessing process = new DataProcessing();
	private static Login login = new Login();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Personnel frame = new Personnel();
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
	public Personnel() {
		super("Personnel list");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 400, 520);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(76, 81, 221, 253);
		contentPane.add(scrollPane);
		//usertable.setFont(new Font("宋体", Font.PLAIN, 20));
		ArrayList<ArrayList<String>> usersdata = new ArrayList<>();
		try {
			usersdata = process.readUserDataFromDB();
		} catch (Exception e) {
			System.out.println("读取发生错误");
		}
		
		inittable();
		
		scrollPane.setViewportView(usertable);
		scrollPane.setColumnHeaderView(usertable);
		
		JLabel lblNewLabel = new JLabel("Users");
		lblNewLabel.setForeground(SystemColor.textHighlight);
		lblNewLabel.setFont(new Font("华文细黑", Font.PLAIN, 20));
		lblNewLabel.setBounds(97, 48, 58, 15);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Roles");
		lblNewLabel_1.setForeground(SystemColor.desktop);
		lblNewLabel_1.setFont(new Font("新細明體-ExtB", Font.PLAIN, 20));
		lblNewLabel_1.setBounds(213, 47, 65, 24);
		contentPane.add(lblNewLabel_1);
		
		JButton btnNewButton = new JButton("Delete");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				User user = login.tran();
				DelUsesrActionPerformed(user.getName(),e);
			}
		});
		btnNewButton.setFont(new Font("宋体", Font.PLAIN, 20));
		btnNewButton.setBounds(76, 372, 104, 33);
		contentPane.add(btnNewButton);
		
		JButton returnbtn = new JButton("Back");
		returnbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ReturnActionPerformed(e);
			}
		});
		returnbtn.setFont(new Font("宋体", Font.PLAIN, 20));
		returnbtn.setBounds(190, 372, 107, 33);
		contentPane.add(returnbtn);
	}
	
	
	private void DelUsesrActionPerformed (String account, ActionEvent evt) {
		int selectedrow = usertable.getSelectedRow();
		
		if(selectedrow == -1) {
			JOptionPane.showMessageDialog(null, "None chosen user");
			return;
		} else {
			//get user account
			String useraccount = (String) usertable.getValueAt(selectedrow, 0);
			// if chosse a blank
			if (useraccount.equals(null)) {
				return;
			}
			//if chose yourself
			if (account.equals(useraccount)) {
				JOptionPane.showMessageDialog(null, "Can't delete yourself!");
				return;
			}
			
			int value = JOptionPane.showConfirmDialog(null, "for real?","Delinterface",2);
			//yes=0 no=1
			if(value == 0) {
				try {
					if (process.deleteuser(useraccount)) {
						inittable();
						JOptionPane.showMessageDialog(null, "delete successfully");
						return;
					}else {
						JOptionPane.showMessageDialog(null, "failed..");
						return;
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	
	
	
	public void inittable() {
		ArrayList<ArrayList<String>> usersdata = new ArrayList<>();
		try {
			usersdata = process.readUserDataFromDB();
		} catch (Exception e) {
			System.out.println("读取发生错误");
		}
		
		ArrayList<String> data = new ArrayList<>();
		// 表格数据
		String[][] rowData = new String[16][2];
		try {
			int row = 0;
			for(int i = 0; i < usersdata.size(); i++) {
				data = usersdata.get(i);
				rowData[i][0] = data.get(0);
				if(data.get(2).equals("1")) {
					rowData[i][1] = "administrator";
				}else {
					rowData[i][1] = "guest";
				}
			}
		} catch(Exception e) {
			System.out.println("赋值发生错误");
		}
		String head[] = {"User","Identity"};
		usertable = new JTable(rowData, head);
		//initTable();
		
	}
	
	// 返回
	private void ReturnActionPerformed(ActionEvent evt) {
		this.dispose();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
