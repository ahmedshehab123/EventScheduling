package com.event.timer;

import java.text.ParseException;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

public class RunBefore48HoursAlert extends QuartzJobBean {
	private Before48HoursAlert run;

	
	public void setRun(Before48HoursAlert run) {
		this.run = run;
	}



	protected void executeInternal(JobExecutionContext context)
			throws JobExecutionException {

		try {
			run.sendMail();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}