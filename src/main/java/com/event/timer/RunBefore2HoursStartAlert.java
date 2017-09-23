package com.event.timer;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

public class RunBefore2HoursStartAlert extends QuartzJobBean {
    private Before2HoursStartAlert be2hours;
  
	public void setBe2hours(Before2HoursStartAlert be2hours) {
		this.be2hours = be2hours;
	}

	@Override
	protected void executeInternal(JobExecutionContext arg0) throws JobExecutionException {
		// TODO Auto-generated method stub
		be2hours.before2hours();
		
	}

}
