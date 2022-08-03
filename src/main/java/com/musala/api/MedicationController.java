package com.musala.api;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.musala.dto.Link;
import com.musala.dto.MedicationDTO;
import com.musala.dto.ResponseMessage;
import com.musala.service.MedicationService;
import com.musala.service.MedicationServiceImpl;
import com.musala.utils.AppException;

@Path("/drones")
@Produces("application/json")
public class MedicationController {

	MedicationService serv = new MedicationServiceImpl();
	
	@GET
	@Path("/{droneID}/medications")
	@Produces("application/json")
	public Response getDronesMedications(@Context UriInfo uriInfo,@PathParam("droneID") int droneID) {
		
		JsonObject obj = new JsonObject();
		GsonBuilder builder = new GsonBuilder();
		builder.excludeFieldsWithoutExposeAnnotation();
		Gson gson = builder.create();
		
		
		try {
			
			List<MedicationDTO> medications = serv.getMedicationsByDrone(uriInfo,droneID);
			
			obj.add("data", new JsonParser().parse(gson.toJson(medications)));
			obj.addProperty("count", medications == null ? 0 : medications.size());
			obj.add("response", new JsonParser().parse(new ResponseMessage("Medications fetched successfully", true).toString()));

			return Response.ok().entity(obj.toString()).build();
			
		}catch(AppException e) {
			obj.add("response", new JsonParser().parse(gson.toJson(new ResponseMessage(e.getMessage(), false))));
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(obj.toString()).build();
		}catch(SQLException e1) {
			obj.add("response", new JsonParser().parse(gson.toJson(new ResponseMessage("Internal server error", false))));
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(obj.toString()).build();
		}catch(Exception e2) {
			obj.add("response", new JsonParser().parse(gson.toJson(new ResponseMessage("Internal server error", false))));
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(obj.toString()).build();
			
		}
	}
	
	
	@POST
	@Path("/{droneID}/medications")
	@Consumes("application/json")
	@Produces("application/json")
	public Response registerDrone(@Context UriInfo uriInfo, MedicationDTO med,@PathParam("droneID") int droneID) {
		JsonObject obj = new JsonObject();
		GsonBuilder builder = new GsonBuilder();
		builder.excludeFieldsWithoutExposeAnnotation();
		Gson gson = builder.create();
		try {
			
			MedicationDTO response = serv.saveMedicationByDrone(droneID, med);
			
			
			List<Link> links = new ArrayList<>();
			links.add(new Link(uriInfo.getAbsolutePathBuilder().path(Integer.toString(response.getMedId())).toString(),"self"));
			response.setLinks(links);
			
			
			obj.add("data", new JsonParser().parse(gson.toJson(response)));
			obj.add("response", new JsonParser().parse(gson.toJson(new ResponseMessage("Drone save successfully", true))));
			
			return Response.status(201).entity(obj.toString()).build();
			
		}catch(AppException e) {
			e.printStackTrace();
			obj.add("response", new JsonParser().parse(gson.toJson(new ResponseMessage(e.getMessage(), false))));
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(obj.toString()).build();
		}catch(SQLException e1) {
			e1.printStackTrace();
			obj.add("response", new JsonParser().parse(gson.toJson(new ResponseMessage("Internal server error", false))));
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(obj.toString()).build();
		}catch(Exception e2) {
			e2.printStackTrace();
			obj.add("response", new JsonParser().parse(gson.toJson(new ResponseMessage("Internal server error", false))));
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(obj.toString()).build();
			
		}
	}
	
	@GET
	@Path("/{droneID}/medications/{medicationID}")
	@Consumes("application/json")
	@Produces("application/json")
	public Response getDroneMedicationByID(@Context UriInfo uriInfo, MedicationDTO med,@PathParam("droneID") int droneID,@PathParam("medicationID") int medicationID) {
		JsonObject obj = new JsonObject();
		GsonBuilder builder = new GsonBuilder();
		builder.excludeFieldsWithoutExposeAnnotation();
		Gson gson = builder.create();
		try {
			System.out.println("Here at medication ID");
			MedicationDTO response = serv.getMedicationByID(medicationID);
			
			
			List<Link> links = new ArrayList<>();
			links.add(new Link(uriInfo.getAbsolutePathBuilder().path(Integer.toString(response.getDroneId())).toString(),"self"));
			response.setLinks(links);
			
			
			obj.add("data", new JsonParser().parse(gson.toJson(response)));
			obj.add("response", new JsonParser().parse(gson.toJson(new ResponseMessage("Drone save successfully", true))));
			
			return Response.status(201).entity(obj.toString()).build();
			
		}catch(AppException e) {
			e.printStackTrace();
			e.printStackTrace();
			obj.add("response", new JsonParser().parse(gson.toJson(new ResponseMessage(e.getMessage(), false))));
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(obj.toString()).build();
		}catch(SQLException e1) {
			e1.printStackTrace();
			obj.add("response", new JsonParser().parse(gson.toJson(new ResponseMessage("Internal server error", false))));
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(obj.toString()).build();
		}catch(Exception e2) {
			e2.printStackTrace();
			obj.add("response", new JsonParser().parse(gson.toJson(new ResponseMessage("Internal server error", false))));
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(obj.toString()).build();
			
		}
	}
	
	
}
