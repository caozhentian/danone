/**
 * 
 */
package com.danone.comfit.common.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author ztcao Intersect operation
 *
 */
public class NumberIntersectUtil {

	/**
	 * @param twomin
	 * @param twomins
	 * @return null: no Intersect 
	 */
	public static final List<? extends TwoMin> isIntersect(TwoMin twomin , List<? extends TwoMin> twomins){
		if(twomin == null || twomins.size() == 0){
			return Collections.emptyList() ;
		}
		List<TwoMin> twoMins = new ArrayList<TwoMin>() ;
		for(TwoMin otherTwoMin: twomins){
			if(twomin.isJudgeIntersect(otherTwoMin)){
				twoMins.add(otherTwoMin) ;
			}
		}
		
		return twoMins ;
	}
}
