package com.paypal.oh.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Component;

import com.paypal.oh.dos.MatchRecord;

@Component
public class MatchRecordDao {
	@Autowired
	private HibernateTemplate template;

	public void setTemplate(HibernateTemplate template) {
		this.template = template;
	}
	
	public void saveRecord(MatchRecord e){
		template.save(e);
	}
	
	public void updateEmployee(MatchRecord e){
		template.update(e);
	}
	
	public void deleteEmployee(MatchRecord e){
		template.delete(e);
	}
	
	public List<MatchRecord> getMatchRecord(MatchRecord e) {
		return template.findByExample(e);
	}
}
