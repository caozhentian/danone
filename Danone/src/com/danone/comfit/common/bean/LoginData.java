package com.danone.comfit.common.bean;

import java.io.Serializable;

public class LoginData implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3250970624307726173L;
	/**
	 * 
	 */

	public String id;
	public String useFlg;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUseFlg() {
		return useFlg;
	}
	public void setUseFlg(String useFlg) {
		this.useFlg = useFlg;
	}
	 
	 
	
	
}
