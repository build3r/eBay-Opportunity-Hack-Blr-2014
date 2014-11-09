package com.paypal.oh.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Component;

import com.paypal.oh.dos.LostReport;


@Component
public class LostReportDao {
	
	@Autowired
	private HibernateTemplate template;

	public void setTemplate(HibernateTemplate template) {
		this.template = template;
	}
	
	public void saveReport(LostReport e){
		template.save(e);
	}
	
	public void updateReport(LostReport e){
		template.update(e);
	}
	
	public void deleteReport(LostReport e){
		template.delete(e);
	}
	
	public List<LostReport> getLostReport(LostReport e) {
		return template.findByExample(e);
	}
}
