package com.rangde.api;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.UriInfo;

import com.rangde.api.model.Patient;
import com.rangde.api.model.Search;
import com.rangde.dbutil.H2Connector;

@Path("/search")
public class SearchResource {

	@Context
	UriInfo uriInfo;
	@Context
	Request request;
	
	static List<Patient> patients = new ArrayList<Patient>();
	private static Map<String, String> entries = new LinkedHashMap<String, String>();
	private static Map<String, String> captions = new LinkedHashMap<String, String>();
	
	@POST
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public List<Patient> findPatients(Search search) {
		getImages();
		Patient patient1 = null;
		int i=1;
		for(String pName: entries.keySet()){
			patient1 = new Patient();
			patient1.setImageSrc(entries.get(pName));
			patient1.setAmountRaised(10000);
			patient1.setAmountNeeded(20000);
			patient1.setAmountRemaining(1000);
			patient1.setPercentageRaised(25);
			patient1.setTimeLeft("3 days");
			patient1.setPatientName(pName);
			patient1.setId(i++);
			patient1.setCaseTitle(captions.get(pName));
			patient1.setFbShares(253);
			patient1.setTweets(123);
			patients.add(patient1);
		}
		return patients;
	}
	
	private static void getImages(){	
		try {
	        BufferedReader reader = new BufferedReader(new InputStreamReader(H2Connector.class.getClassLoader().getResourceAsStream("Images")));
	        String line;
	        while ((line = reader.readLine()) != null) {
	        	String[] parts = line.split(",");
	        	entries.put(parts[0], parts[2]);
	        	captions.put(parts[0], parts[1]);
	        }
	    } catch (FileNotFoundException e) {
	        e.printStackTrace();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}

}
