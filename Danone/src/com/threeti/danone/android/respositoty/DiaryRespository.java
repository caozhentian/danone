/**
 * 
 */
package com.threeti.danone.android.respositoty;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import com.threeti.danone.common.bean.DiaryResposityEvent;
import com.threeti.danone.common.model.Diary;
import com.threeti.danone.common.util.NetUtil;

import de.greenrobot.event.EventBus;

/**
 * @author ztcao
 *
 */
public abstract class DiaryRespository {

	protected abstract boolean localDelete(Diary diary)   ;
	
	protected abstract boolean localDelete(List<? extends Diary> diaries)    ;
	
	protected abstract boolean localUpdate(Diary diary)   ;
	
	protected abstract boolean localUpdate(List<? extends Diary> diaries)    ;
	
	protected abstract boolean loacalInsert(Diary diary)  ;
	
	protected  boolean loacalInsert(List<? extends Diary> diaries){
		if(diaries == null || diaries.size() == 0 ){
			return true ;
		}
		for(Diary diary : diaries){
			String uuid = UUID.randomUUID().toString() ;
			diary.setAppId(uuid) ;
		}
		return true ;
	}
	
	/**(date - beforeDays to date)
	 * @param date
	 * @param beforeDays 
	 * @return
	 */
	protected abstract List<? extends Diary> loacalQuery(Date date , int beforeDays) ;
	
	protected abstract List<? extends Diary> queryNeedDeleteDiary()        ;
	
	/**query between date - beforeDays and date
	 * @param date
	 * @param beforeDays
	 * @return
	 */
	protected abstract List<Diary> serverQuery(Date date , int beforeDays) ;
	
	
	//************************syn relate code  start 
	/**
	 * @param       diary
	 * @return true must set diray serverId value(成功，必须设置服务器返回的Id)
	 */
	protected abstract boolean sync(Diary diary)                 ;
	
	protected abstract List<? extends Diary> getNeedSynDiary()                         ;
	protected abstract List<? extends Diary> sync(List<? extends Diary> diary)         ;
	
	public  void sync(){ //batch sync framework code , for work thread execute
		List<? extends Diary> diaries         = getNeedSynDiary()       ;
		List<? extends Diary> resposeDiaries  = sync(diaries)           ;
		List<Diary> deleteDiaries   = new ArrayList<Diary>()  ;
		List<Diary> updateDiaries   = new ArrayList<Diary>()  ;
		
		for(Diary resposeDiary :resposeDiaries){
			
			if(false){ // syn failed
				
			}
			else{//sync success
				if(resposeDiary.getStatus() == Diary.OPP_DELETE){
					deleteDiaries.add(resposeDiary) ;
				}
				else{
					updateDiaries.add(resposeDiary) ;
				}
			}
				
		}
		
		//execute batch delete
		localDelete(deleteDiaries) ;
		//execute batch update
		localUpdate(updateDiaries) ;
		
	}
	
	
	
	//************************syn relate code  end 
	public void create(Diary diary){
		diary.setStatus(Diary.OPP_ADD) ;
		String uuid = UUID.randomUUID().toString() ;
		diary.setAppId(uuid) ;
		boolean success = loacalInsert(diary) ;
		
		if(success == false ){
			//notify UI 
			postDiaryEvent(DiaryResposityEvent.EVENT_DIARY_LOCAL_OPP_FAIL);
			return ; 
		}
		
		
		postDiaryEvent(DiaryResposityEvent.EVENT_DIARY_LOCAL_OPP_SUCCESS) ;
//		if(NetUtil.isConnected() == false){
//			//disConneted , notify UI
//			loger.debug("net disconneted") ;
//			postDiaryEvent(DiaryResposityEvent.EVENT_DIARY_LOCAL_OPP_SUCCESS) ;
//			return ;
//		}
//		
//		boolean syncSuccess =  sync(diary) ;
//		
//		if(syncSuccess){
//			diary.setStatus(Diary.OPP_NORMAL) ;
//			loacalInsert(diary) ;
//			//notify UI
//			postDiaryEvent(DiaryResposityEvent.EVENT_DIARY_SYNC_OPP_SUCCESS) ;
//		}
//		else{//sync failed
//			//notify UI
//			postDiaryEvent(DiaryResposityEvent.EVENT_DIARY_SYNC_OPP_FAIL)     ;
//		}
	}
	
	public void modify(Diary diary){
		
		if(diary.isOffline()){
			diary.setStatus(Diary.OPP_ADD)      ;
		}
		else{
			diary.setStatus(Diary.OP_MODIFY)      ;
		}
			
		
		boolean success = localUpdate(diary)  ;
		
		if(success == false ){//update failed
			//notify UI eventbus
			postDiaryEvent(DiaryResposityEvent.EVENT_DIARY_LOCAL_OPP_FAIL);
			return ; 
		}
		
		if(NetUtil.isConnected() == false){
			//notify ui
			postDiaryEvent(DiaryResposityEvent.EVENT_DIARY_SYNC_OPP_SUCCESS) ;
			return ;
		}
		
		boolean syncSuccess =  sync(diary) ;
		
		if(syncSuccess){//sync success 
			diary.setStatus(Diary.OPP_NORMAL) ;
			localUpdate(diary) ;
			//notify UI
			postDiaryEvent(DiaryResposityEvent.EVENT_DIARY_SYNC_OPP_SUCCESS) ;
		}
		else{//sync failed  ,notify ui
			//notify UI
			postDiaryEvent(DiaryResposityEvent.EVENT_DIARY_SYNC_OPP_FAIL)     ;
		}
	}
	
	public void delete(Diary diary){
		
		if(diary.isOffline()){
			boolean deleteSuccess = localDelete(diary) ;
			if(deleteSuccess){//notify ui 
				postDiaryEvent(DiaryResposityEvent.EVENT_DIARY_SYNC_OPP_SUCCESS) ;
			}
			else{//notify ui
				postDiaryEvent(DiaryResposityEvent.EVENT_DIARY_SYNC_OPP_FAIL)    ;
			}
			return ;
		}
		
		//deal online diary
		diary.setStatus(Diary.OPP_DELETE) ;
		boolean success = localUpdate(diary) ;
		
		if(success == false ){
			//notify UI eventbus
			postDiaryEvent(DiaryResposityEvent.EVENT_DIARY_LOCAL_OPP_FAIL);
			return ; 
		}
		
		if(NetUtil.isConnected() == false){
			//notify ui
			postDiaryEvent(DiaryResposityEvent.EVENT_DIARY_SYNC_OPP_SUCCESS) ;
			return ;
		}
		
		boolean syncSuccess =  sync(diary) ;
		
		if(syncSuccess){ //sync success 
			localDelete(diary) ;
			postDiaryEvent(DiaryResposityEvent.EVENT_DIARY_SYNC_OPP_SUCCESS) ;
		}
		else{ //sync failed  ,notify ui
			postDiaryEvent(DiaryResposityEvent.EVENT_DIARY_SYNC_OPP_FAIL)     ;
		}
	}
	
	public List<? extends Diary> query(Date date , int beforeDays){
		List<? extends Diary> diarys = loacalQuery(date , beforeDays) ;
		if(diarys == null || diarys.size() == 0){
			diarys = serverQuery(date , beforeDays) ;
			//add app storage
			loacalInsert(diarys) ;
		}
		//delete old diaries
		List<? extends Diary> diaries = queryNeedDeleteDiary() ;
		localDelete(diaries) ;
		return diarys ;
	}
	
	  
	protected void postDiaryEvent(int eventType){
		EventBus.getDefault().post(new DiaryResposityEvent(eventType)) ;
	}

	
}
