import java.io.Serializable;

public class User implements Serializable{
	private String name;
	private String password;
	private int status;//1为admin，0为guest
	
	User(String name, String password, int status) {
		this.setName(name);
		this.setPassword(password);
		this.setStatus(status);
	}
	User(){
		;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
}
