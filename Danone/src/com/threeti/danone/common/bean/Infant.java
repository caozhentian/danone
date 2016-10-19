package com.threeti.danone.common.bean;

import java.util.Date;

/**
 * @author ztcao infant
 *
 */
public class Infant {

	protected String serverId  ;
	
	protected Date   birthDate ;
	//value in "Male feMale"
	protected String sex       ;
	
	public String getServerId() {
		return serverId;
	}
	
	public void setServerId(String serverId) {
		this.serverId = serverId;
	}
	
	public Date getBirthDate() {
		return birthDate;
	}
	
	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}
	
	public String getSex() {
		return sex;
	}
	
	public void setSex(String sex) {
		this.sex = sex;
	}
	
	
}
