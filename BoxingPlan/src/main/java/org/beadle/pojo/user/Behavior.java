package org.beadle.pojo.user;

import java.sql.Date;

import org.beadle.framework.annotation.Column;
import org.beadle.framework.annotation.Entity;
import org.beadle.pojo.module.Module;

@Entity("tb_behavior")
public class Behavior {
	private int id;
	private int count;
	private Date recentTime;
	private User user;
	private Module module;
	
	@Column("recent_time")
	public Date getRecentTime() {
		return recentTime;
	}
	public void setRecentTime(Date recentTime) {
		this.recentTime = recentTime;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Module getModule() {
		return module;
	}
	public void setModule(Module module) {
		this.module = module;
	}
	@Column("count")
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	
	
}
