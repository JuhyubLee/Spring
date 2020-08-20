package com.coderby.myapp.hr.model;

import lombok.Data;

@Data
public class JobVO {

	private String jobId;
	private String jobTitle;
	
	public String getJobId() {
		return jobId;
	}
	public void setJobId(String jobId) {
		this.jobId = jobId;
	}
	public String getJobTitle() {
		return jobTitle;
	}
	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}
	
}
