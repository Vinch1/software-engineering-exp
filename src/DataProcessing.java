import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DataProcessing {
	

	public void saveDataToDB (User user) {
		DBUtil db = new DBUtil();
		try {
			db.getConnection();
			String sql = "insert ignore into userdata(account,password,status) values(?,?,?)";
			Object[] param = new Object[] {
					user.getName(),user.getPassword(),user.getStatus()
			};
			db.executeUpdate(sql, param);
			db.closeAll();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<ArrayList<String>> readUserDataFromDB() {
		ArrayList<ArrayList<String>> Usersdata = new ArrayList<>();
		DBUtil db = new DBUtil();
		try {
			db.getConnection();
			String sql = "SELECT account,password,status from userdata";
			ResultSet rs = db.executeQuery(sql, null);
			while (rs.next()) {
				ArrayList<String> data = new ArrayList<>();
				data.add(rs.getString(1));data.add(rs.getString(2));data.add(rs.getString(3));//可能会有问题
				Usersdata.add(data);
			}
			
			db.closeAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Usersdata;
	}
	
	public boolean deleteuser(String account) {
		DBUtil db = new DBUtil();
		try {
			Connection conn = db.getConnection();
			String sql = "delete from userdata where account like '%s' ";
			Statement stmt=conn.createStatement();
			stmt.executeUpdate(String.format(sql, account));
			conn.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public static ArrayList<String> checkin(String accountt, String passwordd) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
		ArrayList<String> data = new ArrayList<String>();
		DBUtil db = new DBUtil();
		try {
			db.getConnection();
			String sql = "select account,password from userdata where account like '%s' && password like '%s' ";//
			ResultSet rs = db.executeQuery(String.format(sql, accountt,passwordd), null);
			while (rs.next()) {
				data.add(rs.getString(1));data.add(rs.getString(2));
			}
			db.closeAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return data;
	}
	
	// 前面需要if判断是否旧密码输入一致
	public static void modifydata(String account, String newpassword) {
		DBUtil db = new DBUtil();
		try {
			Connection conn = db.getConnection();
			String sql = "update userdata set password = '%s' where account like '%s' ";
			//System.out.println(String.format(sql, newpassword, account));
			Statement stmt=conn.createStatement();//创建一个Statement对象
			stmt.executeUpdate(String.format(sql, newpassword, account));
			conn.close();
			} catch (Exception e) {
			e.printStackTrace();
		}
	}


	
	
	
	
}
