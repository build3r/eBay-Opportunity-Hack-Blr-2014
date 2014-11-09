package com.paypal.oh.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Component;

import com.paypal.oh.dos.FoundReport;

@Component
public class FoundReportDao {
	
	@Autowired
	private HibernateTemplate template;

	public void setTemplate(HibernateTemplate template) {
		this.template = template;
	}
	
	public void save(FoundReport e){
		template.save(e);
	}
	
	public void update(FoundReport e){
		template.update(e);
	}
	
	public void delete(FoundReport e){
		template.delete(e);
	}
	
	public List<FoundReport> get(FoundReport e) {
		return template.findByExample(e);
	}
}
