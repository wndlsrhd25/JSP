package co.dev.vo;

import lombok.Getter;
import lombok.Setter;


public class MemberVO {
	
	private String id;
	private String passwd;
	private String name;
	private String mail;
	
	@Override
	public String toString() {
		return "MemberVO id=" + id + ", passwd=" + passwd + ", name=" + name + ", mail=" + mail ;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPasswd() {
		return passwd;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	
}
