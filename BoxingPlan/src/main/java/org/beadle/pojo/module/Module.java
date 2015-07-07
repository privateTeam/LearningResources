package org.beadle.pojo.module;

import org.beadle.framework.annotation.Column;
import org.beadle.framework.annotation.Entity;

@Entity("tb_module")
public class Module {
	private int id;
	private String funcUrl;
	private String imgUrl;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@Column("func_url")
	public String getFuncUrl() {
		return funcUrl;
	}
	public void setFuncUrl(String funcUrl) {
		this.funcUrl = funcUrl;
	}
	
	@Column("img_url")
	public String getImgUrl() {
		return imgUrl;
	}
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	
	
}
