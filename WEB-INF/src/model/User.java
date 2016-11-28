package model;

public class User {

	private int rid;
	private String loginName;;
	private String passWord;
	private String name;
	private String email;

	public int getRid() {
		return rid;
	}
	public String getLoginName() {
		return loginName;
	}
	public String getPassWord() {
		return passWord;
	}
	public String getName() {
		return name;
	}
	public String getEmail() {
		return email;
	}

	public void setRid(int rid) {
		this.rid = rid;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setEmail(String email) {
		this.email = email;
	}

}
