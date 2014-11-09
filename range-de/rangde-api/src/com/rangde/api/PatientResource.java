package com.rangde.api;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.UriInfo;

import com.rangde.api.model.Patient;
import com.rangde.api.model.PatientTimeline;
import com.rangde.api.model.PatientUpdate;

@Path("/patient")
public class PatientResource {

	@Context
	UriInfo uriInfo;
	@Context
	Request request;

	@GET
	@Path("{id}")
	@Produces({ MediaType.APPLICATION_JSON })
	public Patient getPatient(@PathParam("id") Integer id) {
		System.out.println("Fetching patient:" + id);
		Patient patient = SearchResource.patients.get(id);
		return patient;
	}

	@GET
	@Path("/{id}/timeline")
	@Produces({ MediaType.APPLICATION_JSON })
	public PatientTimeline getPatientTimeline(@PathParam("id") Integer id) {
		System.out.println("Fetching patient timeline for:" + id);
		PatientTimeline pt = new PatientTimeline();
		List<PatientUpdate> pts = new ArrayList<PatientUpdate>();
		pts.add(new PatientUpdate(
				"Parvathy Menon",
				"Social Worker",
				"Mohan has been admitted in the hospital today for pre-surgery check up. He is in the general ward. His bed number is 202.",
				"http://www.rangde.org/images/landingPage/madness_pro_img7.jpg",
				"3 hrs ago", "Vaatsalya Gadag"));
		return new PatientTimeline("Fundraising", pts);
	}

	@GET
	@Path("/{id}/about")
	@Produces({ MediaType.APPLICATION_JSON })
	public String getPatientAbout(@PathParam("id") Integer id) {
		String about = "Mohan Kumar is a cycle rickshaw puller, who had a minor accident 3 years ago while riding his rickshaw in Varanasi.  The accident lead to a hairline fracture of the hip bone.  However the long effects of cycling and pulling weight has lead to further damage and for the past 2 months he has not been able to work due to permanent damage to his hip bone.  He has been advised to undergo a total hip replacement surgery.  Having lost his livelihood, he is currently dependent upon his son.  After the surgery he will be able to walk and take up some vocation.";
		return about;
	}

}
