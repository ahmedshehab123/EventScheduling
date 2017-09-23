package com.event.timer;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

public class RunTicketQuartiz extends QuartzJobBean {
	private TicketQuartiz runTicket;
	
	public void setRunTicket(TicketQuartiz runTicket) {
		this.runTicket = runTicket;
	}


	@Override
	protected void executeInternal(JobExecutionContext arg0) throws JobExecutionException {
		// TODO Auto-generated method stub
		runTicket.sendTicket();
		
	}

}
