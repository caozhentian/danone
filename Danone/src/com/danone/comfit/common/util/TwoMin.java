package com.danone.comfit.common.util;

/**
 * @author ztcao Two min for exmaple ( startMin = 30 , endMin 60)
 *
 */
public interface TwoMin {

//	private int startMin ;
//	
//	private int endMin   ;
	
	/**
	 * @param otherTwoMin
	 * @return true : intersect false: no intersect
	 */
//	boolean isJudgeIntersect(TwoMin otherTwoMin){
//		if(endMin < otherTwoMin.startMin){
//			return false ;
//		}
//		if(otherTwoMin.endMin < startMin){
//			return false ;
//		}
//		return true ;
//	}

	boolean isJudgeIntersect(TwoMin otherTwoMin) ;
	
	long     getMax() ;
	
	long     getMin() ;
//	public int getStartMin() {
//		return startMin;
//	}
//
//	public void setStartMin(int startMin) {
//		this.startMin = startMin;
//	}
//
//	public int getEndMin() {
//		return endMin;
//	}
//
//	public void setEndMin(int endMin) {
//		this.endMin = endMin;
//	}
	
	
}
