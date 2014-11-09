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
import com.opphack.sevaikarangal.blooddonor.request.RequestInfo;
@Path("/requestor/")
public class BloodRequestorResource {
	
@GET
@Produces(MediaType.TEXT_PLAIN)
@Path("/hello")
public String sayHellow(){
	return "Hello World from request!";
}
 
 @POST
 @Produces(MediaType.TEXT_PLAIN)
 @Consumes(MediaType.APPLICATION_JSON)
 @Path("/") 
 public String addNewRequest(RequestInfo bloodRequestorRequest) {
	 	BloodRequest bloodRequest = new BloodRequest();
	 	Long requestId = bloodRequest.addRequest(bloodRequestorRequest);
        return String.valueOf(requestId);
    }
 
 @PUT
 @Produces(MediaType.TEXT_PLAIN)
 @Consumes(MediaType.APPLICATION_JSON)
 @Path("/") 
 public String updateRequestStatus(RequestInfo bloodRequestorRequest) {
	 	BloodRequest bloodRequest = new BloodRequest();
	 	Long requestId = bloodRequest.updateRequestStatus(bloodRequestorRequest.getRequestId(),bloodRequestorRequest.getStatus());
        return String.valueOf(requestId);
    }
 
 @GET
 @Produces(MediaType.APPLICATION_JSON)
 @Path("/{requestid}")
 public RequestInfo getRequest(@PathParam("requestid") long requestId){
	 BloodRequest bloodRequest = new BloodRequest();
	 return bloodRequest.getRequestById(requestId);
 }
 
 @GET
 @Produces(MediaType.APPLICATION_JSON)
 @Path("/")
 public List<RequestInfo> getRequest(@QueryParam("city") String city,
		 @QueryParam("bloodGroup") String bloodGroup,
		 @QueryParam("locality") String locality){
	 BloodRequest bloodRequest = new BloodRequest();
	 return bloodRequest.getRequestByCityBloodGroup(city,bloodGroup,locality);
 }
 
 @GET
 @Produces(MediaType.APPLICATION_JSON)
 @Path("/myrequest/")
 public List<RequestInfo> getRequestByPhoneNumber(@QueryParam("phonenumber") Long phoneNumber){
	 BloodRequest bloodRequest = new BloodRequest();
	 return bloodRequest.getRequestByPhoneNum(phoneNumber);
 }
 
}
