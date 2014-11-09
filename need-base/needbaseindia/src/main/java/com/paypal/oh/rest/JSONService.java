package com.paypal.oh.rest;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.paypal.oh.dao.FoundReportDao;
import com.paypal.oh.dao.LostReportDao;
import com.paypal.oh.dao.MatchRecordDao;
import com.paypal.oh.dos.FoundReport;
import com.paypal.oh.dos.LostReport;
import com.paypal.oh.dos.MatchRecord;
import com.paypal.oh.notification.NotificationRecord;
import com.paypal.oh.utils.SendMail;


@Controller
@RequestMapping(value = "/nbi/common")
public class JSONService {
	
	@Autowired
	private LostReportDao lostReportDao;

	@Autowired
	private FoundReportDao foundReportDao;
	
	@Autowired
	private MatchRecordDao matchRecordDao;
	
	@RequestMapping(value = "/lost/report" , method = RequestMethod.POST)
	@Consumes(MediaType.APPLICATION_JSON)
	public @ResponseBody String persistLostCase(ModelMap modelMap, @RequestBody LostReport lostReport) {
		
		lostReport.setCaseId(UUID.randomUUID().toString());
		lostReportDao.saveReport(lostReport);
		String result = "Lost case registered " + lostReport.toString() ;
		return result;
	}

	@RequestMapping(value = "/lost/triggerAnalyzer", method = RequestMethod.GET)
	public @ResponseBody
	String triggerAnalyzer() {
		LostReport lostReport = new LostReport();
		List<LostReport> lostReports = lostReportDao.getLostReport(lostReport);
        FoundReport foundReport = new FoundReport();
        foundReport.setSubjectGender(lostReport.getSubjectGender());
        foundReport.setSubjectLanguage(lostReport.getSubjectLanguage());
        List<FoundReport>  foundReports = foundReportDao.get(foundReport);
        List<MatchRecord>  matchRecords = match(lostReports, foundReports);
        for( MatchRecord m : matchRecords){
        	matchRecordDao.saveRecord(m);
        }
        NotificationRecord notificationRecord = new NotificationRecord();
        notificationRecord.setLostReport(lostReports.get(0));
        notificationRecord.setFoundReports(foundReports);
        SendMail.EmailNotifier("chabhishek123@gmail.com", notificationRecord);
		return "Successfuly triggered";
	}
	
	private List<MatchRecord> match(List<LostReport> lostReports,
			List<FoundReport> foundReports) {
		List<MatchRecord> matchRecords = new ArrayList<MatchRecord>();
		MatchRecord m = new MatchRecord();
		m.setFoundCaseId("b25a5ea5-d3a4-46d1-8eaf-630a19c2cf6c");
		m.setLostCaseId("f236f9d7-8678-42a2-9167-bd2e0ae14536");
		matchRecords.add(m);
		return matchRecords;
	}

	@RequestMapping(value = "/found/report" , method = RequestMethod.POST)
	@Consumes(MediaType.APPLICATION_JSON)
	public @ResponseBody String persistFoundCase(ModelMap modelMap, @RequestBody FoundReport foundReport) {
		
		foundReport.setCaseId(UUID.randomUUID().toString());
		foundReportDao.save(foundReport);
		String result = "Lost case registered " + foundReport.toString() ;
		return result;
	}
	
	@RequestMapping(value = "/search" , method = RequestMethod.POST)
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public @ResponseBody List<FoundReport> search(@RequestBody FoundReport foundReport) {
		return foundReportDao.get(foundReport);
	}
	
}