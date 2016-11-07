package com.danone.comfit.service;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorServiceFactory {

	static ExecutorService executorService = Executors.newSingleThreadExecutor() ; 
	
	public static final ExecutorService getExecutorService(){
		return executorService ;
	}
}
