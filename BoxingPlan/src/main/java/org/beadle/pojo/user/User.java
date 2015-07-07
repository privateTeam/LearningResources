package org.beadle.pojo.user;

import java.util.HashSet;
import java.util.Set;

import org.beadle.framework.annotation.Column;
import org.beadle.framework.annotation.Entity;

@Entity("tb_user")
public class User {
	private int id;
	private String userName;
	private String password;
	private Set<Behavior> behaviors = new HashSet<Behavior>();
	
	@Column("user_name")
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	@Column("password")
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", userName=" + userName + ", password="
				+ password + "]";
	}
	
}
