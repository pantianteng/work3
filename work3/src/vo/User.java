package vo;

public class User {
	private String userName;
	private String chrName;
	private String mail;
	private String province;
	private String city;
	private String password;
	private String role;

	public User() {
	}

	public User(String userName, String chrName, String mail, String province, String city, String password, String role) {
		this.userName = userName;
		this.chrName = chrName;
		this.mail = mail;
		this.province = province;
		this.city = city;
		this.password = password;
		this.role = role;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getChrName() {
		return chrName;
	}

	public void setChrName(String chrName) {
		this.chrName = chrName;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
}
 