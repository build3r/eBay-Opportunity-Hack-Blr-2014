package com.paypal.oh.notification;

import java.util.List;

/** #########################################
 * Created by ROCKSTARS of Compliance!!!! 
    #########################################
  */

import com.paypal.oh.dos.FoundReport;
import com.paypal.oh.dos.LostReport;

public class NotificationRecord {

	private LostReport lostReport;
	private List<FoundReport> foundReports;

	public LostReport getLostReport() {
		return lostReport;
	}

	public void setLostReport(LostReport lostReport) {
		this.lostReport = lostReport;
	}

	public List<FoundReport> getFoundReports() {
		return foundReports;
	}

	public void setFoundReports(List<FoundReport> foundReports) {
		this.foundReports = foundReports;
	}

}
