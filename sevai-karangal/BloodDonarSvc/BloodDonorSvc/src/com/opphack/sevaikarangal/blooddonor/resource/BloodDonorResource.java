package com.opphack.sevaikarangal.blooddonor.resource;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.opphack.sevaikarangal.blooddonor.data.BloodRequest;
import com.opphack.sevaikarangal.blooddonor.data.Donor;
import com.opphack.sevaikarangal.blooddonor.request.RequestInfo;
import com.opphack.sevaikarangal.blooddonor.request.DonorRequest;
@Path("/donor/")
public class BloodDonorResource {
	
@GET
@Produces(MediaType.TEXT_PLAIN)
@Path("/hello")
public String sayHellow(){
	return "Hello World!";
}
 
 @POST
 @Produces(MediaType.TEXT_PLAIN)
 @Consumes(MediaType.APPLICATION_JSON)
 @Path("/") 
 public String addNewDonor(DonorRequest donorRequest) {
	 	Donor donor = new Donor();
	 	Long donorId = donor.addDonor(donorRequest);
        return String.valueOf(donorId);
    }
 
 @PUT
 @Produces(MediaType.TEXT_PLAIN)
 @Consumes(MediaType.APPLICATION_JSON)
 @Path("/") 
 public String updateDonorLatLong(DonorRequest donorRequest) {
	 	Donor donor = new Donor();
	 	Long donorId = donor.updateDonorLatLong(donorRequest.getDonorId(),donorRequest.getLatitude(),donorRequest.getLongitude());
        return String.valueOf(donorId);
    }
 
 @GET
 @Produces(MediaType.APPLICATION_JSON)
 @Path("/{donorid}")
 public DonorRequest getDonor(@PathParam("donorid") long donorId){
	 Donor donor = new Donor();
	 return donor.getDonorById(donorId);
 }
 
 @GET
 @Produces(MediaType.APPLICATION_JSON)
 @Path("/")
 public List<DonorRequest> getDonor(@QueryParam("city") String city,
		 @QueryParam("bloodGroup") String bloodGroup,
		 @QueryParam("locality") String locality){
	 Donor donor = new Donor();
	 return donor.getDonorByCityBloodGroup(city,bloodGroup,locality);
 }
 
 @GET
 @Produces(MediaType.APPLICATION_JSON)
 @Path("/request/")
 public List<DonorRequest> getDonorByRequestId(@QueryParam("id") long requestId){
	 BloodRequest bloodRequest = new BloodRequest();
	 RequestInfo requestObject = bloodRequest.getRequestById(requestId);
	 if(requestObject.getCity() != null && requestObject.getBloodGroup() != null){
		 return getDonor(requestObject.getCity(),requestObject.getBloodGroup(),requestObject.getLocality());
	 }
	 return null;
 }
 
}
