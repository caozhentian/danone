/**
 * 
 */
package com.threeti.danone.common.bean;

import java.util.Date;

/**
 * @author ztcao baseclass declare common filded and meathod(基类 定义了共同的字段和方法)
 *
 */
public class Diary {
	
	public static int  OPP_NORMAL    = 1   ;

	public static int  OPP_ADD       = 2   ;
	
	public static int  OP_MODIFY     = 3   ;
	
	public static int  OPP_DELETE    = 4   ;
	
	//local id 
	protected String appId       ;
	
	//server id
	protected String serverId    ;
	
	//infant fk(婴儿的外键)
	protected String infantId  ;
	
	/**
	 * 把小时 分 秒 毫秒 设置为0的时间
	 */
	protected Date   ddat      ;
	
	/**
	 * OPP_Add OP_Delete OP_Modify OP_Normal
	 */
	protected int status ;
	
	protected String modifyReason ;
	
	protected String deleteReason ;
	
	public boolean isOffline(){
		if(serverId == null || serverId.length() == 0){
			return true ;
		}
		return false ;
	}
	
	public String getServerId() {
		return serverId;
	}


	public void setServerId(String serverId) {
		this.serverId = serverId;
	}

	public String getAppId() {
		return appId;
	}


	public void setAppId(String appId) {
		this.appId = appId;
	}


	public String getInfantId() {
		return infantId;
	}


	public void setInfantId(String infantId) {
		this.infantId = infantId;
	}


	public Date getDdat() {
		return ddat;
	}

	public void setDdat(Date ddat) {
		this.ddat = ddat;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getModifyReason() {
		return modifyReason;
	}

	public void setModifyReason(String modifyReason) {
		this.modifyReason = modifyReason;
	}

	public String getDeleteReason() {
		return deleteReason;
	}

	public void setDeleteReason(String deleteReason) {
		this.deleteReason = deleteReason;
	} 
	
	
}
