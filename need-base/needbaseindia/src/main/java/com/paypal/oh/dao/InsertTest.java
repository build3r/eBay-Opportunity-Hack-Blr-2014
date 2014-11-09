package com.paypal.oh.dao;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.paypal.oh.dos.FoundReport;

public class InsertTest {
public static void main(String[] args) {
	
	ApplicationContext applicationContext = new FileSystemXmlApplicationContext("C:/abhishek/projects/hack/oh/needbaseindia/needbaseindia/src/main/webapp/WEB-INF/mvc-dispatcher-servlet.xml");
	
	FoundReportDao dao=(FoundReportDao)applicationContext.getBean("foundReportDao");
	FoundReport e = new FoundReport();
	e.setSubjectFirstName("Komal");
	List<FoundReport> x = dao.get(e);
	System.out.println(x.get(0).getCaseId());
}
}
