package com.threeti.danone.common.bean;


public class UpProgress {

	private long progress;
	private long totalLen;
	private boolean hasDone;
	private long todo;
	
	

	public UpProgress(long progress, long totalLen, boolean hasDone, long todo) {

		this.progress = progress;
		this.todo = todo;
		this.hasDone = hasDone;
		this.totalLen = totalLen;
	}

	public long getProgress() {
		return progress;
	}

	public void setProgress(long progress) {
		this.progress = progress;
	}

	public long getTotalLen() {
		return totalLen;
	}

	public void setTotalLen(long totalLen) {
		this.totalLen = totalLen;
	}

	public boolean isHasDone() {
		return hasDone;
	}

	public void setHasDone(boolean hasDone) {
		this.hasDone = hasDone;
	}

	public long getTodo() {
		return todo;
	}

	public void setTodo(long todo) {
		this.todo = todo;
	}

}
