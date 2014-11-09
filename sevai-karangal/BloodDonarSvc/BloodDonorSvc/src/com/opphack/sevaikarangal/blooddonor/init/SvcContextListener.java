package com.opphack.sevaikarangal.blooddonor.init;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.googlecode.objectify.ObjectifyService;
import com.opphack.sevaikarangal.blooddonor.data.BloodRequest;
import com.opphack.sevaikarangal.blooddonor.data.Donor;

public class SvcContextListener implements ServletContextListener{

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		//Register Objectify Classes for datastore
		ObjectifyService.register(Donor.class);
		ObjectifyService.register(BloodRequest.class);
	}

}
