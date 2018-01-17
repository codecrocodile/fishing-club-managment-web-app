package com.codecrocodile.club.model.catchrecord;

import java.util.List;

public class CatchLogResponse {
	
	private List<CatchLog> catchLogs;
	
	private int totalCount;
	
	
	public CatchLogResponse() {
		super();
	}

	public List<CatchLog> getCatchLogs() {
		return catchLogs;
	}

	public void setCatchLogs(List<CatchLog> catchLogs) {
		this.catchLogs = catchLogs;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

}
