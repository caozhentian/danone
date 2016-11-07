/**
 * 
 */
package com.danone.comfit.net;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;

import com.danone.comfit.common.bean.BaseModel;
import com.danone.comfit.common.bean.TimeSpent;

/**
 * @author ztcao 统计的网络访问api
 *
 */
public interface StatisticsApiService {
	Call<BaseModel<List<TimeSpent>>> sync(@Body List<TimeSpent> timeSpentes) ;
}
