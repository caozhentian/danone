/**
 * 
 */
package com.threeti.danone.common.bean;

/**
 * @author ztcao from resposity layer event
 *
 */
public class DiaryResposityEvent {

	/**
	 * local save or delete success  
	 */
	public static int EVENT_DIARY_LOCAL_OPP_SUCCESS  = 0x01 ;
	/**
	 * local save or delete fail  
	 */
	public static int EVENT_DIARY_LOCAL_OPP_FAIL     = 0x02 ;
	
	/**
	 * sync success
	 */
	public static int EVENT_DIARY_SYNC_OPP_SUCCESS   = 0x03 ;
	
	/**
	 * sync fail
	 */
	public static int EVENT_DIARY_SYNC_OPP_FAIL      = 0x04 ;
	
	private int eventType ;

	
	public int getEventType() {
		return eventType;
	}

	public void setEventType(int eventType) {
		this.eventType = eventType;
	}

	public DiaryResposityEvent(int eventType) {
		super();
		this.eventType = eventType;
	} 
	
	
}
